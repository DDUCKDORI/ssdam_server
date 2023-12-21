package com.dduckdori.SsdamServer.Login;

import com.nimbusds.jose.shaded.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClientUtils {
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

        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static String doPost(String url, Map<String, String> tokenRequest) {
        String result = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        Integer statusCode = null;
        String reasonPharse = null;
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
            reasonPharse = httpResponse.getStatusLine().getReasonPhrase();
            HttpEntity entity = httpResponse.getEntity();

            result = EntityUtils.toString(entity,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
/*
result = {
  "keys": [
    {
      "kty": "RSA",
      "kid": "YuyXoY",
      "use": "sig",
      "alg": "RS256",
      "n": "1JiU4l3YCeT4o0gVmxGTEK1IXR-Ghdg5Bzka12tzmtdCxU00ChH66aV-4HRBjF1t95IsaeHeDFRgmF0lJbTDTqa6_VZo2hc0zTiUAsGLacN6slePvDcR1IMucQGtPP5tGhIbU-HKabsKOFdD4VQ5PCXifjpN9R-1qOR571BxCAl4u1kUUIePAAJcBcqGRFSI_I1j_jbN3gflK_8ZNmgnPrXA0kZXzj1I7ZHgekGbZoxmDrzYm2zmja1MsE5A_JX7itBYnlR41LOtvLRCNtw7K3EFlbfB6hkPL-Swk5XNGbWZdTROmaTNzJhV-lWT0gGm6V1qWAK2qOZoIDa_3Ud0Gw",
      "e": "AQAB"
    },
    {
      "kty": "RSA",
      "kid": "W6WcOKB",
      "use": "sig",
      "alg": "RS256",
      "n": "2Zc5d0-zkZ5AKmtYTvxHc3vRc41YfbklflxG9SWsg5qXUxvfgpktGAcxXLFAd9Uglzow9ezvmTGce5d3DhAYKwHAEPT9hbaMDj7DfmEwuNO8UahfnBkBXsCoUaL3QITF5_DAPsZroTqs7tkQQZ7qPkQXCSu2aosgOJmaoKQgwcOdjD0D49ne2B_dkxBcNCcJT9pTSWJ8NfGycjWAQsvC8CGstH8oKwhC5raDcc2IGXMOQC7Qr75d6J5Q24CePHj_JD7zjbwYy9KNH8wyr829eO_G4OEUW50FAN6HKtvjhJIguMl_1BLZ93z2KJyxExiNTZBUBQbbgCNBfzTv7JrxMw",
      "e": "AQAB"
    },
    {
      "kty": "RSA",
      "kid": "fh6Bs8C",
      "use": "sig",
      "alg": "RS256",
      "n": "u704gotMSZc6CSSVNCZ1d0S9dZKwO2BVzfdTKYz8wSNm7R_KIufOQf3ru7Pph1FjW6gQ8zgvhnv4IebkGWsZJlodduTC7c0sRb5PZpEyM6PtO8FPHowaracJJsK1f6_rSLstLdWbSDXeSq7vBvDu3Q31RaoV_0YlEzQwPsbCvD45oVy5Vo5oBePUm4cqi6T3cZ-10gr9QJCVwvx7KiQsttp0kUkHM94PlxbG_HAWlEZjvAlxfEDc-_xZQwC6fVjfazs3j1b2DZWsGmBRdx1snO75nM7hpyRRQB4jVejW9TuZDtPtsNadXTr9I5NjxPdIYMORj9XKEh44Z73yfv0gtw",
      "e": "AQAB"
    }
  ]
}
 */