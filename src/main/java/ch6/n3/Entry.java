package ch6.n3;

import java.util.Map;

public class Entry<K,V> implements Map.Entry<K,V>{
   private final K key;
   private V value;

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    public Entry(K key) {
        this.key = key;
    }

    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }
}
