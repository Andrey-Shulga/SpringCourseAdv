package beans.rest;

import beans.configuration.SpringWebConfig;
import beans.models.Ticket;
import beans.rest.json.PriceJson;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestClient {

    public static void main(String[] args) {

        //testGetTicketPrice();
        testBookTicket();
        //testGetAllTicketsInPdf();
        testGetAllTicketsInJson();
    }

    private static void testGetAllTicketsInJson() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringWebConfig.class);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        final String URI = "http://localhost:8080/api/ticketsJson";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(URI, HttpMethod.GET, entity, String.class);
        System.out.println("Tickets " + response.getBody());
    }

    private static void testGetAllTicketsInPdf() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringWebConfig.class);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        restTemplate.setMessageConverters(getMessageConverters());

        final String URI = "http://localhost:8080/api/tickets";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType("application", "pdf")));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List> response = restTemplate.exchange(URI, HttpMethod.GET, entity, List.class);
        List<Ticket> ticketList = response.getBody();
        HttpHeaders responseHeaders = response.getHeaders();
        System.out.println("Response headers " + responseHeaders);
    }

    private static List<HttpMessageConverter<?>> getMessageConverters() {

        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new PdfHttpMessageConverter());
        return converters;
    }

    private static void testBookTicket() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringWebConfig.class);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        final String URI = "http://localhost:8080/api/book";
        final String JSON = "{\"eventName\":\"Middle Show\",\"auditoriumName\":\"Yellow Hall\",\"dateTime\":\"2017-02-19T11:45\",\"seats\":\"48,49\",\"email\":\"test2@email.com\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(JSON, headers);

        ResponseEntity<String> response = restTemplate.exchange(URI, HttpMethod.POST, entity, String.class);
        System.out.println("Your booked ticket " + response.getBody());

    }

    private static void testGetTicketPrice() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringWebConfig.class);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        final String URI = "http://localhost:8080/api/price/{eventName}/{auditoriumName}/{dateTime}/{email}/{seatsList}";
        String eventName = "Great Show";
        String auditoriumName = "Blue Hall";
        String dateTime = "2017-02-12T12:13";
        String email = "test1.email.com";
        String seats = "1,2,3,32";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<PriceJson> response = restTemplate.exchange(URI, HttpMethod.GET, entity, PriceJson.class, eventName, auditoriumName, dateTime, email, seats);
        PriceJson price = response.getBody();
        System.out.println("Price for ticket " + price.getPrice());
    }


}
