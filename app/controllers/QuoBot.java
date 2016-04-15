package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import play.libs.ws.WSClient;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:panchal@yahoo-inc.com">Deven Panchal</a>
 */
public class QuoBot extends Controller {

    private static final String GREETINGS_FILE = "resources/greetings.txt";

    private List<String> greetings;

    @Inject
    WSClient ws;

    public QuoBot() {
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
        Logger.info("Incoming Request: " + request().host(), request().uri());
        Logger.info(kikRequest.getFrom() + ": " + kikRequest.getBody());
        KikClient.postTextMessage(ws, kikRequest.getFrom(), kikRequest.getChatId(), greetings);
        return ok("Hello Bot incoming route.");
    }

}
