package zookeeper;

import java.io.IOException;

class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: Executor hostPort znode program [args ...]");
            System.exit(2);
        }
        String hostPort = args[0];
        String znode = args[1];
        String[] exec = new String[args.length - 2];
        System.arraycopy(args, 2, exec, 0, exec.length);
        try {
            new Executor(hostPort, znode, exec).run();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
