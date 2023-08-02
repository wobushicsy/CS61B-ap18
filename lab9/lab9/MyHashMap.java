package lab9;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int hashIndex = hash(key);
        int numBuckets = buckets.length;
        hashIndex = Math.floorMod(hashIndex, numBuckets);
        ArrayMap<K, V> am = buckets[hashIndex];

        return am.get(key);
    }

    private void resize(int newSize) {
        ArrayMap<K, V>[] newArrayMap = new ArrayMap[newSize];
        for (int i = 0; i < newSize; i += 1) {
            newArrayMap[i] = new ArrayMap<>();
        }
        for (int i = 0; i < buckets.length; i += 1) {
            ArrayMap<K, V> oldArrayMap = buckets[i];
            for (K key : oldArrayMap.keySet()) {
                int hashIndex = hash(key);
                int numBuckets = newSize;
                hashIndex = Math.floorMod(hashIndex, newSize);
                ArrayMap<K, V> am = newArrayMap[hashIndex];
                am.put(key, oldArrayMap.get(key));
            }
        }
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        int hashIndex = hash(key);
        int numBuckets = buckets.length;
        hashIndex = Math.floorMod(hashIndex, numBuckets);
        ArrayMap<K, V> am = buckets[hashIndex];
        if (!am.containsKey(key)) {
            size += 1;
            if (size * 1.0 / numBuckets > MAX_LF) {
                resize(numBuckets * 2);
            }
        }
        am.put(key, value);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (ArrayMap<K, V> am: buckets) {
            for (K key: am.keySet()) {
                keySet.add(key);
            }
        }

        return keySet;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        int hashIndex = hash(key);
        ArrayMap<K, V> am = buckets[hashIndex];
        V value = am.remove(key);
        if (value != null) {
            size -= 1;
        }

        return value;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        V val = get(key);
        if (!value.equals(val)) {
            return null;
        }
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> keySet = keySet();
        return keySet.iterator();
    }
}
