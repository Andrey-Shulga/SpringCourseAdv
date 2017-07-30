package beans.controller;

import beans.exception.NotEnoughMoneyException;
import beans.models.*;
import beans.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import util.Converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static util.Converter.getStrToLocalDateTime;
import static util.Converter.parseSeatsList;

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

    @Autowired
    private UserAccountService userAccountService;

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
        List<Integer> intSeats = Converter.parseSeatsList(seats);
        String email = requestParams.get("email");

        Auditorium auditorium = auditoriumService.getByName(audName);
        Event event = eventService.getEvent(eventName, auditorium, dateTime);
        User user = userService.getUserByEmail(email);

        double price = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), dateTime, intSeats, user);
        map.put("price", price);

        return "price";
    }

    @RequestMapping(value = "/bookTicket", method = RequestMethod.POST)
    public ModelAndView bookTicket(@RequestParam Map<String, String> requestParams, ModelMap map) throws NotEnoughMoneyException {

        try {
            String eventName = requestParams.get("eventName");
            String audName = requestParams.get("audName");
            String date = requestParams.get("date");
            LocalDateTime dateTime = Converter.getStrToLocalDateTime(date);
            String seats = requestParams.get("seats");
            List<Integer> intSeats = parseSeatsList(seats);
            String email = requestParams.get("email");

            Auditorium auditorium = auditoriumService.getByName(audName);
            Event event = eventService.getEvent(eventName, auditorium, dateTime);
            User user = userService.getUserByEmail(email);

            double price = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), dateTime, intSeats, user);
            UserAccount userAccount = user.getUserAccount();
            if (price > userAccount.getMoney())
                throw new NotEnoughMoneyException("You have not enough money to buy this ticket. Please fill you account.");
            userAccount.setMoney(userAccount.getMoney() - price);

            Ticket ticket = new Ticket(event, LocalDateTime.now(), intSeats, user, price);
            Ticket bookedTicked = bookingService.bookTicket(ticket.getUser(), ticket);

            if (bookedTicked != null) {
                userAccountService.saveMoney(userAccount);
                map.put("result", " Success! You booked ticket on that event.");
            }
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
        LocalDateTime dateTime = Converter.getStrToLocalDateTime(date);

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




}
