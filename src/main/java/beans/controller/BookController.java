package beans.controller;

import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.AuditoriumService;
import beans.services.BookingService;
import beans.services.EventService;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("result")
public class BookController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;

    @Autowired
    @Qualifier("bookingServiceImpl")
    private BookingService bookingService;

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

    @RequestMapping(value = "/price", method = RequestMethod.GET)
    public String getTicketPrice(@RequestParam Map<String, String> requestParams, ModelMap map) {

        String eventName = requestParams.get("eventName");
        String audName = requestParams.get("audName");
        String date = requestParams.get("date");
        LocalDateTime dateTime = getStrToLocalDateTime(date);
        String seats = requestParams.get("seats");
        List<Integer> intSeats = parseSeatsList(seats);
        String email = requestParams.get("email");

        Auditorium auditorium = auditoriumService.getByName(audName);
        Event event = eventService.getEvent(eventName, auditorium, dateTime);
        User user = userService.getUserByEmail(email);

        double price = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), dateTime, intSeats, user);
        map.put("price", price);

        return "price";
    }

    @RequestMapping(value = "/bookTicket", method = RequestMethod.POST)
    public ModelAndView bookTicket(@RequestParam Map<String, String> requestParams, ModelMap map) {

        String eventName = requestParams.get("eventName");
        String audName = requestParams.get("audName");
        String date = requestParams.get("date");
        LocalDateTime dateTime = getStrToLocalDateTime(date);
        String seats = requestParams.get("seats");
        List<Integer> intSeats = parseSeatsList(seats);
        String email = requestParams.get("email");

        Auditorium auditorium = auditoriumService.getByName(audName);
        Event event = eventService.getEvent(eventName, auditorium, dateTime);
        User user = userService.getUserByEmail(email);

        double price = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), dateTime, intSeats, user);

        Ticket ticket = new Ticket(event, LocalDateTime.now(), intSeats, user, price);
        try {
            Ticket bookedTicked = bookingService.bookTicket(ticket.getUser(), ticket);
            if (bookedTicked != null)
                map.put("result", " Success!");
        } catch (Exception e) {
            String result = " Fail! " + e.getMessage();
            map.put("result", result);
        }

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/ticketList", method = RequestMethod.GET)
    public String ticketList(@RequestParam Map<String, String> requestParams, ModelMap map) {

        String eventName = requestParams.get("eventName");
        String audName = requestParams.get("audName");
        String date = requestParams.get("date");
        LocalDateTime dateTime = getStrToLocalDateTime(date);

        List<Ticket> ticketList = bookingService.getTicketsForEvent(eventName, audName, dateTime);
        map.put("ticketList", ticketList);

        return "tickets-list";
    }

    @RequestMapping(value = "/getTicketList", method = RequestMethod.GET)
    public String getTicketList(@RequestParam Map<String, String> requestParams, ModelMap map) {

        List<Ticket> ticketList = bookingService.getAllTickets();
        map.put("ticketList", ticketList);

        return "tickets-list";
    }

    private List<Integer> parseSeatsList(String seats) {

        String regexCommaPattern = "\\s*,\\s*";
        List<String> strSeats = Arrays.asList(seats.split(regexCommaPattern));
        return strSeats.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    private LocalDateTime getStrToLocalDateTime(String strDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(strDate, formatter);
    }
}
