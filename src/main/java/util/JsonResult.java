package util;

import java.util.HashMap;
import java.util.Map;

import org.beetl.json.JsonTool;

public class JsonResult {
	
	static JsonTool jsonTool = new JsonTool();
	static{
		jsonTool.addLocationAction("~d","f/yyyy.MM.dd/");
	}
	private boolean success = true;
	private Map data = new HashMap();
	private Object obj = null;
	
	
	
	public JsonResult(boolean success,Object obj){
		this.success = success;
		this.obj = obj;
	}
	
	public JsonResult(Object obj){
		this.success = true;
		this.obj = obj;
	}
	
	public JsonResult(){
		this.success = true;
		this.obj = null;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String json(){
		data.put("success", success);
		data.put("data", obj);
		return jsonTool.serialize(data);
	}
	
	
}
