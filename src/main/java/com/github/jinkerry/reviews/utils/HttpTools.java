package com.github.jinkerry.reviews.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: jinfeng
 * Date: 14-2-21
 * Time: 上午10:36
 * To change this template use File | Settings | File Templates.
 */
public class HttpTools {

    private static Logger log = Logger.getLogger(HttpTools.class);

    /**
     * POST
     */
    public static String[] post(String url, Header[] headers, HttpEntity httpEntity) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if (headers != null && headers.length != 0) {
            httpPost.setHeaders(headers);
        }
        if (httpEntity != null) {
            httpPost.setEntity(httpEntity);
        }

        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPost);

        String[] resp = new String[2];
        resp[0] =  httpResponse.getStatusLine().toString();
        resp[1] =  processResponse(httpResponse);

        log.debug("POST. URL= " + url + ", status: " + httpResponse.getStatusLine());
        httpPost.abort();
        return resp;
    }


    /**
     * GET
     */
    public static String[] get(String url, Header[] headers) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        if (headers != null && headers.length != 0) {
            httpGet.setHeaders(headers);
        }
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGet);

        String[] resp = new String[2];
        resp[0] =  httpResponse.getStatusLine().toString();
        resp[1] =  processResponse(httpResponse);

        log.debug("GET. URL= " + url + ", status: " + httpResponse.getStatusLine());
        httpGet.abort();
        return resp;
    }


    /**
     * DELETE
     */
    public static String[] delete(String url, Header[] headers) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        if (headers != null && headers.length != 0) {
            httpDelete.setHeaders(headers);
        }
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpDelete);

        String[] resp = new String[2];
        resp[0] =  httpResponse.getStatusLine().toString();
        resp[1] =  processResponse(httpResponse);

        log.debug("DELETE. URL= " + url + ", status: " + httpResponse.getStatusLine());
        httpDelete.abort();
        return resp;
    }

    public static String[] put(String url, Header[] headers) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        if (headers != null && headers.length != 0) {
            httpPut.setHeaders(headers);
        }
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPut);

        String[] resp = new String[2];
        resp[0] =  httpResponse.getStatusLine().toString();
        resp[1] =  processResponse(httpResponse);

        log.debug("PUT. URL= " + url + ", status: " + httpResponse.getStatusLine());
        httpPut.abort();
        return resp;
    }


    private static String processResponse(HttpResponse httpResponse) throws IOException {
        String response = "";
        HttpEntity httpEntity = httpResponse.getEntity();
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));
        String str = null;
        while ((str = reader.readLine()) != null)
            response += str;

        return response;
    }


    public static Document getXML(String response) throws UnsupportedEncodingException, DocumentException {
       InputStream in = new ByteArrayInputStream(response.getBytes("UTF-8"));

       SAXReader saxReader = new SAXReader();
       saxReader.setEncoding("UTF-8");
       Document doc;
       doc = saxReader.read(new InputSource(in));

       return doc;

    }

}
