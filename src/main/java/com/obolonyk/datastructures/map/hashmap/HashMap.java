package com.obolonyk.datastructures.map.hashmap;

import com.obolonyk.datastructures.map.Map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class HashMap<K, V> implements Map<K, V> {
    private static final double LOAD_FACTOR = 0.75;
    private static final int DEFAULT_GROW = 2;
    private static final int INITIAL_CAPACITY = 5;

    private Entry<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        this.buckets = new Entry[INITIAL_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        restructure();
        int index = getBucketIndex(buckets, key);
        V returnedValue = null;
        Entry<K, V> entry = new Entry<>(key, value);
        if (buckets[index] == null) {
            buckets[index] = entry;
            size++;
            return returnedValue;
        }

        Entry<K, V> oldEntry = getLastEntryInChain(buckets[index]);

        if (entry.hash == oldEntry.hash) {
            if (Objects.equals(oldEntry.key, key)) {
                returnedValue = oldEntry.value;
                oldEntry.value = value;
                return returnedValue;
            }
        }

        oldEntry.next = entry;
        size++;
        return returnedValue;
    }

    @Override
    public V get(K key) {
        int index = getBucketIndex(buckets, key);
        V returnedValue = null;
        if (buckets[index] == null) {
            return returnedValue;
        } else {
            Entry<K, V> entry = buckets[index];
            while (entry != null) {
                if (Objects.equals(entry.key, key)) {
                    return entry.value;
                }
                entry = entry.next;
            }
        }
        return null;
    }

    @Override
    public boolean containsValue(V value) {
        boolean returnedValue = false;
        for (Entry<K, V> bucket : buckets) {
            while (bucket != null) {
                if (Objects.equals(bucket.value, value)) {
                    returnedValue = true;
                    return returnedValue;
                }
                bucket = bucket.next;
            }
        }
        return returnedValue;
    }

    @Override
    public V remove(K key) {
        V returnedValue = null;
        int bucketIndex = getBucketIndex(buckets, key);
        Entry<K, V> bucket = buckets[bucketIndex];
        if (bucket == null) {
            return returnedValue;
        } else {
            Entry<K, V> next = bucket.next;
            while (bucket.next != null) {
                if (Objects.equals(bucket.key, key)) {
                    returnedValue = bucket.value;
                    buckets[bucketIndex] = next;
                    size--;
                    return returnedValue;
                }
                bucket = next;
            }
            if (Objects.equals(bucket.key, key)) {
                returnedValue = bucket.value;
                buckets[bucketIndex] = next;
                size--;
                return returnedValue;
            }
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
        private boolean canRemove = false;
        Entry<K, V> currentEntry;
        Entry<K, V> currentBucket;


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
                currentBucket = buckets[bucketIndex];
                if (currentEntry != null) {
                    currentBucket = currentEntry;
                }

                if (currentBucket == null) {
                    bucketIndex++;
                } else {
                    if (currentBucket.next == null) {
                        bucketIndex++;
                        entryCount++;
                        currentEntry = null;
                        canRemove = true;
                        return currentBucket;
                    }
                    entryCount++;
                    currentEntry = currentBucket.next;
                    canRemove = true;
                    return currentBucket;
                }
            }
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("The element does not exist");
            }
            int index = getBucketIndex(buckets, currentBucket.key);
            if (currentBucket.next == null) {
                buckets[index] = null;
            } else {
                Entry<K, V> newEntry = currentBucket.next;
                buckets[index] = newEntry;
            }
            currentBucket = null;
            canRemove = false;
            entryCount--;
            size--;
        }

    }

    private int getBucketIndex(Entry<K, V>[] buckets, K key) {
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
        private Entry<K, V> next;

        private int hash;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            if (key != null) {
                this.hash = key.hashCode();
            }
            hash = 1;
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
            Entry<K, V>[] newBuckets = new Entry[newCapacity];
            for (Map.Entry<K, V> entry : this) {
                innerPut(newBuckets, (Entry<K, V>) entry);
            }
            buckets = newBuckets;
        }
    }

    private void innerPut(Entry<K, V>[] newBuckets, Entry<K, V> entry) {
        entry.next = null;
        K key = entry.getKey();
        int index = getBucketIndex(newBuckets, key);
        if (newBuckets[index] == null) {
            newBuckets[index] = entry;
        } else {
            Entry<K, V> lastEntryInChain = getLastEntryInChain(newBuckets[index]);
            lastEntryInChain.next = entry;
        }
    }

    private Entry<K, V> getLastEntryInChain(Entry<K, V> newBucket) {
        Entry<K, V> lastEntryInChain = newBucket;
        while (lastEntryInChain.next != null) {
            lastEntryInChain = lastEntryInChain.next;
        }
        return lastEntryInChain;
    }

}
