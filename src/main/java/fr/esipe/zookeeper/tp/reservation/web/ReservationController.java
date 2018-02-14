package fr.esipe.zookeeper.tp.reservation.web;

/**
 * Created by Vyach on 13/02/2018.
 */
import fr.esipe.zookeeper.tp.reservation.zookeeper.ZkConnect;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Value("${zookeeper.host}")
    private String zkHost;

    @GetMapping("/")
    @ResponseBody
    public String index() throws Exception {

        ZkConnect connector = new ZkConnect();
        ZooKeeper zk = connector.connect(zkHost);

        List<String> zNodes = zk.getChildren("/", true);

        for(String zNode: zNodes) {
            logger.info("Children de \"/\" " + zNode);
        }


        return "Application is running !";
    }

}