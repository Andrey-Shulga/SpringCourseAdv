package beans.configuration;

import beans.soap.SoapUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("beans.soap.com.epam");
        return marshaller;
    }

    @Bean
    public SoapUser getSoapUser(Jaxb2Marshaller marshaller) {
        SoapUser soapUser = new SoapUser();
        soapUser.setDefaultUri("http://localhost:8080/ws/booking");
        soapUser.setMarshaller(marshaller);
        soapUser.setUnmarshaller(marshaller);
        return soapUser;
    }
}
