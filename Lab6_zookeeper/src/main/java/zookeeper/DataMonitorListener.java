package zookeeper;

public interface DataMonitorListener {
    void exists(boolean exists);

    void closing(int rc);
}
