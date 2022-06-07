package zookeeper.executor;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import static org.apache.zookeeper.KeeperException.Code.SESSIONEXPIRED;

class DataMonitor implements Watcher, AsyncCallback.StatCallback {
    ZooKeeper zooKeeper;
    String znode;
    boolean dead;
    DataMonitorListener listener;

    public DataMonitor(ZooKeeper zooKeeper, String znode, DataMonitorListener listener) {
        this.zooKeeper = zooKeeper;
        this.znode = znode;
        this.listener = listener;
        watch(znode);
    }

    @Override
    public void process(WatchedEvent event) {
        String path = event.getPath();

        if (event.getType() == Event.EventType.None) {
            switch (event.getState()) {
                case SyncConnected:
                    break;
                case Expired:
                    dead = true;
                    listener.closing(SESSIONEXPIRED.intValue());
                    break;
            }
        } else {
            if (path != null && path.equals(znode)) {
                watch(znode);
            }
        }
    }

    private void watch(String path) {
        zooKeeper.exists(znode, true, this, null);
        try {
            Stat nodeStat = zooKeeper.exists(path, true);
            if (nodeStat != null) {
                zooKeeper.getChildren(path, true);
                System.out.println("Actual children number: " + zooKeeper.getAllChildrenNumber(znode));
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        boolean exists;
        KeeperException.Code code = KeeperException.Code.get(rc);
        switch (code) {
            case OK -> exists = true;
            case NONODE -> exists = false;
            case SESSIONEXPIRED, NOAUTH -> {
                dead = true;
                listener.closing(rc);
                return;
            }
            default -> {
                zooKeeper.exists(znode, true, this, null);
                return;
            }
        }
        listener.exists(exists);
    }
}
