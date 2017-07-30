package beans.rest;

import beans.models.Ticket;
import beans.pdf.PDFBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdfHttpMessageConverter extends AbstractHttpMessageConverter<List<Ticket>> {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    public PdfHttpMessageConverter() {
        super(new MediaType("application", "pdf"));
    }

    @Override
    public boolean canRead(Class aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class aClass, MediaType mediaType) {

        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {

        List<MediaType> mediaTypes = new ArrayList<>();
        MediaType pdfType = new MediaType("application", "pdf");
        mediaTypes.add(pdfType);
        return mediaTypes;
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    protected List<Ticket> readInternal(Class<? extends List<Ticket>> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void writeInternal(List<Ticket> t, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

        Map<String, Object> map = new HashMap<>();
        map.put("ticketList", t);
        PDFBuilder pdfBuilder = new PDFBuilder();
        try {
            pdfBuilder.render(map, request, response);
        } catch (Exception e) {
            throw new HttpMessageNotWritableException(e.getMessage());
        }

    }


}
