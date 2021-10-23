package com.mf.integration;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;


import com.mf.smax.web.ticketController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.HttpClient;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;


public class SMACall {

    private static Logger log = LoggerFactory.getLogger(ticketController.class);

    public static void main(String[] args) throws UnsupportedEncodingException {


        if (args.length > 2) {


            String displaylabel = args[0];
            String Description = args[1];
            String actualservice = args[2];
            if (!displaylabel.equals("") && !Description.equals("")) {
                Constants.Initialize();
                JSONObject obj = new JSONObject();
                obj.put("Login", Constants.username);
                obj.put("Password", Constants.password);
                if (!actualservice.equals(""))
                    Constants.RegisteredForActualService = actualservice;
                obj = geneIncidentJSONForCreate(displaylabel, Description, Constants.IncidentModel, Constants.agent, Constants.RegisteredForActualService, Constants.servicegroup);
                log.debug(obj.toString());
                createTicket(obj);
            } else {
                log.debug("args length::0");
                System.out.println("args length::0");
            }
        }
        System.exit(1);


    }


    /***
     * 提交ACTION
     * @param obj
     * @return
     */
    public static String postAction(JSONObject obj) {

        String result = "";
        HttpClient httpClient = null;
        try {

            StringBuffer Token = new StringBuffer("");
            httpClient = HttpUtil.logon(Constants.endPoint, Constants.username, Constants.password, Token);
            if (httpClient != null) {

                if (!Token.toString().equals("")) {
                    log.info("Token::" + Token.toString());
                    log.info("requestUrl::" + Constants.requestUrl);

                    log.info(obj.toString());
                    result = HttpUtil.postJson(obj, Token.toString(), Constants.requestUrl, httpClient);
                    log.info("result::" + result);

                }
            }
        } catch (Exception ex) {
            log.error("****项目信息更新失败****" + ex.getMessage());
            result = ex.getMessage();
        } finally {
            if (httpClient != null) {
                log.debug("close Http Connection");
                httpClient.getConnectionManager().shutdown();
            }
        }

        log.debug("post result::" + result);
        return result;

    }


    /***
     * 创建工单
     * @param obj
     * @return
     */
    public static String createTicket(JSONObject obj) {

        String result = "";

        HttpClient httpClient = null;

        try {

            StringBuffer Token = new StringBuffer("");
            httpClient = HttpUtil.logon(Constants.endPoint, Constants.username, Constants.password, Token);
            //log.debug("...");
            if (httpClient != null) {
                // 关闭项目
                if (!Token.toString().equals("")) {

                    log.info("开始插入...");
                    log.info(obj.toString());
                    result = HttpUtil.postJson(obj, Token.toString(), Constants.requestUrl, httpClient);
                    log.info("result::" + result);
                }
            }
        } catch (Exception ex) {
            log.error("****项目信息更新失败****" + ex.getMessage());
            result = ex.getMessage();
        } finally {
            if (httpClient != null) {
                log.debug("close Http Connection");
                httpClient.getConnectionManager().shutdown();
            }
        }
        System.out.println("post result::" + result.toString());
        log.debug("post result::" + result);
        return result;

    }

    public static String queryTicket(String memberID, int type) {

        String result = "";

        HttpClient httpClient = null;


        try {
            log.debug("开始Query...1");
            // com.mf.integration.ComUtil.Initialize();
            log.debug("开始Query...2");
            //String Token="";
            StringBuffer Token = new StringBuffer("");
            httpClient = HttpUtil.logon(Constants.endPoint, Constants.username, Constants.password, Token);
            //log.debug("...");
            if (httpClient != null) {

                if (!Token.toString().equals("")) {
                    //List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();

                    log.debug("开始Query...");
                    //log.debug(obj.toString());
                    String url = "";
                    if (type == 1) {
                        // url = com.mf.integration.Constants.replaceAll("PERSONID", memberID);

                    } else if (type == 2) {
                        // url = com.mf.integration.Constants.getincidentUrl.replaceAll("PERSONID", memberID);


                    }
                    log.debug("开始Query..." + url);
                    result = HttpUtil.getJson(Token.toString(), url, httpClient);
                    log.debug("result::" + result);

                }


            }
        } catch (Exception ex) {
            log.error("****项目信息更新失败****" + ex.getMessage());
            result = ex.getMessage();
        } finally {
            if (httpClient != null) {
                log.debug("close Http Connection");
                httpClient.getConnectionManager().shutdown();
            }
        }

        log.debug("post result::" + result);
        return result;

    }



