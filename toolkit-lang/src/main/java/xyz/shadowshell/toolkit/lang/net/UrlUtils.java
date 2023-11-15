/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author,
 * without permission shall not be any reproduction and transmission.
 */
package xyz.shadowshell.toolkit.lang.net;

import java.util.Map;
import java.util.StringTokenizer;

import xyz.shadowshell.toolkit.lang.Constants;
import xyz.shadowshell.toolkit.lang.MapUtils;

/**
 * UrlUtils
 *
 * @author shadow
 */
public class UrlUtils {

    public static String getPath(String url) {
        String path = "";
        int index = url.indexOf('?');
        if (index == -1) {
            path = URLDecoderUtils.decode(url, Constants.DEFAULT_ENCODING);
        } else {
            path = url.substring(0, index);
        }
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        return path;
    }

    public static Map<String, String> getParametersMap(String url) {
        int index = url.indexOf('?');
        if (index == -1) {
            return null;
        }
        Map<String, String> parametersMap = MapUtils.newHashMap();
        String paramString = url.substring(index + 1);
        StringTokenizer st = new StringTokenizer(paramString, "&");
        while (st.hasMoreTokens()) {
            String e = st.nextToken();
            int sep = e.indexOf('=');
            if (sep != -1) {
                parametersMap.put(URLDecoderUtils.decode((e.substring(0, sep)).trim(), Constants.DEFAULT_ENCODING),
                        URLDecoderUtils.decode((e.substring(sep + 1)), Constants.DEFAULT_ENCODING));
            } else {
                parametersMap.put(URLDecoderUtils.decode((e).trim(), Constants.DEFAULT_ENCODING), "");
            }
        }
        return parametersMap;
    }
}