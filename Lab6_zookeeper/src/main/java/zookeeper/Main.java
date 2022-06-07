package zookeeper;

import zookeeper.executor.Executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
            Executor executor = new Executor(hostPort, znode, exec);
            Thread t = new Thread(executor);
            t.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                br.readLine();
                executor.printTree();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
