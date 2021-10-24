package com.mf.smax.test;

import java.io.UnsupportedEncodingException;


import com.mf.integration.HttpUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class servicetest {

	private static Logger log = LoggerFactory.getLogger(servicetest.class);

	public static void main(String[] args) throws UnsupportedEncodingException {

//		String url = "http://inst2.abc.net:8000/SMAX-Restful/incident/alertmanager";
//		String jsonSTR = "{\"receiver\":\"mail\",\"status\":\"firing\",\"alerts\":[{\"status\":\"firing\",\"labels\":" +
//				"{\"alertname\":\"UATTESTING\",\"instance\":\"172.21.16.11:9100\",\"job\":\"node\",\"severity\":" +
//				"\"warning\"},\"annotations\":{\"description\":\"172.21.16.11:9100: " +
//				"Memory usage is above 30 (current value is:62.81925065332021)\",\"summary\":\"172.21.16.11:9100: High Memory usage detected\"}," +
//				"\"startsAt\":\"2021-10-22T13:51:12.445Z\",\"endsAt\":\"0001-01-01T00:00:00Z\",\"generatorURL\":\"http://inst2.infoclue.net:9090/graph?g0.expr=%281+-+%28node_memory_MemAvailable_bytes+%2F+%28node_memory_MemTotal_bytes%29%29%29+%2A+100+%3E+2&g0.tab=1\",\"fingerprint\":\"a6f3517d1f9bf0d1\"}],\"groupLabels\":{\"alertname\":\"NodeMemoryUsage\"}," +
//				"\"commonLabels\":{\"alertname\":\"UATTESTING\",\"instance\":\"172.21.16.11:9100\",\"job\":\"node\",\"severity\":\"warning\"},\"commonAnnotations\":{\"description\":\"172.21.16.11:9100: Memory usage is above 30 (current value is:62.81925065332021)\",\"summary\":\"172.21.16.11:9100: High Memory usage detected\"},\"externalURL\":\"http://inst2.infoclue.net:9093\",\"version\":\"4\",\"groupKey\":\"{}:{alertname=\\\"UATTESTING\\\"}\",\"truncatedAlerts\":0}";

//		HttpUtil.postJSONRequest(url, jsonSTR);

		String url = "http://inst2.abc.net:8000/SMAX-Restful/ticket/retrieve";
		String jsonSTR = "{\"ticketID\":\"84490\",\"ticketType\":\"INCIDENT\"}";

		HttpUtil.postJSONRequest(url, jsonSTR);


	}
	
	

}
