package com.mf.smax.entity;


import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Alert implements Serializable {

	private static final long serialVersionUID = 1L;

	private String commonAnnotations = "";
	private String commonLabels =  "";
	private String status =  "";

//	{

	public String getCommonAnnotations() {
		return commonAnnotations;
	}

	public void setCommonAnnotations(String commonAnnotations) {
		this.commonAnnotations = commonAnnotations;
	}

	public String getCommonLabels() {
		return commonLabels;
	}

	public void setCommonLabels(String commonLabels) {
		this.commonLabels = commonLabels;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
//		'receiver': 'webhook',
//			'status': 'firing',
//			'alerts': [
//		{'status': 'firing',
//				'labels': {'alertname': 'InstanceDown',
//				'instance': '192.168.111.12:9100',
//				'job': 'consul_111_83',
//				'severity': 'critical'},
//			'annotations':
//			{'description': 'Host 192.168.111.12:9100 Down',
//					'summary': 'Host 192.168.111.12:9100 of consul_111_83 is  Down !!!'},
//			'startsAt': '2020-07-22T08:41:22.633075734Z',
//				'endsAt': '0001-01-01T00:00:00Z',
//				'generatorURL': 'http://prometheus.server:9090/graph?g0.expr=up%7Binstance%3D~%2210%7C80%7C192.%2A%22%7D+%3D%3D+0&g0.tab=1',
//				'fingerprint': 'c33ac30fed829472'}],
//		'groupLabels': {'instance': '192.168.111.12:9100'},
//		'commonLabels': {'alertname': 'InstanceDown',
//			'instance': '192.168.111.12:9100',
//			'job': 'consul_111_83', 'severity': 'critical'},
//		'commonAnnotations':
//		{'description': 'Host 192.168.111.12:9100 Down',
//				'summary': 'Host 192.168.111.12:9100 of consul_111_83 is  Down !!!'},
//		'externalURL': 'http://prometheus.server:9093',
//			'version': '4',
//			'groupKey': '{}:{instance="192.168.111.12:9100"}',
//			'truncatedAlerts': 0
//	}
}
