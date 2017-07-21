import beans.models.*;
import beans.services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        AuditoriumService auditoriumService = (AuditoriumService) ctx.getBean("auditoriumServiceImpl");
        BookingService bookingService = (BookingService) ctx.getBean("bookingServiceImpl");
        EventService eventService = (EventService) ctx.getBean("eventServiceImpl");
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        DiscountService discountService = (DiscountService) ctx.getBean("discountServiceImpl");

        Auditorium blueHall = new Auditorium("Blue Hall", 500, Arrays.asList(23, 24, 25, 26));
        Auditorium yellowHall = new Auditorium("Yellow Hall", 800, Arrays.asList(23, 24, 25, 26, 32, 34, 36, 38));
        Auditorium redHall = new Auditorium("Red Hall", 1000, Arrays.asList(23, 24, 25, 26, 32, 34, 36, 38, 52, 54, 56, 58, 60));
        auditoriumService.save(blueHall);
        auditoriumService.save(yellowHall);
        auditoriumService.save(redHall);

        Event greatEvent = new Event("Great Show", Rate.HIGH, 300, LocalDateTime.of(2017, 2, 12, 12, 13), blueHall);
        Event midEvent = new Event("Middle Show", Rate.MID, 200, LocalDateTime.of(2017, 2, 19, 11, 45), yellowHall);
        Event lowEvent = new Event("Small Show", Rate.LOW, 100, LocalDateTime.of(2017, 9, 24, 10, 35), redHall);
        eventService.create(greatEvent);
        eventService.create(midEvent);
        eventService.create(lowEvent);

        UserAccount userAccountIvanov = new UserAccount(5000);
        UserAccount userAccountPetrov = new UserAccount(3000);
        UserAccount userAccountAnotherIvanov = new UserAccount(2000);
        User userIvanov = new User("test1@email.com", "Ivanov", LocalDate.of(1983, 3, 21), "$2a$12$vKjJ.JwaKnxLSt6GzZX9Ee/a9EYyUj5flb8zstcAEBl5LZVunuc5S", "ROLE_REGISTERED_USER");
        User userPetrov = new User("test2@email.com", "Petrov", LocalDate.of(1986, 5, 11), "$2a$12$1YNl2i/O.OXNXqYmdGGKNOX2d/KO0tN.ibJAMWk/SdQV4bWmzKe3u", "ROLE_REGISTERED_USER, ROLE_BOOKING_MANAGER");
        User userAnotherIvanov = new User("test3@email.com", "Ivanov", LocalDate.of(1990, 1, 3), "$2a$12$kE/Bq7vMo9GNk6K0bZkHDezdqN8vzUzVWvKKidQzix3V5HAB1lW8K", "ROLE_REGISTERED_USER");
        userIvanov.setUserAccount(userAccountIvanov);
        userPetrov.setUserAccount(userAccountPetrov);
        userAnotherIvanov.setUserAccount(userAccountAnotherIvanov);
        userService.register(userIvanov);
        userService.register(userPetrov);
        userService.register(userAnotherIvanov);

        /*String email = "dmitriy.vbabichev@gmail.com";
        String name = "Dmytro Babichev";
        String eventName = "The revenant";
        String auditoriumName = "Blue hall";
        Auditorium blueHall = auditoriumService.getByName(auditoriumName);
        Auditorium yellowHall = auditoriumService.getByName("Yellow hall");
        Auditorium redHall = auditoriumService.getByName("Red hall");
        LocalDateTime dateOfEvent = LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(15, 45, 0));

        userService.register(new User(email, name, LocalDate.now()));
        userService.register(new User("laory@yandex.ru", name, LocalDate.of(1992, 4, 29)));

        User userByEmail = userService.getUserByEmail(email);
        System.out.println("User with email: [" + email + "] is " + userByEmail);
        System.out.println();

        System.out.println("All users with name: [" + name + "] are: ");
        userService.getUsersByName(name).forEach(System.out:: println);
        System.out.println();

        Event event1 = eventService.create(
                new Event(eventName, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(9, 0, 0)),
                          blueHall));
        System.out.println();
        System.out.println("Event by name: " + eventService.getByName(event1.getName()));
        System.out.println();
        eventService.create(new Event(eventName, Rate.HIGH, 60, dateOfEvent, blueHall));
        Event event2 = eventService.create(
                new Event(eventName, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                          blueHall));
        eventService.create(
                new Event(eventName, Rate.HIGH, 90, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                          redHall));
        Event event = new Event(eventName, Rate.HIGH, 150,
                                LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)), yellowHall);
        event = eventService.create(event);

        System.out.println("List of all events:");
        eventService.getAll().forEach(System.out:: println);
        System.out.println();

        System.out.println(
                "Discount for user: [" + email + "] for event: [" + eventName + "] in auditorium: [" + auditoriumName +
                "] on date: [" + dateOfEvent + "] is [" +
                discountService.getDiscount(userByEmail, eventService.getEvent(eventName, blueHall, dateOfEvent))
                + "]");
        System.out.println();

        eventService.remove(event2);
        System.out.println("List of all events:");
        eventService.getAll().forEach(System.out:: println);
        System.out.println();

        List<Integer> seats = Arrays.asList(23, 24, 25, 26);
        double ticketPrice = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(),
                                                           event.getDateTime(), seats, userByEmail);
        System.out.println("Price for event: [" + event + "], seats: [" + seats + "], user: [" + userByEmail + "] = "
                           + ticketPrice);

        List<Integer> seats2 = Arrays.asList(27, 28, 29, 30);
        List<Integer> seats3 = Arrays.asList(2, 8, 9, 3);
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats, userByEmail, ticketPrice));
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats2, userByEmail,
                                                          bookingService.getTicketPrice(event.getName(),
                                                                                        event.getAuditorium().getName(),
                                                                                        event.getDateTime(), seats2,
                                                                                        userByEmail)));
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats3, userByEmail,
                                                          bookingService.getTicketPrice(event.getName(),
                                                                                        event.getAuditorium().getName(),
                                                                                        event.getDateTime(), seats3,
                                                                                        userByEmail)));

        System.out.println();
        System.out.println("Tickets booked for event: [" + event + "]");
        List<Ticket> ticketsForEvent = bookingService.getTicketsForEvent(event.getName(),
                                                                         event.getAuditorium().getName(),
                                                                         event.getDateTime());
        IntStream.range(0, ticketsForEvent.size()).forEach(
                i -> System.out.println("" + i + ") " + ticketsForEvent.get(i)));

        System.out.println();
        eventService.getByName("testName1");
        eventService.getByName("testName2");
        eventService.getByName("testName2");
        eventService.getByName("testName3");
        eventService.getByName(eventName);
        eventService.getEvent(event.getName(), event.getAuditorium(), event.getDateTime());

        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                                      userByEmail);
        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                                      userByEmail);

        System.out.println("CounterAspect.getAccessByNameStat() = " + CounterAspect.getAccessByNameStat());
        System.out.println("CounterAspect.getGetPriceByNameStat() = " + CounterAspect.getGetPriceByNameStat());
        System.out.println("CounterAspect.getBookTicketByNameStat() = " + CounterAspect.getBookTicketByNameStat());
        System.out.println();
        System.out.println("DiscountAspect.getDiscountStatistics() = " + DiscountAspect.getDiscountStatistics());
        System.out.println();
        System.out.println("LuckyWinnerAspect.getLuckyUsers() = " + LuckyWinnerAspect.getLuckyUsers());*/
    }
}
