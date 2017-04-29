package util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MVCExceptionHandler implements HandlerExceptionResolver {  
	
	public MVCExceptionHandler(){
		int a = 1 ;
	}

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {  
//        Map<String, Object> model = new HashMap<String, Object>();  
//        model.put("ex", ex);  
        ex.printStackTrace();
        ModelAndView view = new ModelAndView("/common/error");
        view.addObject("ex", ex);
        return view;
    }  
}  