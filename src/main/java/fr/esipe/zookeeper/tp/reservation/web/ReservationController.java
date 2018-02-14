package fr.esipe.zookeeper.tp.reservation.web;

/**
 * Created by Vyach on 13/02/2018.
 */
import fr.esipe.zookeeper.tp.reservation.model.Client;
import fr.esipe.zookeeper.tp.reservation.model.Ticket;
import fr.esipe.zookeeper.tp.reservation.service.ReservationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService service;


    @RequestMapping(value = "/authentification/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String index(@PathVariable String name) throws Exception {

        Client client = new Client(name);
        service.createIdClientNode(client);

        logger.info("Json string is retourning...");

        return client.toString();
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