//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.27 at 09:26:00 PM ALMT 
//


package beans.soap.com.epam;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Event complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Event"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://epam.com}rate" minOccurs="0"/&gt;
 *         &lt;element name="ticketPrice" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="dateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://epam.com}auditorium" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Event", propOrder = {
        "id",
        "name",
        "rate",
        "ticketPrice",
        "dateTime",
        "auditorium"
})
public class Event {

    protected long id;
    protected String name;
    @XmlSchemaType(name = "string")
    protected Rate rate;
    protected double ticketPrice;
    protected String dateTime;
    protected Auditorium auditorium;

    /**
     * Gets the value of the id property.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the rate property.
     *
     * @return possible object is
     * {@link Rate }
     */
    public Rate getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     *
     * @param value allowed object is
     *              {@link Rate }
     */
    public void setRate(Rate value) {
        this.rate = value;
    }

    /**
     * Gets the value of the ticketPrice property.
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Sets the value of the ticketPrice property.
     */
    public void setTicketPrice(double value) {
        this.ticketPrice = value;
    }

    /**
     * Gets the value of the dateTime property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets the value of the dateTime property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDateTime(String value) {
        this.dateTime = value;
    }

    /**
     * Gets the value of the auditorium property.
     *
     * @return possible object is
     * {@link Auditorium }
     */
    public Auditorium getAuditorium() {
        return auditorium;
    }

    /**
     * Sets the value of the auditorium property.
     *
     * @param value allowed object is
     *              {@link Auditorium }
     */
    public void setAuditorium(Auditorium value) {
        this.auditorium = value;
    }

}
