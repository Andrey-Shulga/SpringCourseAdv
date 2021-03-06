package beans.controller;

import beans.models.Ticket;
import beans.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import util.Converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class PdfController {

    @Autowired
    @Qualifier("bookingServiceImpl")
    private BookingService bookingService;

    @RequestMapping (value ="/openPdf", method = RequestMethod.GET)
    public String openPdf(){

        return "pdf";
    }

    @RequestMapping (headers="accept=application/pdf", method = RequestMethod.GET)
    public String getPdf(@RequestParam Map<String, String> requestParams, ModelMap map){

        String eventName = requestParams.get("eventName");
        String audName = requestParams.get("audName");
        String date = requestParams.get("date");
        LocalDateTime dateTime = Converter.getStrToLocalDateTime(date);

        List<Ticket> ticketList = bookingService.getTicketsForEvent(eventName, audName, dateTime);
        map.put("ticketList", ticketList);
        return "pdfView";
    }


}
