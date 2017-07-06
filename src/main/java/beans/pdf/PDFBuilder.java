package beans.pdf;

import beans.models.Ticket;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class PDFBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document doc, PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<Ticket> ticketList = (List<Ticket>) map.get("ticketList");

        doc.add(new Paragraph("Tickets booked for event"));

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {1.5f, 3.0f, 2.0f, 2.0f, 2.0f, 2.0f, 1.5f});
        table.setSpacingBefore(10);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Ticket number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Booked date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Event", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Event date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Seats number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Username", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        for (Ticket ticket : ticketList) {
            table.addCell(String.valueOf(ticket.getId()));
            table.addCell(ticket.getDateTime().toString());
            table.addCell(ticket.getEvent().getName());
            table.addCell(ticket.getEvent().getDateTime().toString());
            table.addCell(ticket.getSeats());
            table.addCell(ticket.getUser().getName());
            table.addCell(String.valueOf(ticket.getPrice()));
        }

        doc.add(table);

    }

}
