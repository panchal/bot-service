package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Http;

/**
 * @author <a href="mailto:panchal@yahoo-inc.com">Deven Panchal</a>
 */
public class KikRequest {

    JsonNode request;
    JsonNode messages;
    private String from;
    private String body;
    private String chatId;

    public KikRequest(Http.Request request) {
        this.request = request.body().asJson();
        this.messages = request.body().asJson().findPath("messages");
        this.from = messages.get(0).findPath("from").asText();
        this.body = messages.get(0).findPath("body").asText();
        this.chatId = messages.get(0).findPath("chatId").asText();
    }

    public JsonNode getRequest() {
        return request;
    }

    public JsonNode getMessages() {
        return messages;
    }

    public String getFrom() {
        return from;
    }

    public String getBody() {
        return body;
    }

    public String getChatId() {
        return chatId;
    }

}
