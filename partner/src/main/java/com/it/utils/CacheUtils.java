package com.it.utils;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;


/**
 * Created by xieyue on 2016/6/27.
 * CacheUtils
 */
public class CacheUtils {

    private static CacheManager cacheManager = new CacheManager();
    private static Ehcache ehcache = cacheManager.getEhcache("user");

    public static Object get(String key) {
        Element element = ehcache.get(key);
        // element 为空则返回 null,否则element.getObjectValue()
        return element == null ? null : element.getObjectValue();
    }

    public static void set(String key, Object object) {
        Element element = new Element(key, object);
        ehcache.put(element);
    }

    public static Object remove(String key) {
        Object object = ehcache.get(key);
        ehcache.remove(key);
        return object;
    }
}
