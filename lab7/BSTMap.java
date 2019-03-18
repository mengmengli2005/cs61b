import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {
    BST bst;
    int size;

    public BSTMap() {
        this.bst = new BST();
        this.size = 0;
    }

    @Override
    public void clear() {
        this.bst = new BST();
        this.size = 0;
    }

    @Override
    public V get(K key) {
        if (key == null) return null;
        BSTNode node = bst.get(bst.root, key);
        if (node == null) return null;
        else return node.val;
    }

    @Override
    public void put(K key, V value) {
        BSTNode tmp = bst.get(bst.root, key);
        if (tmp != null) {
            tmp.val = value;
            return;
        }
        bst.insert(key, value);
        size ++;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    private class BSTNode{
       private K key;
       private V val;
       private BSTNode left;
       private BSTNode right;

       BSTNode(K key, V val) {
           this.key = key;
           this.val = val;
       }
    }

    private class BST {
        private BSTNode root;

        BST() {
        }

        BSTNode get(BSTNode node, K key) {
            if (key == null) return null;
            if (node == null) return null;
            if (key.equals(node.key)) return node;
            else if (key.compareTo(node.key) < 0) return get(node.left, key);
            else return get(node.right, key);
        }

        void insert(K key, V value) {
            if (key == null) throw new ExceptionInInitializerError();
            root = helper(root, key, value);
        }

        BSTNode helper(BSTNode node, K key, V value) {
            if (node == null) return new BSTNode(key, value);
            if (key.compareTo(node.key) < 0) node.left = helper(node.left, key, value);
            else if (key.compareTo(node.key) > 0) node.right = helper(node.right, key, value);
            return node;
        }

    }

    @Override
    public V remove(K key) {throw new UnsupportedOperationException();}
    @Override
    public V remove(K key, V value) {throw new UnsupportedOperationException();}
    @Override
    public Set<K> keySet() {throw new UnsupportedOperationException();}
    @Override
    public Iterator<K> iterator() {throw new UnsupportedOperationException();}
}