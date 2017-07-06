package beans.controller;

import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class FreemarkerController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/freemarker", method = RequestMethod.GET)
    public String freemarker() {

        return "template";
    }

    @RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
    public String getUserByEmail(@RequestParam Map<String, String> requestParams, ModelMap map) {

        String email = requestParams.get("email");
        User user = userService.getUserByEmail(email);
        map.addAttribute("user", user);
        return "template";
    }

    @RequestMapping(value = "/getUsersWithName", method = RequestMethod.GET)
    public String getUsersWithName(@RequestParam Map<String, String> requestParams, ModelMap map) {

        String name = requestParams.get("name");
        List<User> userList = userService.getUsersByName(name);
        map.addAttribute("userList", userList);
        return "template";
    }
}