    /***
     * 查询特定工单详细信息
     * @param ticketID
     * @param ticketType，支持工单类型如REQUEST/INCIDENT
     * @return
     */
    public static String getTicket(String ticketID, String ticketType) {

        String result = "";

        HttpClient httpClient = null;


        try {

            StringBuffer Token = new StringBuffer("");
            httpClient = HttpUtil.logon(Constants.endPoint, Constants.username, Constants.password, Token);
            //log.debug("...");
            if (httpClient != null) {

                if (!Token.toString().equals("")) {


                    log.info("开始getTicket...");
                    //log.debug(obj.toString());
                    String url = "";
                    if (ticketType.equals("REQUEST")) {

                        log.info(Constants.therequestUrl);
                        url = com.mf.integration.Constants.therequestUrl.replace("{TICKETID}", ticketID);

                    } else if (ticketType.equals("INCIDENT")) {
                        log.info(Constants.theincidentUrl);
                        url = com.mf.integration.Constants.theincidentUrl.replace("{TICKETID}", ticketID);


                    }
                    log.info("Queryurl..." + url);
                    result = HttpUtil.getJson(Token.toString(), url, httpClient);
                    log.info("result::" + result);

                }


            }
        } catch (Exception ex) {
            log.error("****EXCEPTION****" + ex.getMessage());
            result = ex.getMessage();
        } finally {
            if (httpClient != null) {
                log.debug("close Http Connection");
                httpClient.getConnectionManager().shutdown();
            }
        }

        log.debug("post result::" + result);
        return result;

    }

    public static String getIncident(String ticketID, String ticketType) {

        String result = "";

        HttpClient httpClient = null;


        try {

            StringBuffer Token = new StringBuffer("");
            httpClient = HttpUtil.logon(Constants.endPoint, Constants.username, Constants.password, Token);
            //log.debug("...");
            if (httpClient != null) {

                if (!Token.toString().equals("")) {


                    log.info("开始getTicket...");
                    //log.debug(obj.toString());
                    String url = "";
                    if (ticketType.equals("REQUEST")) {

                        log.info(Constants.therequestUrl);
                        url = com.mf.integration.Constants.therequestUrl.replace("{TICKETID}", ticketID);

                    } else if (ticketType.equals("INCIDENT")) {
                        log.info(Constants.theincidentUrl);
                        url = com.mf.integration.Constants.theincidentUrl.replace("{TICKETID}", ticketID);


                    }
                    log.info("Queryurl..." + url);
                    result = HttpUtil.getJson(Token.toString(), url, httpClient);
                    log.info("result::" + result);

                }


            }
        } catch (Exception ex) {
            log.error("****EXCEPTION****" + ex.getMessage());
            result = ex.getMessage();
        } finally {
            if (httpClient != null) {
                log.debug("close Http Connection");
                httpClient.getConnectionManager().shutdown();
            }
        }

        log.debug("post result::" + result);
        return result;

    }

    /***
     * 获取人名或者组名
     * @param ID   特定的ID
     * @param type 1为人名，为组名
     * @return
     */
    public static String getPersonName(String ID, int type) {

        String result = "";
        String name = "";
        HttpClient httpClient = null;


        try {
            log.debug("开始Query...getPersonName");

            StringBuffer Token = new StringBuffer("");
            httpClient = HttpUtil.logon(Constants.endPoint, Constants.username, Constants.password, Token);
            //log.debug("...");
            if (httpClient != null) {

                if (!Token.toString().equals("")) {

                    String url = "";

                    if (type == 1)
                        url = com.mf.integration.Constants.personUrl.replaceAll("PERSONID", ID);
                    else if (type == 2)
                        url = com.mf.integration.Constants.groupUrl.replaceAll("GROUPID", ID);


                    log.debug("开始Query..." + url);
                    result = HttpUtil.getJson(Token.toString(), url, httpClient);

                    JSONObject resultJson = JSONObject.fromObject(result.toString());

                    JSONArray entities = resultJson.getJSONArray("entities");
                    JSONObject entity = entities.getJSONObject(0);


                    JSONObject properties = entity.getJSONObject("properties");
                    log.debug("properties::" + properties.toString());

                    name = properties.getString("Name");

                    log.debug("result::" + result);

                }


            }
        } catch (Exception ex) {
            log.error("****EXCEPTION****" + ex.getMessage());
            result = ex.getMessage();
        } finally {
            if (httpClient != null) {
                log.debug("close Http Connection");
                httpClient.getConnectionManager().shutdown();
            }
        }


        return name;

    }

