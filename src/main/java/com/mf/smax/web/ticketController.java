package com.mf.smax.web;

import com.mf.integration.ComUtil;
import com.mf.integration.Constants;
import com.mf.integration.HttpUtil;
import com.mf.smax.entity.Ticket;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

//import com.mf.dao.Project;
@RequestMapping("/api")
public class smaxController {
    private static Logger log = Logger.getLogger(smaxController.class);

    @Value("${smax.RegisteredForActualService}")
    private String RegisteredForActualService;

    @Value("${smax.agent}")
    private String agent;
    @Value("${smax.username}")
    private String username;
    @Value("${smax.password}")
    private String password;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createIncident(Ticket ticket) {
        log.debug("begin.....");
        log.debug(this.agent);
        log.debug(ticket.getDisplaylabel());
        String result = "";
        String displaylabel = ticket.getDisplaylabel();
        String Description = ticket.getDescription();
        String actualservice = ticket.getActualservice();
        if (!displaylabel.equals("") && !Description.equals("")) {
            ComUtil.Initialize();
            JSONObject obj = new JSONObject();
            obj.put("Login", this.username);
            obj.put("Password", this.password);
            if (!actualservice.equals(""))
                this.RegisteredForActualService = actualservice;
            obj = geneIncident(displaylabel, Description, this.agent, this.RegisteredForActualService);
            log.debug(obj.toString());
            result = createTicket(obj);
        } else {
            log.debug("args length::0");
            System.out.println("args length::0");
        }


        return result;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        if (args.length > 2) {
            //log.debug((new StringBuilder("args length::")).append(args.length).toString());

            String displaylabel = args[0];
            String Description = args[1];
            String actualservice = args[2];
            if (!displaylabel.equals("") && !Description.equals("")) {
                ComUtil.Initialize();
                JSONObject obj = new JSONObject();
                obj.put("Login", Constants.username);
                obj.put("Password", Constants.password);
                if (!actualservice.equals(""))
                    Constants.RegisteredForActualService = actualservice;
                obj = geneIncident(displaylabel, Description, Constants.agent, Constants.RegisteredForActualService);
                log.debug(obj.toString());
                createTicket(obj);
            } else {
                log.debug("args length::0");
                System.out.println("args length::0");
            }
        }
        System.exit(1);


    }


    public static String postAction(JSONObject obj) {

        String result = "";
        HttpClient httpClient = null;
        try {

            StringBuffer Token = new StringBuffer("");
            httpClient = HttpUtil.logon(Constants.endPoint, Token);
            if (httpClient != null) {

                if (!Token.toString().equals("")) {
                    //List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
                    System.out.println("Token::" + Token.toString());
                    log.debug("开始插入...");
                    log.debug(obj.toString());
                    result = HttpUtil.postJson(obj, Token.toString(), Constants.requestUrl, httpClient);

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
        System.out.println("post result::" + result.toString());
        log.debug("post result::" + result);
        return result;

    }


    public static String createTicket(JSONObject obj) {

        String result = "";

        HttpClient httpClient = null;

        try {
            // com.mf.integration.ComUtil.Initialize();
            //String Token="";
            StringBuffer Token = new StringBuffer("");
            httpClient = HttpUtil.logon(Constants.endPoint, Token);
            //log.debug("...");
            if (httpClient != null) {
                // 关闭项目
                if (!Token.toString().equals("")) {
                    //List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
                    System.out.println("Token::" + Token.toString());
                    log.debug("开始插入...");
                    log.debug(obj.toString());
                    result = HttpUtil.postJson(obj, Token.toString(), Constants.requestUrl, httpClient);

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
            httpClient = HttpUtil.logon(Constants.endPoint, Token);
            //log.debug("...");
            if (httpClient != null) {

                if (!Token.toString().equals("")) {
                    //List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();

                    log.debug("开始Query...");
                    //log.debug(obj.toString());
                    String url = "";
                    if (type == 1) {
                        url = Constants.getrequestUrl.replaceAll("PERSONID", memberID);

                    } else if (type == 2) {
                        url = Constants.getincidentUrl.replaceAll("PERSONID", memberID);
                        //result=HttpUtil.getJson(Token.toString(), url, httpClient);

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

    public static String getTicket(String ticketID, int type) {

        String result = "";

        HttpClient httpClient = null;


        try {
            // log.debug("开始Query...1");
            ComUtil.Initialize();
            //log.debug("开始Query...2");
            //String Token="";
            StringBuffer Token = new StringBuffer("");
            httpClient = HttpUtil.logon(Constants.endPoint, Token);
            //log.debug("...");
            if (httpClient != null) {

                if (!Token.toString().equals("")) {
                    //List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();

                    log.debug("开始Query...");
                    //log.debug(obj.toString());
                    String url = "";
                    if (type == 1) {
                        url = Constants.therequestUrl.replaceAll("TICKETID", ticketID);

                    } else if (type == 2) {
                        url = Constants.theincidentUrl.replaceAll("TICKETID", ticketID);
                        //result=HttpUtil.getJson(Token.toString(), url, httpClient);

                    }
                    log.debug("开始Query..." + url);
                    result = HttpUtil.getJson(Token.toString(), url, httpClient);
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

        log.debug("post result::" + result);
        return result;

    }

    public static String getName(String ID, int type) {

        String result = "";
        String name = "";
        HttpClient httpClient = null;


        try {
            // log.debug("开始Query...1");
            ComUtil.Initialize();
            //log.debug("开始Query...2");
            //String Token="";
            StringBuffer Token = new StringBuffer("");
            httpClient = HttpUtil.logon(Constants.endPoint, Token);
            //log.debug("...");
            if (httpClient != null) {

                if (!Token.toString().equals("")) {

                    String url = "";

                    if (type == 1)
                        url = Constants.personUrl.replaceAll("PERSONID", ID);
                    else if (type == 2)
                        url = Constants.groupUrl.replaceAll("GROUPID", ID);


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
            httpClient = HttpUtil.logon(logonurl, Token);
            //log.debug("...");
            log.debug("****Logontoken****" + Token.toString());
            if (httpClient != null) {

                // "_项目管理.Project"));
                if (!Token.toString().equals("")) {
                    //List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();

                    result = Token.toString();

                }


                //HttpUtil.logout(httpClient) ;

            }
        } catch (Exception ex) {
            log.error("****创建失败****" + ex.getMessage());
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

    public static JSONObject geneRequest(String displaylabel, String Description, String person) {


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

    public static JSONObject geneIncident(String displaylabel, String Description, String person, String actualservice) {


        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("DisplayLabel", displaylabel);
        properties.put("Description", Description);
        properties.put("ImpactScope", "SingleUser");


        properties.put("PhaseId", "Classify");
        properties.put("FirstTouch", "true");
        properties.put("Urgency", "SlightDisruption");

        //实际服务的水平
        // properties.put("ServiceDeskGroup", "10786");
        properties.put("ServiceDeskGroup", Constants.servicegroup);
        properties.put("Priority", "LowPriority");
        properties.put("SLT", "10786");

        //请求者
        if (!person.equals(""))
            properties.put("RequestedByPerson", person);
        else
            properties.put("RequestedByPerson", Constants.agent);

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
        //System.out.println(obj0.toString());

        return obj0;
    }


}
