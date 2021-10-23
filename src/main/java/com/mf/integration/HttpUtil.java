package com.mf.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;


import net.sf.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import org.apache.http.entity.StringEntity;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/***
 * http工具类
 */
public class HttpUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public static String HtmltoTxt(String str) {
        str = str.replaceAll("\\<.*?\\>", "");
        str = StringEscapeUtils.unescapeHtml(str);
        str = str.trim().replaceAll("\\n", "<br>");

        return str;
    }

    /***
     *
     * @param str
     * @return
     */
    public static String getFirstUrl(String str) {
        int start = str.indexOf("<");
        int end = str.indexOf(">");

        if (start > 0 && end > 0 && end > start)
            return str.substring(start + 1, end);
        else
            return "";

    }


    public static void post(JSONObject json, String url) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            System.out.println(json.toString());

            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");

            // 解决中文乱码问题
            StringEntity stringEntity = new StringEntity(json.toString(),
                    "UTF-8");
            stringEntity.setContentEncoding("UTF-8");

            httpPost.setEntity(stringEntity);

            System.out
                    .println("Executing request " + httpPost.getRequestLine());

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response)
                        throws ClientProtocolException, IOException {//
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {

                        HttpEntity entity = response.getEntity();

                        return entity != null ? EntityUtils.toString(entity)
                                : null;
                    } else {
                        throw new ClientProtocolException(
                                "Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = httpClient.execute(httpPost, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    /***
     * 基于json提交Post请求
     * @param url
     * @param jsonSTR
     * @return
     */
    public static String postJSONRequest(String url, String jsonSTR) {

        boolean success = false;
        String result = "";
        HttpClient httpClient = new DefaultHttpClient();

        try {
            // get method
            HttpPost httpPost = new HttpPost(url);
            // set header
            httpPost.setHeader("Content-Type", "application/json");
            log.info(url);

            // 解决中文乱码问题
            StringEntity stringEntity = new StringEntity(jsonSTR,
                    "UTF-8");
            stringEntity.setContentEncoding("UTF-8");

            try {
                httpPost.setEntity(stringEntity);

            } catch (Exception e) {
                log.error(e.getMessage());
            }

            // response
            HttpResponse response = null;
            try {

                response = httpClient.execute(httpPost);

                log.info("response " + response.getStatusLine());

                success = true;
                result = EntityUtils.toString(response.getEntity());
                log.info("Result::" + result);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            if (!success)
                httpClient.getConnectionManager().shutdown();
        }
        return result;
    }


    public static String getJson(String Token, String url, List<BasicNameValuePair> formparams, HttpClient httpClient) {

        boolean success = false;

        HttpClient httpClient1 = new DefaultHttpClient();
        String result = "";
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Content-Type", "application/json;charset=UTF-8");
            httpGet.addHeader("Cookie", "LWSSO_COOKIE_KEY=" + Token);
            HttpResponse response = null;
            try {
                response = httpClient1.execute(httpGet);
                log.debug("response " + response.getStatusLine());
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                log.debug(result);
                success = true;
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            if (!success)
                httpClient1.getConnectionManager().shutdown();
        }

        return result;
    }


    public static String getJson(String Token, String url, HttpClient httpClient) {

        boolean success = false;
        HttpClient httpClient1 = new DefaultHttpClient();
        String result = "";
        try {

            HttpGet httpGet = new HttpGet(url);
            // set header
            httpGet.addHeader("Content-Type", "application/json;charset=UTF-8");
            httpGet.addHeader("Cookie", "LWSSO_COOKIE_KEY=" + Token);
            // response
            HttpResponse response = null;
            try {
                response = httpClient1.execute(httpGet);
                log.debug("response " + response.getStatusLine());
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                log.debug(result);
                success = true;
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            if (!success)
                httpClient1.getConnectionManager().shutdown();
        }
        return result;
    }

    /**
     * 登陆后，获取Token
     *
     * @return
     */
    public static HttpClient logon(String url, String username, String password, StringBuffer Token) {

        boolean success = false;
        HttpClient httpClient = new DefaultHttpClient();

        try {
            // get method
            HttpPost httpPost = new HttpPost(url);
            // set header
            httpPost.setHeader("Content-Type", "application/json");
            log.info(url);

            JSONObject obj = new JSONObject();
            obj.put("Login", username);
            obj.put("Password", password);

            // 解决中文乱码问题
            StringEntity stringEntity = new StringEntity(obj.toString(),
                    "UTF-8");
            stringEntity.setContentEncoding("UTF-8");

            try {
                httpPost.setEntity(stringEntity);

            } catch (Exception e) {
                log.error(e.getMessage());
            }

            HttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);

                Token.append(EntityUtils.toString(response.getEntity()));

                success = true;

            } catch (Exception e) {
                log.error(e.getMessage());

            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            if (!success)
                httpClient.getConnectionManager().shutdown();
        }
        if (success) {
            return httpClient;
        } else
            return null;
    }

    /***
     * 提交Json登录
     * @param obj
     * @param url
     * @param httpClient
     * @return
     */
    public static String postJson(JSONObject obj, String Token, String url,
                                  HttpClient httpClient) {

        boolean success = false;
        String result = "";

        try {
            // get method
            HttpPost httpPost = new HttpPost(url);
            // set header
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            httpPost.addHeader("Cookie", "LWSSO_COOKIE_KEY=" + Token);
            Header[] enum1 = httpPost.getAllHeaders();


            // 解决中文乱码问题
            StringEntity stringEntity = new StringEntity(obj.toString(),
                    "UTF-8");
            stringEntity.setContentEncoding("UTF-8");

            try {
                httpPost.setEntity(stringEntity);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            HttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);

                System.out.println("response " + response.getStatusLine());
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
                success = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }


    /**
     * @return
     */
    public static boolean logout(HttpClient httpClient) {

        boolean success = false;
        // HttpClient httpClient = new DefaultHttpClient();

        try {
            // get method
            HttpPost httpPost = new HttpPost(Constants.endPoint);
            // System.out.println("logout---"+Constants.logout_url);
            // set header
            httpPost.setHeader("Content-Type",
                    "application/x-www-form-urlencoded");

            // response
            HttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);
            } catch (Exception e) {
                System.out
                        .println("Exception---------------:" + e.getMessage());
            }

            try {
                // HttpEntity entity = response.getEntity();
                System.out.println("logout" + response.getStatusLine());

                // String result=EntityUtils.toString(response.getEntity());
                // System.out.println(result);
                success = true;
            } catch (Exception e) {
                System.out.println("Exception1---------------:"
                        + e.getMessage());
            }

        } catch (Exception ex) {
            System.out.println("Exception2---------------:" + ex.getMessage());
        } finally {
            if (!success) {

                httpClient.getConnectionManager().shutdown();
            }
        }
        if (httpClient != null) {
            httpClient.getConnectionManager().shutdown();
        }
        return success;
    }


    /***
     * @param urlText
     * @return String
     * @author Boyer
     */
    public static String urlToLink(String urlText) {

        String regexp = "(((http|ftp|https|file)://)|((?<!((http|ftp|https|file)://))www\\.))"
                + ".*?"
                + "(?=(&nbsp;|\\s|锟斤拷|<br />|$|[<>]))";
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(urlText);
        StringBuffer stringbuffer = new StringBuffer();
        while (matcher.find()) {
            String url = matcher.group().substring(0, 3).equals("www") ? "http://"
                    + matcher.group()
                    : matcher.group();
            String tempString = "<a href=\"" + url + "\">" + matcher.group()
                    + "</a>";

            int tempLength = tempString.length();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < tempLength; ++i) {
                char c = tempString.charAt(i);
                if (c == '\\' || c == '$') {
                    buffer.append("\\").append(c);
                } else {
                    buffer.append(c);
                }
            }
            tempString = buffer.toString();
            matcher.appendReplacement(stringbuffer, tempString);
        }
        matcher.appendTail(stringbuffer);
        return stringbuffer.toString();
    }

    /**
     * @param note
     * @return
     * @author Boyer
     */
    public static String textToLinks(String note) {

        int noteLength = note.length();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < noteLength; ++i) {
            char c = note.charAt(i);
            if (c == '\\' || c == '$') {
                buffer.append("\\").append(c);
            } else {
                buffer.append(c);
            }
        }
        String linkNote = "/a>" + buffer.toString() + "<a ";
        String regexp = "(?<=/a>).*?(?=<a )";
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(linkNote);
        StringBuffer stringbuffer = new StringBuffer();
        while (matcher.find()) {
            String tempString = urlToLink(matcher.group());
            matcher.appendReplacement(stringbuffer, tempString);
        }
        matcher.appendTail(stringbuffer);
        String result = stringbuffer.toString();

        return result.substring(3, result.length() - 3);
    }


}