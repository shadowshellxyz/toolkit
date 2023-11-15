//
//package net.shadowdefender.toolkit.template.cache;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * CacheSourceTest
// *
// * @author shadow
// */
//public class CacheSourceTest {
//
//    private CacheSource<String> cacheSource = new CacheSource<String>() {
//
//        Map<String, String> CACHE = new HashMap<>();
//
//        @Override
//        public String getCacheKeyPrefix() {
//            return "JARVIS";
//        }
//
//        @Override
//        public String getObjectFromCache(String key, Class<String> type) {
//            return "net.shadowdefender".equalsIgnoreCase(key) ? "hi" : null;
//        }
//
//        @Override
//        public void putObjectToCache(String key, String object, int ttl) {
//
//        }
//
//        @Override
//        public CacheTtl ttl() {
//            return null;
//        }
//
//        @Override
//        public String serialize(Object object) {
//            return null;
//        }
//
//        @Override
//        public String deserialize(String string, Class<String> type) {
//            return null;
//        }
//    };
//
//    @Test
//    public void defaultCacheKeyItemSeparator() {
//        Assert.assertEquals(":", cacheSource.getCacheKeyItemSeparator());
//    }
//
//    @Test
//    public void getObjectType() {
//        Assert.assertEquals(String.class, cacheSource.getObjectType());
//    }
//
//    @Test
//    public void getEnvCode() {
//        Assert.assertEquals("LOCAL", cacheSource.getEnvInfoCode());
//    }
//
//    @Test
//    public void containsKey() {
//
//        Assert.assertTrue(cacheSource.containsCacheKey("net.shadowdefender"));
//        Assert.assertFalse(cacheSource.containsCacheKey("com.shadowdefender"));
//    }
//
//    @Test
//    public void buildCacheKey() {
//
//        Assert.assertEquals("", cacheSource.buildCacheKey());
//        Assert.assertEquals("", cacheSource.buildCacheKey(null));
//        Assert.assertEquals("", cacheSource.buildCacheKey(new Object[0]));
//
//        Assert.assertEquals("JARVIS:LOCAL:sd", cacheSource.buildCacheKey(new Object[]{"sd"}));
//        Assert.assertEquals("JARVIS:LOCAL:A:B", cacheSource.buildCacheKey(new Object[]{"A", "B"}));
//        Assert.assertEquals("JARVIS:LOCAL:A:B:C", cacheSource.buildCacheKey(new Object[]{"A", "B", "C"}));
//    }
//
//}