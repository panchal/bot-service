package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;

/**
 * @author <a href="mailto:panchal@yahoo-inc.com">Deven Panchal</a>
 */
public class KikClient {

    private static final String KIK_SCHEME = "https";
    private static final String KIK_SERVER = "api.kik.com";

    public static void postTextMessage(WSClient ws, String to, String chatId, String textMessage) {
        String kikPath = "/v1/message";
        String kikUrl = getUrl(KIK_SCHEME, KIK_SERVER, kikPath);
        WSRequest kikRequest =  ws
                .url(kikUrl)
                .setHeader("Content-Type", "application/json")
                .setAuth("devenpanchal", "ea4d0b90-d0ad-41d7-8bc6-216459249d84");

        kikRequest.post(getPostMessage(to, chatId, textMessage));
    }

    public static String getUrl(String scheme, String server, String path) {
        return scheme + "://" + server + path;
    }

    public static JsonNode getPostMessage(String to, String chatId, String textMessage) {
        ObjectNode message = Json.newObject();
        message.put("body", textMessage);
        message.put("to", to);
        message.put("type", "text");
        message.put("chatId", chatId);
        ArrayNode messages = Json.newArray();
        messages.add(message);
        ObjectNode body = Json.newObject();
        body.put("messages", messages);
        return body;
    }

}
