package ch6.n3;

import java.util.ArrayList;
import java.util.Map;

public class Table<K, V> {
    private ArrayList<Entry<K, V>> entries;

    public V put(K key, V value) {
        int position = findKeyPos(key);
        if (position == -1) {
            Entry<K,V> entry = new Entry<>(key);
            entry.setValue(value);
            entries.add(entry);
            return null;
        } else {
            Entry<K,V> existingEntry = entries.get(position);
            V oldValue = existingEntry.getValue();
            existingEntry.setValue(value);
            return oldValue;
        }
    }

    public V get(K key) {
        int position = findKeyPos(key);
        if (position != -1) {
            return entries.get(position).getValue();
        }
        return null;
    }

    private int findKeyPos(K key) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getKey() == key)
                return i;
        }
        return -1;
    }
    static class Entry<K,V> implements Map.Entry<K,V>{
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
}
