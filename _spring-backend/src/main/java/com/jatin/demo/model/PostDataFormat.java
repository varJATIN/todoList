package com.jatin.demo.model;

import org.json.simple.JSONObject;

public class PostDataFormat {
	
	JSONObject data;
//	JSONObject attributes;
//	String type;
	
	
	public JSONObject getData() {
		return data;
	}
	public void setData(JSONObject data) {
		this.data = data;
	}
//	public JSONObject getAttributes() {
//		return attributes;
//	}
//	public void setAttributes(JSONObject attributes) {
//		this.attributes = attributes;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
	@Override
	public String toString() {
		return "PostDataFormat [data=" + data + "]";
	}

}
