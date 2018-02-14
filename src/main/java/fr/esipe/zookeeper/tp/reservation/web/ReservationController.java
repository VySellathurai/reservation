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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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

    @RequestMapping(value = "/reservation/ticket/{refClient}", method = RequestMethod.GET)
    @ResponseBody
    public String reservationTicket(@PathVariable String refClient) throws Exception {

        String clientPath = service.getIdClientPath(refClient);

        Ticket ticket = new Ticket("DE343234", "Paris-Lyon", "03-03-18");

        service.createTicketRefNode(clientPath, ticket);

        return "{" +
                "\"operation\": \"ticket\"," +
                " \"status\": \"OK\"" +
                "}";
    }

    @RequestMapping(value = "/reservation/date/{refClient}", method = RequestMethod.GET)
    @ResponseBody
    public String reservationDate(@PathVariable String refClient) throws Exception {

        String clientPath = service.getIdClientPath(refClient);

        Ticket ticket = new Ticket("DE343234", "Paris-Lyon", "03-03-18");

        service.createDateZNode(clientPath, ticket);

        return "{" +
                "\"operation\": \"date\"," +
                " \"status\": \"OK\"" +
                "}";
    }

}