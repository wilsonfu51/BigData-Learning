package com.wilson.java;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

public class CreateGroup implements Watcher {

    private static final int SESSION_TIMEOUT = 5000;

    private ZooKeeper zk;
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public void connect(String hosts) throws IOException, InterruptedException {
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
        /*new Watcher() {
		
            public void process(WatchedEvent we) {
   
               if (we.getState() == KeeperState.SyncConnected) {
                  connectedSignal.countDown();
               }
            }
        });*/
        System.out.println("connect");
        connectedSignal.await();
        System.out.println("connect2");
    }

    @Override
    public void process(WatchedEvent event) { // Watcher Interface
        System.out.println("process0");
        if (event.getState() == KeeperState.SyncConnected) {
            System.out.println("process");
            connectedSignal.countDown();
        }
    }

    public void create(String groupName) throws KeeperException,
     InterruptedException {
        System.out.println("create");
        String path = "/" + groupName;
        String createdPath = zk.create(path, null/*data*/, Ids.OPEN_ACL_UNSAFE,
            CreateMode.PERSISTENT);
        System.out.println("Created " + createdPath);
    }

    public void close() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws Exception {
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect(args[0]);
        createGroup.create(args[1]);
        createGroup.close();
    }
}
