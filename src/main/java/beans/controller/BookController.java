package beans.controller;

import beans.models.Auditorium;
import beans.models.Event;
import beans.models.User;
import beans.services.AuditoriumService;
import beans.services.EventService;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;

    @RequestMapping("")
    String index(ModelMap map) {

        List<User> userList = userService.getUsers();
        List<Auditorium> auditoriumList = auditoriumService.getAuditoriums();
        List<Event> eventList = eventService.getAll();
        map.addAttribute("userList", userList);
        map.addAttribute("auditoriumList", auditoriumList);
        map.addAttribute("eventList", eventList);
        return "book";
    }
}
