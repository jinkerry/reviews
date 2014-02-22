package com.github.jinkerry.reviews.utils;

import com.github.jinkerry.reviews.data.CommonData;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: jinfeng
 * Date: 14-2-21
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
public class AuthTools {

    private static Logger logger = Logger.getLogger(AuthTools.class);

    public void getToken() throws IOException, DocumentException {
        String url = "http://www.douban.com/service/auth/request_token";
        UrlTools urlTools = new UrlTools(url);
        urlTools.addQueryParam("oauth_consumer_key", CommonData.apikey);
        urlTools.addQueryParam("oauth_signature_method", CommonData.oauth_signature_method);

        urlTools.addQueryParam("oauth_timestamp", Long.toString(System.currentTimeMillis()));
        Random rand = new Random();
        urlTools.addQueryParam("oauth_nonce", Integer.toString(rand.nextInt()));

        url = urlTools.getCompleteUrl();
        logger.info("before encode: " + url);
        String data = url.substring(url.indexOf("?") + 1);
        logger.info("data: " + data);
        urlTools.addQueryParam("oauth_signature", hmacSha1(CommonData.secret, data));

        url = urlTools.getCompleteUrl();
        String[] response = HttpTools.get(url, null);
        logger.info(urlTools.getCompleteUrl() + " : " + response[1]);

    }


    private static String hmacSha1(String secret, String data) {
        try {
            byte[] secretBytes = secret.getBytes();

            SecretKeySpec signingKey = new SecretKeySpec(secretBytes, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            String str = Base64.encode(rawHmac);
            logger.info("hmacsha1: " + str);
            return str;

//            StringBuilder sb = new StringBuilder();
//            for(byte b:rawHmac){
//                sb.append(byteToHexString(b));
//            }
//
//            logger.info("hmacsha1: " + sb.toString());
//
//            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void auth(){

    }

    public static void main(String[] argv) throws IOException, DocumentException {
        AuthTools tool = new AuthTools();
        tool.getToken();

    }

}
