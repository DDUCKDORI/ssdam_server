package com.dduckdori.ssdam_server.Login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClientUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static String doGet(String URL){
        String result = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        Integer statusCode = null;
        String reasonPhrase = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet get = new HttpGet(URL);
            response = httpClient.execute(get);
            statusCode = response.getStatusLine().getStatusCode();
            reasonPhrase = response.getStatusLine().getReasonPhrase();
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity,"UTF-8");
            EntityUtils.consume(entity);
            if(statusCode!=200){
                logger.error(String.format("[doGet]http get url(%s) failed. status code:%s. reason:%s. result:%s", URL, statusCode, reasonPhrase, result));
            }
        }catch(Throwable t){
            logger.error(String.format("[doGet]http get url(%s) failed. status code:%s. reason:%s.", URL, statusCode, reasonPhrase), t);
        }finally {
            try{
                if(response!= null){
                    response.close();
                }
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error(String.format("[doGet]release http get resource failed. url(%s). reason:%s.", URL, e.getMessage()));
            }
        }
        return result;
    }

    public static String doPost(String url, Map<String, String> tokenRequest) {
        String result = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        Integer statusCode = null;
        String reasonPhrase = null;
        try{
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
            List<NameValuePair> nvps = new ArrayList<>();
            Set<Map.Entry<String,String>> entrySet = tokenRequest.entrySet();
            for(Map.Entry<String,String> entry : entrySet){
                String fieldName = entry.getKey();
                String fieldValue = entry.getValue();
                nvps.add(new BasicNameValuePair(fieldName,fieldValue));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps);
            httpPost.setEntity(formEntity);
            httpResponse = httpClient.execute(httpPost);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
            HttpEntity entity = httpResponse.getEntity();

            result = EntityUtils.toString(entity,"UTF-8");
            if (statusCode != 200) {
                logger.error(String.format("[doPost]post url(%s) failed. status code:%s. reason:%s. param:%s. result:%s", url, statusCode, reasonPhrase, objectMapper.writeValueAsString(tokenRequest), result));
            }
            EntityUtils.consume(entity);
        } catch (Throwable t) {
            try {
                logger.error(String.format("[doPost]post url(%s) failed. status code:%s. reason:%s. param:%s.", url, statusCode, reasonPhrase, objectMapper.writeValueAsString(tokenRequest)), t);
            } catch (JsonProcessingException e) {
            }
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                try {
                    logger.error(String.format("[doPost]release http post resource failed. url(%s). reason:%s, param:%s.", url, e.getMessage(), objectMapper.writeValueAsString(tokenRequest)));
                } catch (JsonProcessingException ex) {
                }
            }
        }
        return result;
    }
}