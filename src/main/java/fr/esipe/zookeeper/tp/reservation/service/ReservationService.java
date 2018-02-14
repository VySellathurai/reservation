package fr.esipe.zookeeper.tp.reservation.service;

/**
 * Created by Vyach on 13/02/2018.
 */
import fr.esipe.zookeeper.tp.reservation.model.Client;
import fr.esipe.zookeeper.tp.reservation.model.Ticket;
import fr.esipe.zookeeper.tp.reservation.zookeeper.ZkConnect;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Value("${zookeeper.host}")
    private String zkHost;

    private ZkConnect connector;

    public void createIdClientNode(Client client) throws Exception {

        Date date = new Date();

        connector = new ZkConnect();
        ZooKeeper zk = connector.connect(zkHost);

        String path = "/id_" + client.getReference();

        connector.createNode(path, client.getName().getBytes());

        logger.info("path created : " + path + "\n value : " + client.getName());

        connector.close();


    }

    public void createTicketRefNode(String idClientPath, Ticket ticket) throws Exception {

        connector = new ZkConnect();
        ZooKeeper zk = connector.connect(zkHost);

        String path = idClientPath + "/tk_" + ticket.getReference();

        connector.createNode(path, ticket.getLibelle().getBytes());

        logger.info("path created : " + path + "\n value : " + ticket.getLibelle());

        connector.close();

    }

    public void createDateZNode(String idClientPath, Ticket ticket) throws Exception {

        connector = new ZkConnect();
        ZooKeeper zk = connector.connect(zkHost);

        String path = idClientPath + "/dt_" + ticket.getDate();

        connector.createNode(path, ticket.getDate().getBytes());

        logger.info("path created : " + path + "\n value : " + ticket.getDate());

        connector.close();

    }

    public String getIdClientPath(String refClient) {

        return "/id_" + refClient;
    }

    public List<String> getZNodePathFromIdRefPath(String idRefPath) throws Exception {

        connector = new ZkConnect();
        ZooKeeper zk = connector.connect(zkHost);

        List<String> childrens = connector.getZNodeChildren(idRefPath);

        connector.close();

        return childrens;
    }

    public String getZNodeData(String path) throws Exception {

        connector = new ZkConnect();
        ZooKeeper zk = connector.connect(zkHost);

        String data = (String) connector.getZNodeData(path);

        logger.info("from path: " + path + " data: " + data);

        connector.close();

        return data;
    }
}