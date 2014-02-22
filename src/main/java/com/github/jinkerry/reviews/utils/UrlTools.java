package com.github.jinkerry.reviews.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: jinfeng
 * Date: 14-2-21
 * Time: 下午3:55
 * To change this template use File | Settings | File Templates.
 */
public class UrlTools {

    private String url;
    private Map<String, String> queryParam;

    public UrlTools(String url) {
        this.url = url;
    }

    public Map<String, String> getQueryParam() {
        return this.queryParam;
    }


    public void setQueryParam(Map<String, String> queryParam) {
        this.queryParam = queryParam;
    }


    public void addQueryParam(String key, String value) {
        if (queryParam == null){
            queryParam = new TreeMap<String, String>();
        }
        if(value != null)
            queryParam.put(key, value);
    }


    public String getCompleteUrl() throws UnsupportedEncodingException {

        StringBuilder sb = new StringBuilder(url);

        if(queryParam != null && queryParam.size() > 0){
            Set<String> keys = queryParam.keySet();

            Iterator<String> it = keys.iterator();
            if(it.hasNext()){
                String key = it.next();
                String value = queryParam.get(key);
                sb.append("?").append(key + "=").append(value);
            }
            while (it.hasNext()) {
                String key = it.next();
                String value = queryParam.get(key);
                sb.append("&").append(key + "=").append(value);
            }
        }

        return sb.toString();
    }


}
