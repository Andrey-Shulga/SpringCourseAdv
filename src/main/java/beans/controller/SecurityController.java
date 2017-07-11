package beans.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public ModelAndView login() {

        return new ModelAndView("login");
    }


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied() {

        return new ModelAndView("access-denied-403");
    }

}