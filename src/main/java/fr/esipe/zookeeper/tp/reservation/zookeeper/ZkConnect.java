package fr.esipe.zookeeper.tp.reservation.zookeeper;

/**
 * Created by Vyach on 13/02/2018.
 */
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkConnect {
    private ZooKeeper zk;
    private CountDownLatch connSignal = new CountDownLatch(0);

    private static final Logger logger = LoggerFactory.getLogger(ZkConnect.class);

    public ZooKeeper connect(String host) throws Exception {
        zk = new ZooKeeper(host, 3000, new Watcher() {
            public void process(WatchedEvent event) {
                if (event.getState() == KeeperState.SyncConnected) {
                    connSignal.countDown();
                }
            }
        });
        connSignal.await();
        return zk;
    }

    public void close() throws InterruptedException {
        zk.close();
    }

    public void createNode(String path, byte[] data) throws Exception {
        zk.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void updateNode(String path, byte[] data) throws Exception {
        zk.setData(path, data, zk.exists(path, true).getVersion());
    }

    public void deleteNode(String path) throws Exception {
        zk.delete(path, zk.exists(path, true).getVersion());
    }


    public Stat getZNodeStats(String path) throws KeeperException,
            InterruptedException {
        Stat stat = zk.exists(path, true);
        if (stat != null) {
            logger.info("Node exists and the node version is "+ stat.getVersion());
        } else {
            logger.info("Node does not exists");
        }
        return stat;
    }

    public Object getZNodeData(String path) throws KeeperException, InterruptedException, UnsupportedEncodingException {

        Stat stat = getZNodeStats(path);
        byte[] b = null;
        if (stat != null) {
            b = zk.getData(path, true, null);
        }

        String data = new String(b, "UTF-8");

        return data;
    }

}