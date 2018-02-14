package fr.esipe.zookeeper.tp.reservation.service;

/**
 * Created by Vyach on 13/02/2018.
 */
import fr.esipe.zookeeper.tp.reservation.model.Ticket;
import fr.esipe.zookeeper.tp.reservation.zookeeper.ZkConnect;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Value("${zookeeper.host}")
    private String zkHost;

    private ZkConnect connector;

    public void createIdClientNode(String fulliDentite) throws Exception {

        Date date = new Date();

        String idClient = fulliDentite + "#" + date;

        logger.info("Consitution of idClient : " + idClient);

        connector = new ZkConnect();
        ZooKeeper zk = connector.connect(zkHost);

        connector.createNode("/id_" + idClient, fulliDentite.getBytes());

        connector.close();


    }

    public void createTicketRefNode(String idClientPath, Ticket ticket) throws Exception {

        connector = new ZkConnect();
        ZooKeeper zk = connector.connect(zkHost);

        connector.createNode(idClientPath + "/" + ticket.getReference(), ticket.getLibelle().getBytes());

    }

}