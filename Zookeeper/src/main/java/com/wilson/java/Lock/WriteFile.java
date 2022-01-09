package com.wilson.java.Lock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.wilson.java.ConnectionWatcher;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;

/**
 * 一个使用zookeeper分布式锁同时写一个文件的案例，一次仅可以有一个线程写文件，
 * 即获取共享锁的那个线程写
 * 
 * 创建日期： 2022/01/09
 */
public class WriteFile extends ConnectionWatcher{

    private static DistributedLock lock;
    private static String fileName;
    private static String content;
    private static LockMap lockMap = new LockMap(-1L, false);
    private static CountDownLatch lockGetedSignal;

    public WriteFile() throws IOException, InterruptedException {
        lock = new DistributedLock();
        String hosts = "master,worker1,worker2";
        lock.connect(hosts);
    }

    public void writeContent(String fileName, String content) throws InterruptedException, KeeperException, IOException {
        Random rand = new Random();
        String sessionID = zk.getSessionId() + "";
        lockMap =  lock.getLock(fileName, sessionID, this);
        if (lockMap.getLockGeted()) {
            System.out.println("lock geted");
            BufferedWriter bw = null;
            try {  
                File file = new File("C:/Users/wilson/lock_test.txt");  
                if (!file.exists()) {  
                    file.createNewFile();  
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);  
                bw = new BufferedWriter(fw);
                bw.write("sessionID " + sessionID + ", int " + content + "\n");
                System.out.println("sessionID " + sessionID + ", int " + content);
                bw.close(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(rand.nextInt(10)*1000);
        } else {
            System.out.println("not get lock");
            lockGetedSignal = new CountDownLatch(1);
            lockGetedSignal.await();
        }
        
        lock.unlock(fileName, sessionID, lockMap);
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == EventType.NodeDeleted) {
            try {
                // System.out.println("get the notice of node deleted");
                writeContent(fileName, content);
            } catch (InterruptedException e) {
                System.out.println("Interrupted. Exiting.\n");
                Thread.currentThread().interrupt();
            } catch (KeeperException e) {
                System.out.printf("KeeperException:  %s. Exiting.\n", e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        lockGetedSignal.countDown();
    }

    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        fileName = args[0];
        for (int i = 0; i < 5; i++) {
            content = i + args[1];
            WriteFile wf = new WriteFile();
            wf.writeContent(fileName, content);
            wf.close();
        }
    }

}
