package com.obolonyk.dataStructures.Map.HashMap;

import com.obolonyk.dataStructures.List.ArrayList.ArrayList;
import com.obolonyk.dataStructures.List.List;
import com.obolonyk.dataStructures.Map.Map;

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

    public HashMap() {
        buckets = new ArrayList[INITIAL_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        restructure();
        List<Entry<K, V>> bucket = getBucket(key);
        V returnedValue = null;
        Entry<K, V> newEntry = new Entry<>(key, value);
        if (bucket.size() == 0) {
            bucket.add(newEntry);
            size++;
            return returnedValue;
        }
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                returnedValue = entry.value;
                entry.value = value;
                return returnedValue;
            }
        }
        bucket.add(newEntry);
        size++;
        return returnedValue;
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
        private int entryCounter;
        private int verifier = -1;
        private Iterator<Entry<K, V>> bucketIterator;
        private Entry<K, V> currentEntry;

        @Override
        public boolean hasNext() {
            return entryCounter != size;
        }

        @Override
        public Entry<K, V> next() {
            if (size != 0) {
                while (entryCounter <= size) {
                    List<Entry<K, V>> currentBucket = buckets[bucketIndex];
                    if (currentBucket == null) {
                        bucketIndex++;
                    } else {
                        if (verifier != bucketIndex) {
                            bucketIterator = currentBucket.iterator();
                            verifier = bucketIndex;
                        }
                        if (bucketIterator.hasNext()) {
                            entryCounter++;
                            currentEntry = bucketIterator.next();
                            return currentEntry;
                        } else {
                            if (bucketIndex == buckets.length - 1) {
                                break;
                            }
                            bucketIndex++;
                        }
                    }
                }
            }
            throw new NoSuchElementException("There is no next element in the map");
        }

        @Override
        public void remove() {
            if (currentEntry != null) {
                bucketIterator.remove();
                currentEntry = null;
                entryCounter = entryCounter - 1;
                verifier = -1;
                size--;
            } else {
                throw new IllegalStateException("The element does not exist");
            }
        }
    }

    private List<Entry<K, V>> getBucket(K key) {
        int base = 0;
        int index;
        if (key != null) {
            base = key.hashCode();
            if (base == Integer.MIN_VALUE) {
                index = Math.abs(base + 1) % buckets.length;
                if (buckets[index] == null) {
                    buckets[index] = new ArrayList<>();
                }
                return buckets[index];
            } else {
                index = Math.abs(base) % buckets.length;
                if (buckets[index] == null) {
                    buckets[index] = new ArrayList<>();
                }
                return buckets[index];
            }
        } else {
            if (buckets[base] == null) {
                buckets[base] = new ArrayList<>();
            }
            return buckets[base];
        }
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
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
        return get(key) != null;
    }

    int getBucketLength() {
        return buckets.length;
    }

    private void restructure() {
        if (buckets.length * LOAD_FACTOR <= size) {
            int newCapacity = INITIAL_CAPACITY * DEFAULT_GROW;
            List<Entry<K, V>>[] newBuckets = new ArrayList[newCapacity];
            List<Entry<K, V>>[] oldBuckets = new ArrayList[buckets.length];
            System.arraycopy(buckets, 0, oldBuckets, 0, buckets.length);
            buckets = newBuckets;
            size = 0;
            for (List<Entry<K, V>> oldBucket : oldBuckets) {
                if (oldBucket != null) {
                    for (Entry<K, V> entry : oldBucket) {
                        put(entry.key, entry.value);
                    }
                }
            }
        }
    }

}
