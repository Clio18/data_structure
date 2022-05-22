package com.obolonyk.dataStructures.Map;

import java.util.HashMap;

public interface Map<K, V> extends Iterable<Map.Entry<K,V>> {
    V put (K key, V value);
    V get (K key);
    boolean containsValue(V value);
    V remove (K key);
    int size();
    String toString();
    boolean containsKey(K key);

    interface Entry <K, V>{
        K getKey();
        void setValue(V value);
        V getValue();
    }
}
