package com.mf.smax.web;


import com.mf.integration.Constants;

import com.mf.integration.SMACall;

import com.mf.smax.entity.QueryTicket;
import com.mf.smax.entity.Ticket;
import io.swagger.annotations.*;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Api
@RestController
public class ticketController {
    private static Logger log = LoggerFactory.getLogger(ticketController.class);


    /***
     * 通用开单
     * @param ticket
     * @return
     */
    @PostMapping(value = "/incident/create")
    public String createIncident(@RequestBody Ticket ticket) {
        log.info("begin TO createIncident...");
        log.info(Constants.endPoint);
        log.info(ticket.getDisplaylabel());
        String result = "";
        String displaylabel = ticket.getDisplaylabel();
        String Description = ticket.getDescription();
        String actualservice = ticket.getActualservice();
        if (!displaylabel.equals("") && !Description.equals("")) {

            if (!actualservice.equals(""))
                Constants.RegisteredForActualService = actualservice;
            JSONObject obj = SMACall.geneIncidentJSONForCreate(displaylabel, Description, Constants.IncidentModel, Constants.agent, Constants.RegisteredForActualService, Constants.servicegroup);
            System.out.println(obj.toString());
            result = SMACall.postAction(obj);
        }

        log.info(result);
        return result;
    }


    /***
     * 针对ALERTMANAGET的事件开单（目前只支持事件开单，不支持关单）
     * @param alert
     * @return
     */
    @PostMapping(value = "/incident/alertmanager")
    public String createIncidentByAlertManager(@RequestBody Map<String, Object> alert) {

        log.info("createIncidentByAlertManager.....");
        String result = "";
        String displaylabel = "";
        String description = "";
        String actualservice = "";
        JSONObject jsonAlert = JSONObject.fromObject(alert);
        log.info("jsonAlert：" + jsonAlert.toString());
        String status = jsonAlert.getString("status");
        JSONObject commonLabels = jsonAlert.getJSONObject("commonLabels");

        log.info(commonLabels.getString("instance"));
        actualservice = commonLabels.getString("instance");
        log.info("status---" + status);

        for (Map.Entry<String, Object> entry : alert.entrySet()) {


            if (entry.getKey().equals("commonLabels")) {
                displaylabel = entry.getValue().toString().replace("{", "").replace("}", "");
            }

            if (entry.getKey().equals("commonAnnotations")) {
                description = entry.getValue().toString().replace("{", "").replace("}", "");
            }
        }
        if (!displaylabel.equals("") && !description.equals("")) {
            if (!Constants.RegisteredForActualService.equals(""))
                actualservice = Constants.RegisteredForActualService;

            //添加description标志位
            description = description + "<p>-----------------</p> <p>Created By AlertManager</p>";
            JSONObject obj = SMACall.geneIncidentJSONForCreate(displaylabel, description, Constants.IncidentModel, Constants.agent, actualservice, Constants.servicegroup);
            log.info("jsonIncident：" + obj.toString());
            result = SMACall.postAction(obj);
        }

        log.info("result：" + result);
        return result;
    }


    /***
     * 查询特定工单详细信息
     * @param ticket
     * @return
     */
    @PostMapping(value = "/ticket/retrieve")
    public String getTicketByID(@RequestBody QueryTicket queryTicket) {

        log.info("getTicketByID.....");
        String result = "";

        String ticketID = queryTicket.getTicketID();
        String ticketType = queryTicket.getTicketType();

        if (!ticketID.equals("") && !ticketType.equals("")) {
            result = SMACall.getTicket(ticketID, ticketType);
        }

        log.info("getTicketByID result：" + result);
        return result;
    }


}
