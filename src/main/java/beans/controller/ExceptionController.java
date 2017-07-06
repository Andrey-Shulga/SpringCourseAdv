package beans.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView errorHandler(HttpServletRequest req, Exception e) throws Exception {


        ModelAndView map = new ModelAndView("error");
        map.addObject("exception", e);
        map.addObject("url", req.getRequestURL());

        return map;
    }
}
