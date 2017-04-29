package controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import util.CaptchaHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import Po.UserPo;
import Server.UserServer;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("/account/login");
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request, HttpSession session){

        String username = request.getParameter("username");
        if(username==null||username==""){
            return "error";
        }
        String userpwd = request.getParameter("userpwd");
        if(userpwd==null||userpwd==""){
            return "error";
        }
        String verify = request.getParameter("verify").toLowerCase();
        if(verify==null||verify==""){
            return "error";
        }

        String vcode = session.getAttribute("code").toString();

        if (!verify.equals(vcode)){
            return "error1";
        }

        ApplicationContext context  =  new ClassPathXmlApplicationContext("../../WEB-INF/applicationContext.xml");
        UserServer server = (UserServer)context.getBean("userServer");

        UserPo user = server.VerifyUser(username,userpwd);
        if(user==null){
            return "error";
        }

        session.setAttribute("loginobject",user);

        return "ok";
    }

    /**
     * 获取验证码
     * @param req
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping(value = "verify",method = RequestMethod.GET)
    public void verify(HttpServletRequest req, HttpServletResponse response, HttpSession session) throws IOException{
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);


        CaptchaHelper vCode = new CaptchaHelper(100,30,5,10);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
    }
}
