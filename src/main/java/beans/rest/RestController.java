package beans.rest;

import beans.exception.NotEnoughMoneyException;
import beans.models.*;
import beans.rest.json.PriceJson;
import beans.rest.json.RequestTicketJson;
import beans.services.AuditoriumService;
import beans.services.BookingService;
import beans.services.EventService;
import beans.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.Converter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api")
public class RestController {

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;

    @RequestMapping(value = "/price/{eventName}/{auditoriumName}/{dateTime}/{email}/{seatsList}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTicketPrice(@PathVariable String eventName, @PathVariable String auditoriumName, @PathVariable String dateTime, @PathVariable String email, @PathVariable String seatsList, HttpServletResponse response) throws IOException {

        String query = "http://localhost:8080/api/price/Great%20Show/Blue%20Hall/2017-02-12T12:13/test1@email.com/1,2,3,32";
        LocalDateTime date = Converter.getStrToLocalDateTime(dateTime);
        List<Integer> seats = Converter.parseSeatsList(seatsList);
        User user = userService.getUserByEmail(email);

        double price = bookingService.getTicketPrice(eventName, auditoriumName, date, seats, user);
        PriceJson wrapper = new PriceJson();
        wrapper.setPrice(String.valueOf(price));
        return mapper.writeValueAsString(wrapper);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String bookTicket(@RequestBody RequestTicketJson json) throws NotEnoughMoneyException, JsonProcessingException {

        String eventName = json.getEventName();
        String auditoriumName = json.getAuditoriumName();
        LocalDateTime dateTime = Converter.getStrToLocalDateTime(json.getDateTime());
        List<Integer> seats = Converter.parseSeatsList(json.getSeats());
        String email = json.getEmail();

        Auditorium auditorium = auditoriumService.getByName(auditoriumName);
        Event event = eventService.getEvent(eventName, auditorium, dateTime);
        User user = userService.getUserByEmail(email);

        double price = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), dateTime, seats, user);
        UserAccount userAccount = user.getUserAccount();
        if (price > userAccount.getMoney())
            throw new NotEnoughMoneyException("You have not enough money to buy this ticket. Please fill you account.");
        userAccount.setMoney(userAccount.getMoney() - price);

        Ticket ticket = new Ticket(event, LocalDateTime.now(), seats, user, price);

        return mapper.writeValueAsString(bookingService.bookTicket(ticket.getUser(), ticket));
    }

    @RequestMapping(value = "/tickets", headers = "accept=application/pdf", method = RequestMethod.GET, produces = "application/pdf")
    public List<Ticket> getAllTickets() throws Exception {

        return bookingService.getAllTickets();
    }

    @RequestMapping(value = "/tickets/{eventName}/{auditoriumName}/{dateTime}", headers = "accept=application/pdf", method = RequestMethod.GET, produces = "application/pdf")
    public List<Ticket> getAllTicketsByEvent(@PathVariable String eventName, @PathVariable String auditoriumName, @PathVariable String dateTime) throws Exception {

        LocalDateTime date = Converter.getStrToLocalDateTime(dateTime);

        return bookingService.getTicketsForEvent(eventName, auditoriumName, date);
    }

    @RequestMapping(value = "/ticketsJson", method = RequestMethod.GET, produces = "application/json")
    public String getAllTicketsJson() throws Exception {

        return mapper.writeValueAsString(bookingService.getAllTickets());
    }
}
