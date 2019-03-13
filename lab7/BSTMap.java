import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {
    public int size;
    public bst node;

    public BSTMap() {
        size = 0;
    }

    @Override
    public void clear() {
        this.node = null;
        size = 0;
    }

    @Override
    public V get(K key) {
        if (node == null || !this.containsKey(key)) {return null;}
        if (node.key.equals(key)) {return node.val;}
        else if (node.key.compareTo(key) > 0) {return node.left.get(node.left, key).val;}
        else {return node.right.get(node.right, key).val;}
    }

    @Override
    public void put(K key, V value) {
        if (node == null) {node = new bst(key, value); size += 1;}
        else {
            bst lookup = node.get(node, key);
            if (lookup != null) {lookup.val = value;}
            else {
                node = node.insect(node, key, value);
                size += 1;
            }
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (node == null) {return false;}
        return node.get(node, key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    private class bst {
        private K key;
        private V val;
        private bst left;
        private bst right;

        public bst(K k, V v, bst l, bst r) {
            key = k; val = v; left = l; right = r;
        }
        public bst(K k, V v) {
            key = k; val = v;
        }

        bst get(bst T, K key) {
            if (T == null) {return null;}
            if (T.key.equals(key)) {return T;}
            else if (T.key.compareTo(key) > 0) {return get(T.left, key);}
            else {return get(T.right, key);}
        }

        bst insect(bst T, K key, V value) {
            if (T == null) {return new bst(key, value);}
            if (T.key.compareTo(key) > 0) {T.left = insect(T.left, key, value);}
            else if (T.key.compareTo(key) < 0) {T.right = insect(T.right, key, value);}
            return T;
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