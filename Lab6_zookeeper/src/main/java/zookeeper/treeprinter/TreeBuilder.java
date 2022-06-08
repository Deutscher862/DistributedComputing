package zookeeper.treeprinter;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

public class TreeBuilder {
    public static TreeNode buildTree(String rootName, ZooKeeper zooKeeper) {
        TreeNode root = new TreeNode(rootName);
        createNodeTree(zooKeeper, root, rootName);
        return root;
    }

    private static void createNodeTree(ZooKeeper zooKeeper, TreeNode currentNode, String path) {
        try {
            List<String> children = zooKeeper.getChildren(path, false);
            for (String child : children) {
                currentNode.addNode(new TreeNode(path + "/" + child));
            }
            for (TreeNode child : currentNode.getChildren()) {
                createNodeTree(zooKeeper, child, child.getName());
            }
        } catch (KeeperException | InterruptedException e) {
            System.err.println("node called \\z does not exist");
            e.printStackTrace();
        }
    }
}
