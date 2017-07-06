package beans.controller;

import beans.models.Event;
import beans.models.User;
import beans.services.EventService;
import beans.services.UserService;
import com.google.gson.*;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;

    private List <User> userList= new ArrayList<>();
    private List <Event> eventList = new ArrayList<>();

    @RequestMapping(value = "/openFileForm", method = RequestMethod.GET)
    public String openFileForm() {

        return "file";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String resultJson = new String(bytes);
                parseJsonToObjects(resultJson);
                for (User user: userList)
                    userService.register(user);
                for (Event event: eventList)
                    eventService.create(event);
                return new String(bytes);
            } catch (IOException e) {
                return "You failed to upload " + e.getMessage();
            }
        } else {
            return "You failed to upload because the file was empty.";
        }
    }

    private void parseJsonToObjects(String resultJson) {

        JsonObject jsonObject = new JsonParser().parse(resultJson).getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("batch").getAsJsonArray();

        for (int i=0; i<jsonArray.size(); i++) {
            JsonElement jsonElement = jsonArray.get(i);
            JsonObject someJsonObject;
            if (jsonElement.isJsonObject()) {
                someJsonObject = (JsonObject) jsonElement;
                User user;
                Event event;
                if ("user".equals(someJsonObject.get("model").getAsString())) {
                    user = parseJsonToUser(someJsonObject);
                    if (user!=null)
                        userList.add(user);
                }
                if ("event".equals(someJsonObject.get("model").getAsString())) {
                    event = parseJsonToEvent(someJsonObject);
                    if (event!=null)
                        eventList.add(event);
                }

            }

        }

    }

    private Event parseJsonToEvent(JsonObject eventJsonObject) {

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();

        return gson.fromJson(eventJsonObject, Event.class);

    }

    private User parseJsonToUser(JsonObject userJsonObject) {

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();

        return gson.fromJson(userJsonObject, User.class);
    }

}
