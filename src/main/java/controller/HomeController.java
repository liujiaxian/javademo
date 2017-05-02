package controller;

import Dao.UserDao;
import Po.UserPo;
import Server.UserServer;
import com.sun.deploy.net.HttpResponse;
import org.beetl.json.JsonTool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController {

    public static   ApplicationContext context  =  new ClassPathXmlApplicationContext("../../WEB-INF/applicationContext.xml");
    public  static UserServer server = (UserServer)context.getBean("userServer");

    @RequestMapping
    public ModelAndView main(HttpSession session,HttpServletRequest request){
        ModelAndView view = new ModelAndView("main");

        String name = session.getAttribute("loginobject").toString();

        request.setAttribute("username",name);

        return view;
    }


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(ModelMap model){
        ModelAndView view = new ModelAndView("index");
        //当前系统名称
        String OS_NAME = System.getProperty("os.name").toLowerCase(Locale.US);
        model.addAttribute("sysname",OS_NAME);
        //当前系统的位数X86?X64
        String OS_ARCH = System.getProperty("os.arch").toLowerCase(Locale.US);
        model.addAttribute("sysarch",OS_ARCH);
        //获取当前系统的版本信息
        String OS_VERSION = System.getProperty("os.version").toLowerCase(Locale.US);
        model.addAttribute("sysversion",OS_VERSION);
        return view;
    }
    private UserDao userdao;
    @RequestMapping(value = "/userinfo",method = RequestMethod.GET)
    public ModelAndView userinfo(ModelMap model){
        ModelAndView view = new ModelAndView("userinfo");
        List<UserPo> userlist = server.findAll();

        model.addAttribute("userlist",userlist);

        return view;
    }

    @RequestMapping(value = "/userinfosearch",method = RequestMethod.GET)
    public ModelAndView userinfosearch(ModelMap model,HttpServletRequest req){
        String username = req.getParameter("username");


        ModelAndView view = new ModelAndView("userinfo");
        List<UserPo> userlist = server.findUserName(username);

        model.addAttribute("userlist",userlist);

        return view;
    }

    @RequestMapping(value = "/useradd",method = RequestMethod.GET)
    public ModelAndView useradd(){
        ModelAndView view = new ModelAndView("useradd");
        return view;
    }

    @RequestMapping(value = "/useradd",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String useradd(HttpServletRequest request){
        String username = request.getParameter("username");
        if(username==null||username==""){
            return JsonReturn(Enum_Type.失败.ordinal(),"用户名称不能为空！",null);
        }
        String userpwd = request.getParameter("userpwd");
        if(userpwd==null||userpwd==""){
            return JsonReturn(Enum_Type.失败.ordinal(),"用户密码不能为空！",null);
        }

        UserPo user = new UserPo();
        user.userName = username;
        user.userPwd = userpwd;

        server.addUser(user);

        return JsonReturn(Enum_Type.成功.ordinal(),"用户添加成功！",null);
    }

    @RequestMapping(value = "/useredit/{userid}",method = RequestMethod.GET)
    public ModelAndView useredit(@PathVariable("userid") int userid,ModelMap model){
        ModelAndView view = new ModelAndView("useredit");

        UserPo user = server.searchUser(userid);

        model.addAttribute("user",user);

        return view;
    }

    @RequestMapping(value = "/useredit",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String useredit(HttpServletRequest request){
        Integer userID = Integer.valueOf(request.getParameter("userid"));
        UserPo user = server.searchUser(userID);
        if (user==null){
            return JsonReturn(Enum_Type.失败.ordinal(),"该数据不存在或已被删除！",null);
        }

        String username = request.getParameter("username");
        if(username==null||username==""){
            return JsonReturn(Enum_Type.失败.ordinal(),"用户名称不能为空！",null);
        }
        String userpwd = request.getParameter("userpwd");
        if(userpwd==null||userpwd==""){
            return JsonReturn(Enum_Type.失败.ordinal(),"用户密码不能为空！",null);
        }

        user.userName = username;
        user.userPwd = userpwd;


        server.updateUser(user);


        return JsonReturn(Enum_Type.成功.ordinal(),"用户更新成功！",null);
    }

    @RequestMapping(value = "/userdelete",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String userdelete(HttpServletRequest request){
        Integer userID = Integer.valueOf(request.getParameter("userid"));
        UserPo user = server.searchUser(userID);
        if (user==null){
            return JsonReturn(Enum_Type.失败.ordinal(),"该用户不存在或已被删除！",null);
        }

        server.deleteUser(userID);
        return JsonReturn(Enum_Type.成功.ordinal(),"用户删除成功！",null);
    }

    @RequestMapping(value = "/account/layout",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String layout(HttpSession session){
        Object name = session.getAttribute("loginobject");
        if(name!=null){
            session.removeAttribute("loginobject");
        }
        return JsonReturn(Enum_Type.成功.ordinal(),"用户退出成功！",null);
    }

    @RequestMapping(value = "2",method = RequestMethod.GET)
    public ModelAndView tindex(){
        int i = 12/0;
        return new ModelAndView("index");
    }
}
