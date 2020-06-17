package Week_08;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapCache<K, V> {
    private final int CACHE_SIZE;

    private static final float LOAD_FACTORY = 0.75f;

    private LinkedHashMap<K, V> map;

    /**
     * @param cacheSize   缓存大小
     * @param accessOrder true LRU, false FIFO
     */
    public LinkedHashMapCache(int cacheSize, boolean accessOrder) {
        CACHE_SIZE = cacheSize;

        //LinkedHashMap 元素大于 capacity * loadFactor 的时候，就会自动进行两倍扩容
        //为使缓存大小不会进行自动扩容，初始化大小设置为 (CACHE_SIZE / loadFactor) + 1
        int initialCapacity = (int) Math.ceil(CACHE_SIZE / LOAD_FACTORY) + 1;
        map = new LinkedHashMap<K, V>(initialCapacity, LOAD_FACTORY, accessOrder) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > CACHE_SIZE;
            }
        };
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void clear() {
        map.clear();
    }

    public synchronized int size() {
        return map.size();
    }

    public synchronized void remove(K key) {
        map.remove(key);
    }

    public synchronized Set<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }
}
