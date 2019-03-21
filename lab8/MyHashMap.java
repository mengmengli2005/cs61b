import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Array;
import java.util.*;
//import java.util.ArrayList;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private ArrayList<ArrayMap<K, V>> Buckets;
    private int size;
    private double loadFactor;
    private int capacity;

    public MyHashMap() {
        this.Buckets = new ArrayList<>(16);
        this.loadFactor = 0.75;
        this.size = 0;
        this.capacity = 16;
        for (int i = 0; i < capacity; i ++) Buckets.add(new ArrayMap<>());
    }
    public MyHashMap(int initialSize) {
        this.Buckets = new ArrayList<> (initialSize);
        this.loadFactor = 0.75;
        this.size = 0;
        this.capacity = initialSize;
        for (int i = 0; i < capacity; i ++) Buckets.add(new ArrayMap<>());
    }
    public MyHashMap(int initialSize, double loadFactor) {
        this.Buckets = new ArrayList<>(initialSize);
        this.loadFactor = loadFactor;
        this.size = 0;
        this.capacity = initialSize;
        for (int i = 0; i < capacity; i ++) Buckets.add(new ArrayMap<>());
    }

    public int hash(K key) {
        return key.hashCode();
    }

    @Override
    public void clear() {
        this.Buckets = new ArrayList<>(16);
        this.size = 0;
        this.capacity = 16;
        for (int i = 0; i < capacity; i ++) Buckets.add(new ArrayMap<>());
    }

    @Override
    public boolean containsKey(K key) {
        int hashKey = hash(key);
        hashKey = (hashKey % capacity + capacity) % capacity; // 这里需要取余，如何获得 ArrayList's capacity?
        ArrayMap bucket = Buckets.get(hashKey);
        if (bucket.size == 0) return false;
        return bucket.containsKey(key);
    }

    @Override
    public V get(K key) {
        int hashKey = (hash(key) % capacity + capacity) % capacity;
        ArrayMap bucket = Buckets.get(hashKey);
        if (bucket.size == 0) return null;
        return (V) bucket.get(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if ((double) size / capacity >= loadFactor) resize();
        int hashKey = (hash(key) % capacity + capacity) % capacity;
        if (containsKey(key)) {
            Buckets.get(hashKey).insert(key, value);
            return;
        }
        Buckets.get(hashKey).insert(key, value);
        this.size = this.size + 1;
    }

    public void resize() {
        ArrayList<ArrayMap<K, V>> oldBuckets = this.Buckets;
        capacity = capacity * 2;
        this.Buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i ++) Buckets.add(new ArrayMap<>());
        for (int i = 0; i < capacity / 2; i ++) {
            ArrayMap<K, V> A = oldBuckets.get(i);
            if (A.size == 0) continue;
            for (int j = 0; j < A.size; j ++) {
                int hashKey = hash(A.keys[j]) % capacity;
                Buckets.get(hashKey).insert(A.keys[j], A.values[j]);
            }
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> setOfKey = new HashSet<>(this.size);
        for (int i = 0; i < Buckets.size(); i ++) {
            if (Buckets.get(i).size != 0) {
                ArrayMap bucket = Buckets.get(i);
                ArrayList<K> Keys = bucket.getKeys();
                setOfKey.addAll(Keys);
            }
        }
        return setOfKey;
    }

    @Override
    public Iterator<K> iterator() { return new Iterator<K>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public K next() {
            return null;
        }
    };}
//
//    private class HashMapIter extends Iterator<K> {
//
//
//        @Override
//        boolean hasNext() {
//
//        }
//
//        @Override
//        public K next() {
//
//        }
//    }

    private class ArrayMap<K, V> {
        private K[] keys;
        private V[] values;
        private int size;

        public ArrayMap() {
            keys = (K[]) new Object[100];
            values = (V[]) new Object[100]; /** whether if 100 is a good size? Or should the size of ArrayMap changed? */
            size = 0;
        }

        void insert(K key, V val) {
            int ind = getKeyIndex(key);
            if (ind < 0) {
                keys[size] = key;
                values[size] = val;
                this.size += 1;
            } else values[ind] = val;
        }

        int getKeyIndex(K key) {
            for (int i = 0; i < size; i ++) {
                if (key.equals(keys[i])) return i;
            }
            return -1;
        }

        boolean containsKey(K key) {
            return getKeyIndex(key) >= 0;
        }

        V get(K key) {
            if (!this.containsKey(key)) return null;
            int index = getKeyIndex(key);
            return values[index];
        }

        ArrayList<K> getKeys() {
            ArrayList<K> iKeys = new ArrayList<>();
            for (int i = 0; i < this.size; i ++) {
                iKeys.add(keys[i]);
            }
            return iKeys;
        }
    }

    @Override
    public V remove(K key) {throw new UnsupportedOperationException();}
    @Override
    public V remove(K key, V value) {throw new UnsupportedOperationException();}
}
