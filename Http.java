package com.example;


import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;

public class Http {

    public static Object Get(String endpoint, Class returnClassType, Map<String, String> headers) throws Exception
    {
        Object body = null;

        try {
            return Request("GET", endpoint, body, returnClassType, headers);
        } catch (Exception e) {
            throw e;
        }
    }

    public static Object Post(String endpoint, Object body, Class returnClassType, Map<String, String> headers) throws Exception
    {
        try {
            return Request("POST", endpoint, body, returnClassType, headers);
        } catch (Exception e) {
            throw e;
        }
    }


    public static Object Put(String endpoint, Object body, Class returnClassType, Map<String, String> headers) throws Exception
    {
        try {
            return Request("PUT", endpoint, body, returnClassType, headers);
        } catch (Exception e) {
            throw e;
        }
    }

    public static Object Delete(String endpoint, Class returnClassType, Map<String, String> headers) throws Exception
    {
        Object body = null;

        try {
            return Request("DELETE", endpoint, body, returnClassType, headers);
        } catch (Exception e) {
            throw e;
        }
    }


    public static Object Request(String method, String endpoint, Object body, Class returnClassType, Map<String, String> headers) throws Exception
    {
        PayloadParser payloadParser = new PayloadParser();

        String requestJson = "{}";

        if (body != null) {
            requestJson = payloadParser.toJson(body);
        }

        URL url = new URL(endpoint);
        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        String correctEncodedURL=uri.toASCIIString();
        url = new URL(correctEncodedURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setUseCaches(false);
        connection.setConnectTimeout(15000);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Cache-Control", "no-cache");

        for (Map.Entry<String,String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        if (method != "GET") {
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            DataOutputStream stream = new DataOutputStream(outputStream);
            stream.write(requestJson.getBytes("UTF-8"));
            stream.flush();
            stream.close();
        }

        connection.connect();

        InputStream inputStream = connection.getInputStream();
        String responseJson = payloadParser.inputStreamToString(inputStream);
        connection.disconnect();

        return payloadParser.parseJson(responseJson, returnClassType);
    }

}
