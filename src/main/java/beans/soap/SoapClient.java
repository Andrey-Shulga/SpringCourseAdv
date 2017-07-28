package beans.soap;

import beans.configuration.SoapConfig;
import beans.models.User;
import beans.soap.com.epam.GetByIdResponse;
import beans.soap.com.epam.RegisterUserResponse;
import beans.soap.com.epam.UserAccount;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SoapClient {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapConfig.class);
        SoapUser soapUser = context.getBean(SoapUser.class);

        long userId = 3;
        GetByIdResponse response = soapUser.getUserById(userId);
        User user = response.getUser();
        System.out.println("Found user " + user);

        beans.soap.com.epam.User newUser = new beans.soap.com.epam.User("test10@email.com", "Vasya", "1983-05-09",
                "$2a$12$vKjJ.JwaKnxLSt6GzZX9Ee/a9EYyUj5flb8zstcAEBl5LZVunuc5S", "ROLE_REGISTERED_USER", new UserAccount(1000));
        RegisterUserResponse registerUserResponse = soapUser.registerUser(newUser);
        User registeredUser = registerUserResponse.getUser();
        System.out.println("New user " + registeredUser + " was registered");

        long deleteId = registeredUser.getId();
        soapUser.removeUser(deleteId);
        System.out.println("User with id " + deleteId + " was deleted");
    }
}
