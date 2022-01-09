package com.wilson.java.Lock;

import java.io.IOException;
import java.util.List;

import com.wilson.java.ConnectionWatcher;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

/**
 * 一个分布式锁的实现, 线程在/locks目录下使用文件名及当前zk连接sessionID作为节点
 * 前缀，创建临时顺序znode，znode序号最小的可以获得锁，否则等待至前一个序号节点删除
 * 后再尝试获取锁
 * 
 * 创建日期：2022/01/09
 * 
 */
public class DistributedLock extends ConnectionWatcher {
    
    private static String lockPath = "/locks";

    // 需要先创建/lock永久节点，在/lock路径下创建短暂顺序节点，返回序列号作为锁序号
    public long lock(String clientID) throws KeeperException, InterruptedException {
        String path = lockPath + "/" + clientID;
        String lock = zk.create(path, clientID.getBytes(), Ids.OPEN_ACL_UNSAFE, 
            CreateMode.EPHEMERAL_SEQUENTIAL);
        long lockID = Long.parseLong(lock.substring(path.length()));

        // System.out.printf("znode is: %s, lockID is: %s\n", lock, lockID);
        List<String> children = zk.getChildren(lockPath, false);
        for (String child : children) {
            System.out.println(child);
        }

        return lockID;

    }

    /**
     * 
     * @param fileName 创建节点的前缀
     * @param sessionId 当前连接的sessionId，创建节点时跟在文件名之后作为节点名的一部分
     * @param watcher watch the znode which's serial number is current serrial number minus 1
     * @return 是否获取到锁
     * @throws KeeperException
     * @throws InterruptedException
     * @throws IOException
     */
    public LockMap getLock(String fileName, String sessionId, Watcher watcher) 
        throws KeeperException, InterruptedException, IOException {

        String id = fileName + sessionId;
        LockMap lockMap = new LockMap(-1L, true);
        
        // 对指定prefix, 如果已经存在相关短暂节点，则直接获取序列号作为lockId,否则创建新节点
        // 并使用新节点的序列号做为ID
        Stat stat = zk.exists(lockPath, false);
        if (stat == null) {
            zk.create(lockPath, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        List<String> children = zk.getChildren(lockPath, false);
        System.out.println("children size is :" + children.size());

        for (String child : children) {
            System.out.println("child: " + child + ", id: " + id);
            if ( id.length() < child.length() ) {
                if (child.substring(0, id.length()).equals(id)) {
                    lockMap.setLockId( Long.parseLong(child.substring(id.length())) );
                    break;
                }
            }
        }
        if (lockMap.getLockId() == -1L) {
            lockMap.setLockId( lock(id) );
        }
        
        children = zk.getChildren(lockPath, false);

        // 如果/lock下有比当前节点序号更小的节点，则不能获取锁
        for (String child : children) {
            if (lockMap.getLockId() > Long.parseLong(child.substring(child.length()-10))) {
                lockMap.setLockGeted(false);
                // 将lockId - 1节点作为watcher对象，监测该节点是否存在，不存在时再触发获取锁
                if ( lockMap.getLockId() - 1 == Long.parseLong(child.substring(child.length()-10)) ) {
                    zk.exists(lockPath + "/" + child, watcher);
                }
            }
        }

        return lockMap;
    }

    public void unlock(String fileName, String sessionID, LockMap lockMap) throws InterruptedException, KeeperException {
        int len = 10 - String.valueOf(lockMap.getLockId()).length();
        String idWithPrefixZeros = "0000000000".substring(0,len) + lockMap.getLockId();
        String znodeName = fileName + sessionID + idWithPrefixZeros;
        Stat stat = zk.exists(lockPath + "/" + znodeName, false);
        if (stat != null) {
            zk.delete(lockPath + "/" + znodeName, -1);
        }
    }

}
