package com.wilson.java;
import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKExist {
    private static ZooKeeper zk;

    private static ZooKeeperConnection conn;

    // 
    public static Stat exists(String path, Boolean watch) 
        throws KeeperException, InterruptedException {
        return zk.exists(path, watch);
    }

    public static void main(String[] args) 
        throws KeeperException, InterruptedException, IOException {
        String path = "/MyFirstZnode";

        conn = new ZooKeeperConnection();
        zk = conn.connect("master");

        if(exists(path, true) != null) {
            System.out.println("Znode /MyFirstZnode exists.");
        } else {
            System.out.println("Znode " + path + " not exists.");
        }
    }
}
