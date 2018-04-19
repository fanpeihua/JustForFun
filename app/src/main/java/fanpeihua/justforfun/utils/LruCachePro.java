package fanpeihua.justforfun.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by oneball on 2018/3/15.
 */

public class LruCachePro<K, V> {
    private int maxSize;
    private final LinkedHashMap<K, V> map;
    private int hitCount;
    private int missCount;
    private int createCount;
    private int size;
    private int evictionCount;
    private int putCount;

    public LruCachePro(int maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException("maxSize<=0");
        }
        this.maxSize = maxSize;
        this.map = new LinkedHashMap<K, V>(0, 0.75f, true);
    }

    /**
     * 通过key获得相应的item，或者创建返回相应的item。
     * 相应的item会移动到队列的尾部，如果item的value没有被cache或者不能被创建，则返回null
     * @param key
     * @return
     */
    public final V get(K key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }

        V mapValue;
        synchronized (this) {
            mapValue = map.get(key);
            if (mapValue != null) {
                //mapValue不为空表示命中，hitCount+1并返回mapValue对象
                hitCount++;
                return mapValue;
            }
            missCount++;//未命中
        }

        /**
         * 如果未命中，则试图创建一个
         */
        V createdValue = create(key);
        if (createdValue == null) {
            return null;
        }
        synchronized (this) {
            createCount++;
            mapValue = map.put(key, createdValue);
            if (mapValue != null) {
                map.put(key, mapValue);
            } else {
                size += safeSizeOf(key, createdValue);
            }
        }

        if (mapValue != null) {
            entryRemoved(false, key, createdValue, mapValue);
            return mapValue;
        } else {
            //每次新加入对象都需要调用trimToSize方法看是否需要回收
            trimToSize(maxSize);
            return createdValue;
        }
    }

    public final V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("key == null || value ==null");
        }
        V previous;
        synchronized (this) {
            putCount++;
            size += safeSizeOf(key, value);
            previous = map.put(key, value);
            if (previous != null) {
                //如果之前存在键为key的对象，则size应该减去原来对象的大小
                size -= safeSizeOf(key, previous);
            }
        }

        if (previous != null) {
            entryRemoved(false, key, previous, value);
        }
        trimToSize(maxSize);
        return previous;
    }

    protected void entryRemoved(boolean evicted,K key ,V oldValue,V newValue){}

    private void trimToSize(int maxSize) {
        while (true) {
            K key;
            V value;
            synchronized (this) {
                if (size < 0 || (map.isEmpty() && size != 0)) {
                    throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting" +
                            "inconsisitent results");
                }
                if (size <= maxSize || map.isEmpty()) {
                    break;
                }
                Map.Entry<K, V> toEvict = map.entrySet().iterator().next();
                key = toEvict.getKey();
                value = toEvict.getValue();
                map.remove(key);
                size -= safeSizeOf(key, value);
                evictionCount++;
            }
        }
    }

    private V create(K key) {
        return null;
    }

    private int safeSizeOf(K key, V value) {
        int result = sizeOf(key, value);
        if (result < 0) {
            throw new IllegalStateException("Negative size:" + key + "-" + value);
        }
        return result;
    }

    protected int sizeOf(K key, V value) {
        return 1;
    }

    private final void evictAll() {
        trimToSize(-1);
    }

    public synchronized final int size() {
        return size;
    }

    public synchronized final int maxSize() {
        return maxSize;
    }

    public synchronized final int hitCount() {
        return hitCount;
    }

    public synchronized final int missCount() {
        return missCount;
    }

    public synchronized final int createCount() {
        return createCount;
    }

    public synchronized final int putCount() {
        return putCount;
    }

    public synchronized final int evictionCount() {
        return evictionCount;
    }

    public synchronized final Map<K, V> snapShot() {
        return new LinkedHashMap<K, V>(map);
    }

    @Override
    public synchronized final String toString() {
        int accesses = hitCount + missCount;
        int hitPercent = accesses != 0 ? (100 * hitCount / accesses) : 0;
        return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]",
                maxSize, hitCount, missCount, hitPercent);
    }

}
