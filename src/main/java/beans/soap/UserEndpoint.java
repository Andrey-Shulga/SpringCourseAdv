package beans.soap;

import beans.models.User;
import beans.services.UserService;
import beans.soap.com.epam.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://epam.com";
    @Autowired
    private UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getByIdRequest")
    @ResponsePayload
    public GetByIdResponse getById(@RequestPayload GetByIdRequest request) {

        GetByIdResponse response = new GetByIdResponse();
        beans.models.User user = userService.getById(request.getId());
        response.setUser(user);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerUserRequest")
    @ResponsePayload
    public RegisterUserResponse registerUser(@RequestPayload RegisterUserRequest request) {

        RegisterUserResponse response = new RegisterUserResponse();
        beans.models.User user = new beans.models.User(request.getUser());
        User newUser = userService.register(user);
        response.setUser(newUser);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeUserRequest")
    @ResponsePayload
    public RemoveUserResponse removeUser(@RequestPayload RemoveUserRequest request) {

        RemoveUserResponse response = new RemoveUserResponse();
        long id = request.getId();
        GetByIdRequest getByIdRequest = new GetByIdRequest();
        getByIdRequest.setId(id);
        User userToDelete = getById(getByIdRequest).getUser();
        userService.remove(userToDelete);

        return response;
    }
}
