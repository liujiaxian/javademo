package controller;

import Po.ReturnMsg;
import org.beetl.json.JsonTool;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class BaseController {
    public static String JsonReturn(Integer status,String msg,Object data){
        JsonTool tool = new JsonTool();

        ReturnMsg object = new ReturnMsg();
        object.status = status;
        object.msg = msg;
        object.data = tool.serialize(data);

        String json = tool.serialize(object);

        return json;
    }

    public enum Enum_Type {
         成功,失败,验证码错误
    }
}