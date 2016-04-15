package controllers;

import play.Logger;
import play.libs.ws.WSClient;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HelloBot extends Controller {

    private static final String GREETINGS_FILE = "resources/greetings.txt";

    private List<String> greetings;

    @Inject
    WSClient ws;

    public HelloBot() {
        ClassLoader classLoader = getClass().getClassLoader();
        greetings = StreamUtils.loadResourceAsList(classLoader, GREETINGS_FILE);
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result incoming() {
        KikRequest kikRequest = new KikRequest(request());
        Logger.info(kikRequest.getFrom() + ": " + kikRequest.getBody());
        KikClient.postTextMessage(ws, kikRequest.getFrom(), kikRequest.getChatId(), getHelloMessage());
        return ok("Hello Bot incoming route.");
    }

    private String getHelloMessage() {
        Random random = new Random();
        int randomIndex = random.nextInt(greetings.size());
        return greetings.get(randomIndex);
    }

}
