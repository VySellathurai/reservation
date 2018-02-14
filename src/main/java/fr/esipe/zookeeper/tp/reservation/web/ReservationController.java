package fr.esipe.zookeeper.tp.reservation.web;

/**
 * Created by Vyach on 13/02/2018.
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import fr.esipe.zookeeper.tp.reservation.model.Client;
import fr.esipe.zookeeper.tp.reservation.model.Ticket;
import fr.esipe.zookeeper.tp.reservation.service.ReservationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Controller
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService service;


    @RequestMapping(value = "/authentification", method = RequestMethod.POST)
    @ResponseBody
    public String index(@RequestParam String name, @RequestParam String reference) throws Exception {

        logger.info("Controller index : \n name: " + name + "\n reference: " + reference);

        String response = "{}";

        Client client;

        //si le client ne fournit aucune reference alors on creer un znode avec une reference genere
        if (reference == null || reference == "") {

            logger.info("creation du znode associe");
            client = new Client(name);
            service.createIdClientNode(client);

            response = client.toString();


        } else {
            logger.info("reference detecte");
            client = new Client(name, reference);

            String idRefPath = service.getIdClientPath(reference);

            List<String> childrenZnode = service.getZNodePathFromIdRefPath(idRefPath);

            logger.info("Children of znode: " + idRefPath + "are " + childrenZnode.toString());

            String childrenPath;
            String childrenData;
            HashMap<String, String> childrensMap = new HashMap<String, String>();
            for(String children : childrenZnode) {
                childrenPath = idRefPath + "/" + children.toString();
                childrenData = service.getZNodeData(childrenPath);
                logger.info("path to children :" + childrenPath);

                childrensMap.put(children.toString(), childrenData);

            }

            String json = new ObjectMapper().writeValueAsString(childrensMap);

            logger.info("JSON response : " + json);


            response = json;
        }

        return response;
    }

    @RequestMapping(value = "/reservation/ticket", method = RequestMethod.POST)
    @ResponseBody
    public String reservationTicket(@RequestParam String refClient, @RequestParam String refTicket) throws Exception {


        logger.info("Controller reservationTicket : \n refClient: " + refClient + "\n refTicket: " + refTicket);

        String clientPath = service.getIdClientPath(refClient);

        Ticket ticket = new Ticket(refTicket, "Paris-Lyon", "03-03-18");

        service.createTicketRefNode(clientPath, ticket);

        return "{" +
                "\"operation\": \"ticket\"," +
                " \"status\": \"OK\"" +
                "}";
    }

    @RequestMapping(value = "/reservation/date", method = RequestMethod.POST)
    @ResponseBody
    public String reservationDate(@RequestParam String refClient, @RequestParam String date) throws Exception {

        logger.info("Controller reservationDate : \n refClient: " + refClient + "\n date: " + date);

        String clientPath = service.getIdClientPath(refClient);

        Ticket ticket = new Ticket("DE343234", "Paris-Lyon", date);

        service.createDateZNode(clientPath, ticket);

        return "{" +
                "\"operation\": \"date\"," +
                " \"status\": \"OK\"" +
                "}";
    }

}