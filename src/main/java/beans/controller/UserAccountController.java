package beans.controller;

import beans.models.User;
import beans.models.UserAccount;
import beans.services.UserAccountService;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class UserAccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "money", method = RequestMethod.GET)
    public String showMoneyForm(ModelMap map) {

        return "money";
    }

    @RequestMapping(value = "money", method = RequestMethod.POST)
    public ModelAndView addMoney(@RequestParam Map<String, String> requestParams, ModelMap map) {

        try {
            String email = requestParams.get("email");
            String moneyStr = requestParams.get("money");
            double money = Double.parseDouble(moneyStr);

            User user = userService.getUserByEmail(email);
            UserAccount userAccount = user.getUserAccount();
            userAccount.addMoney(money);
            userAccountService.saveMoney(userAccount);
            map.put("result", "Success! " + money + "$ was added in user account");
        } catch (Exception e) {
            String result = " Fail! " + e.getMessage();
            map.put("resultMoney", result);
        }
        return new ModelAndView("money");
    }
}