    /**
     * 获取登录Token
     *
     * @param logonurl
     * @param obj
     * @return
     */
    public static String getToken(String logonurl) {

        String result = "";

        HttpClient httpClient = null;
        try {
            //String Token="";
            StringBuffer Token = new StringBuffer("");
            log.debug("****logonurl****" + logonurl);
            httpClient = HttpUtil.logon(Constants.endPoint, Constants.username, Constants.password, Token);
            //log.debug("...");
            log.debug("****Logontoken****" + Token.toString());
            if (httpClient != null) {

                if (!Token.toString().equals("")) {

                    result = Token.toString();

                }
            }
        } catch (Exception ex) {
            result = ex.getMessage();
        } finally {
            if (httpClient != null) {
                log.debug("close Http Connection");
                httpClient.getConnectionManager().shutdown();
            }
        }

        log.debug("post result::" + result);
        return result;

    }

    public static JSONObject geneRequestJSON(String displaylabel, String Description, String person) {


        HashMap<String, String> map = new HashMap<String, String>();

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("DisplayLabel", displaylabel);
        properties.put("Description", Description);
        //请求人
        //请求者
        if (!person.equals(""))
            properties.put("RequestedByPerson", person);
        else
            properties.put("RequestedByPerson", Constants.agent);

        HashMap<String, Object> entity = new HashMap<String, Object>();

        entity.put("entity_type", "Request");
        entity.put("properties", properties);

        //HashMap<String, Object> entities = new HashMap<String, Object>();

        // entities.put("", value);
//	        //HashMap<String, String> map = new HashMap<String, String>();

        //JSONObject json = JSONObject.fromObject(map);
        ArrayList<HashMap<String, Object>> b = new ArrayList<HashMap<String, Object>>();

        b.add(entity);


        JSONObject obj0 = new JSONObject();

        obj0.put("entities", b);
        obj0.put("operation", "CREATE");
        //调用toString()方法可直接将其内容打印出来
        //System.out.println(obj0.toString());

        return obj0;
    }

    public static JSONObject geneIncidentJSONForCreate(String displaylabel, String description, String incidentmodel, String person, String actualservice, String servicegroup) {


        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("DisplayLabel", displaylabel);
        properties.put("Description", description);
        properties.put("EntityModel", incidentmodel);
        properties.put("ImpactScope", "SingleUser");


        properties.put("PhaseId", "Classify");
        properties.put("FirstTouch", "true");
        properties.put("Urgency", "SlightDisruption");

        //实际服务的水平
        properties.put("ServiceDeskGroup", servicegroup);
        //properties.put("SLT", "10786");

        //请求者
        properties.put("RequestedByPerson", person);

        //properties.put("CompletionCode", "null");
        properties.put("ProcessId", "normal");

        //实际服务的水平
        properties.put("RegisteredForActualService", actualservice);


        HashMap<String, Object> entity = new HashMap<String, Object>();

        entity.put("entity_type", "Incident");
        entity.put("properties", properties);

        //HashMap<String, Object> entities = new HashMap<String, Object>();

        // entities.put("", value);
//	        //HashMap<String, String> map = new HashMap<String, String>();

        //JSONObject json = JSONObject.fromObject(map);
        ArrayList<HashMap<String, Object>> b = new ArrayList<HashMap<String, Object>>();

        b.add(entity);


        JSONObject obj0 = new JSONObject();

        obj0.put("entities", b);
        obj0.put("operation", "CREATE");
        //调用toString()方法可直接将其内容打印出来


        return obj0;
    }

    public static JSONObject geneIncidentJSONForClose(String ID, String Solution, boolean ProblemCandidate) {


        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("Id", ID);
        properties.put("PhaseId", "Close");
        properties.put("Solution", Solution);

        if (ProblemCandidate)
            properties.put("Solution", "true");
        else
            properties.put("Solution", "false");

        properties.put("CompletionCode", "Resolvedbyfix");

        HashMap<String, Object> entity = new HashMap<String, Object>();

        entity.put("entity_type", "Incident");
        entity.put("properties", properties);

        ArrayList<HashMap<String, Object>> b = new ArrayList<HashMap<String, Object>>();
        b.add(entity);


        JSONObject obj0 = new JSONObject();

        obj0.put("entities", b);
        obj0.put("operation", "UPDATE");

        return obj0;
    }


}
