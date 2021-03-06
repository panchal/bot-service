package controllers;

import play.Logger;
import play.libs.ws.WSClient;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * @author <a href="mailto:panchal@yahoo-inc.com">Deven Panchal</a>
 */
public class QuoBot extends Controller {

    private static final String GREETINGS_FILE = "resources/greetings.txt";

    @Inject
    WSClient ws;

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
        KikClient.postTextMessage(ws, kikRequest.getFrom(), kikRequest.getChatId(), "Hello, I am QuoBot");
        return ok("Hello Bot incoming route.");
    }

}
