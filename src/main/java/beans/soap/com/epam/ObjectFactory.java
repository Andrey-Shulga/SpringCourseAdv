//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.27 at 09:26:00 PM ALMT 
//


package beans.soap.com.epam;

import beans.models.User;
import beans.models.UserAccount;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.epam package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Auditorium_QNAME = new QName("http://epam.com", "auditorium");
    private final static QName _Event_QNAME = new QName("http://epam.com", "event");
    private final static QName _Rate_QNAME = new QName("http://epam.com", "rate");
    private final static QName _User_QNAME = new QName("http://epam.com", "user");
    private final static QName _UserAccount_QNAME = new QName("http://epam.com", "userAccount");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epam
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Auditorium }
     */
    public Auditorium createAuditorium() {
        return new Auditorium();
    }

    /**
     * Create an instance of {@link Event }
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link User }
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link UserAccount }
     */
    public UserAccount createUserAccount() {
        return new UserAccount();
    }

    /**
     * Create an instance of {@link GetByIdRequest }
     */
    public GetByIdRequest createGetByIdRequest() {
        return new GetByIdRequest();
    }

    /**
     * Create an instance of {@link GetByIdResponse }
     */
    public GetByIdResponse createGetByIdResponse() {
        return new GetByIdResponse();
    }

    /**
     * Create an instance of {@link RegisterUserRequest }
     */
    public RegisterUserRequest createRegisterUserRequest() {
        return new RegisterUserRequest();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link RemoveUserRequest }
     */
    public RemoveUserRequest createRemoveUserRequest() {
        return new RemoveUserRequest();
    }

    /**
     * Create an instance of {@link RemoveUserResponse }
     */
    public RemoveUserResponse createRemoveUserResponse() {
        return new RemoveUserResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Auditorium }{@code >}}
     */
    @XmlElementDecl(namespace = "http://epam.com", name = "auditorium")
    public JAXBElement<Auditorium> createAuditorium(Auditorium value) {
        return new JAXBElement<Auditorium>(_Auditorium_QNAME, Auditorium.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Event }{@code >}}
     */
    @XmlElementDecl(namespace = "http://epam.com", name = "event")
    public JAXBElement<Event> createEvent(Event value) {
        return new JAXBElement<Event>(_Event_QNAME, Event.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Rate }{@code >}}
     */
    @XmlElementDecl(namespace = "http://epam.com", name = "rate")
    public JAXBElement<Rate> createRate(Rate value) {
        return new JAXBElement<Rate>(_Rate_QNAME, Rate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     */
    @XmlElementDecl(namespace = "http://epam.com", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserAccount }{@code >}}
     */
    @XmlElementDecl(namespace = "http://epam.com", name = "userAccount")
    public JAXBElement<UserAccount> createUserAccount(UserAccount value) {
        return new JAXBElement<UserAccount>(_UserAccount_QNAME, UserAccount.class, null, value);
    }

}