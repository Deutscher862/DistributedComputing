package zookeeper.executor;

interface DataMonitorListener {
    void exists(boolean exists);

    void closing(int rc);
}
