package com.wilson.java;
import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;

public class DeleteGroup extends ConnectionWatcher{

    public void delete(String groupName) {
        String path = "/" + groupName;

        List<String> children;
        try {
            children = zk.getChildren(path, false);
            for (String child : children) {
                zk.delete(path + "/" + child, -1);
            }
            zk.delete(path, -1);
        } catch (KeeperException | InterruptedException e) {
            System.out.printf("Group %s does not exist\n", groupName);
            System.exit(1);
        }
        
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect(args[0]);
        deleteGroup.delete(args[1]);
        deleteGroup.close();
    }
    
}
