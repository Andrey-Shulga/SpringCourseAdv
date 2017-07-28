package beans.soap;

import beans.soap.com.epam.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SoapUser extends WebServiceGatewaySupport {

    GetByIdResponse getUserById(long id) {

        GetByIdRequest request = new GetByIdRequest();
        request.setId(id);
        return (GetByIdResponse) getWebServiceTemplate().marshalSendAndReceive(request);

    }

    RegisterUserResponse registerUser(User user) {

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUser(user);
        return (RegisterUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    RemoveUserResponse removeUser(long id) {

        RemoveUserRequest request = new RemoveUserRequest();
        request.setId(id);
        return (RemoveUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
