package com.obolonyk.dataStructures.Map;

public interface Map<K, V> extends Iterable<Map.Entry<K,V>> {
    V put (K key, V value);
    V get (K key);
    boolean containsValue(V value);
    V remove (K key);
    int size();
    String toString();

    interface Entry <K, V>{
        K getKey();
        void setValue(V value);
        V getValue();
    }
}
