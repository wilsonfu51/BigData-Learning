package com.wilson.java;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKGetData {

    protected static final String KeeperState = null;

    private static ZooKeeperConnection conn;

    private static ZooKeeper zk;

    public static Stat znode_exists(String path) throws 
        KeeperException,InterruptedException {
        return zk.exists(path,true);
    }

    public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
        String path = "/sanguo/weiguo";
        final CountDownLatch connectedSignal = new CountDownLatch(5);
          
        try {
            conn = new ZooKeeperConnection();
            zk = new ZooKeeper("master,worker1,worker2",5000, new Watcher(){
                public void process(WatchedEvent we) {
                    List<String> children = null;
                    try {
                        children = zk.getChildren("/", true);
                    } catch (KeeperException | InterruptedException e1) {
                        e1.printStackTrace();
                    }
    
                    for (String child : children) {
                        System.out.println(child);
                    }
                }
            });
            Stat stat = znode_exists(path);
              
            if(stat != null) {
                byte[] b = zk.getData(path, new Watcher() {
                
                    public void process(WatchedEvent we) {
                      
                        if (we.getType() == Event.EventType.None) {
                            switch(we.getState()) {
                                case Expired:
                                connectedSignal.countDown();
                                break;
                            }
                                    
                        } else {
                            String path = "/sanguo/weiguo";
                                    
                                try {
                                    byte[] bn = zk.getData(path, false, null);
                                    String data = new String(bn, "UTF-8");
                                    System.out.println(data);
                                    connectedSignal.countDown();
                                    
                                } catch(Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                        }
                    }
            }, null);
                  
            String data = new String(b, "UTF-8");
            System.out.println(data);
            connectedSignal.await();
                  
            } else {
                System.out.println("Node does not exists");
            }
        } catch(Exception e) {
          System.out.println(e.getMessage());
        }

        getDataAndWatch();
     }

     public static void getDataAndWatch() throws KeeperException, InterruptedException, IOException {

        Thread.sleep(Long.MAX_VALUE);
     }

}