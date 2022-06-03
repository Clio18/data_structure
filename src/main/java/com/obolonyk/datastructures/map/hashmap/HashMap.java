package com.obolonyk.datastructures.map.hashmap;

import com.obolonyk.datastructures.list.arraylist.ArrayList;
import com.obolonyk.datastructures.list.List;
import com.obolonyk.datastructures.map.Map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class HashMap<K, V> implements Map<K, V> {
    private static final double LOAD_FACTOR = 0.75;
    private static final int DEFAULT_GROW = 2;
    private static final int INITIAL_CAPACITY = 5;

    private List<Entry<K, V>>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        buckets = new ArrayList[INITIAL_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        restructure();
        List<Entry<K, V>> bucket = getBucket(key);
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                V returnedValue = entry.value;
                entry.value = value;
                return returnedValue;
            }
        }
        bucket.add(new Entry<>(key, value));
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        List<Entry<K, V>> bucket = getBucket(key);
        V returnedValue = null;
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                returnedValue = entry.value;
            }
        }
        return returnedValue;
    }

    @Override
    public boolean containsValue(V value) {
        boolean returnedValue = false;
        for (List<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (Objects.equals(entry.value, value)) {
                        returnedValue = true;
                        break;
                    }
                }
            }
        }
        return returnedValue;
    }

    @Override
    public V remove(K key) {
        List<Entry<K, V>> bucket = getBucket(key);
        V returnedValue = null;
        int count = 0;
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                returnedValue = entry.value;
                bucket.remove(count);
                size--;
            }
            count++;
        }
        return returnedValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        private int bucketIndex;
        private int entryCount;
        private Iterator<Entry<K, V>> bucketIterator;
        private boolean canRemove = false;


        @Override
        public boolean hasNext() {
            return entryCount != size;
        }


        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no next element in the map");
            }

            while (true) {
                List<Entry<K, V>> currentBucket = buckets[bucketIndex];
                if (currentBucket == null) {
                    bucketIndex++;
                } else {
                    if (bucketIterator == null) {
                        bucketIterator = currentBucket.iterator();
                    }

                    if (!bucketIterator.hasNext()) {
                        bucketIndex++;
                        bucketIterator = null;
                    } else {
                        entryCount++;
                        canRemove = true;
                        return bucketIterator.next();
                    }
                }
            }

        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("The element does not exist");
            }

            bucketIterator.remove();
            canRemove = false;
            entryCount--;
            size--;
        }

    }

    private List<Entry<K, V>> getBucket(K key) {
        return getBucket(buckets, key);
    }

    private List<Entry<K, V>> getBucket(List<Entry<K, V>>[] buckets, K key) {
        int index = getBucketIndex(buckets, key);
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>(1);
        }
        return buckets[index];
    }

    private int getBucketIndex(List<Entry<K, V>>[] buckets, K key) {
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        if (hash == Integer.MIN_VALUE) {
            return 0;
        }
        return Math.abs(hash) % buckets.length;
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public void setValue(V value) {
            this.value = value;

        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (Map.Entry<K, V> kvEntry : this) {
            stringJoiner.add(kvEntry.toString());
        }
        return stringJoiner.toString();
    }

    @Override
    public boolean containsKey(K key) {
        for (Map.Entry<K, V> entry : this) {
            if (Objects.equals(entry.getKey(), key)) {
                return true;
            }
        }
        return false;
    }

    int getBucketLength() {
        return buckets.length;
    }

    @SuppressWarnings("unchecked")
    private void restructure() {
        if (buckets.length * LOAD_FACTOR <= size) {
            int newCapacity = INITIAL_CAPACITY * DEFAULT_GROW;
            List<Entry<K, V>>[] newBuckets = new ArrayList[newCapacity];
            for (Map.Entry<K, V> entry : this) {
                innerPut(newBuckets, entry);
            }
            buckets = newBuckets;
        }

    }

    private void innerPut(List<Entry<K, V>>[] newBuckets, Map.Entry<K, V> entry) {
        K key = entry.getKey();
        int index = getBucketIndex(newBuckets, key);
        List<Entry<K, V>> list = new ArrayList<>(1);
        if (newBuckets[index] == null) {
            newBuckets[index] = list;
        }
        newBuckets[index].add((Entry<K, V>) entry);
    }

}
