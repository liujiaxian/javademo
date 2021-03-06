package util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/*
 * 在Struts中  拦截器的上下文是通过, ActionContext来获取的（Spring的配置文件就在Context中）
 * MVC中,拦截器的上下文与过滤器相同, 吧request作为形参传入， 设置完毕后要手动配置拦截器
 *
 * */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("-------在action之前执行,如果返回true,则继续向后执行--------");
//        System.out.println(request.getParameter("name"));
        // 此处实现登陆的拦截判断
        if(request.getSession().getAttribute("loginobject")==null){
            response.sendRedirect(request.getContextPath() + "/account/login");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}