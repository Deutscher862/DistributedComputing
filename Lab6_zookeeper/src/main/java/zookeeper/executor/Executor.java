package zookeeper.executor;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import zookeeper.treeprinter.TreeBuilder;
import zookeeper.treeprinter.TreeNode;

import java.io.IOException;

public class Executor implements Watcher, Runnable, DataMonitorListener {
    private final String znode;
    private final String[] exec;
    private final DataMonitor dataMonitor;
    private Process child;
    private final ZooKeeper zooKeeper;

    public Executor(String hostPort, String znode, String[] exec) throws IOException {
        this.exec = exec;
        this.znode = znode;
        zooKeeper = new ZooKeeper(hostPort, 3000, this);
        dataMonitor = new DataMonitor(zooKeeper, znode, this);
    }

    public void process(WatchedEvent event) {
        dataMonitor.process(event);
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (!dataMonitor.dead) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closing(int rc) {
        synchronized (this) {
            notifyAll();
        }
    }

    @Override
    public void exists(boolean exists) {
        if (exists) {
            if (child == null) {
                System.out.println("Starting child");
                try {
                    child = Runtime.getRuntime().exec(exec);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (child != null) {
                System.out.println("Stopping child");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                child = null;
            }
        }
    }

    public void printTree() {
        System.out.println("Node tree structure:");
        TreeNode root = TreeBuilder.buildTree(znode, zooKeeper);
        System.out.println(root);
    }

}
