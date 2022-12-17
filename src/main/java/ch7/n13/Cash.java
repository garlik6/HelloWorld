package ch7.n13;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cash<K, V> extends LinkedHashMap<K, V> {
    private int max_entries = 100;

    public Cash(int max_entries) {
        super();
        this.max_entries = max_entries;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > max_entries;
    }
}
