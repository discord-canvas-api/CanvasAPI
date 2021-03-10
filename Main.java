import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
// Not sure which of the bottom is used...
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {

  public static void main(String[] args) {
    /** GatewayDiscordClient client = DiscordClientBuilder.create("discord key")
      .build()
      .login()
      .block(); **/

    HttpResponse<JsonNode> jsonResponse = null;

    try {
      jsonResponse = Unirest.get("https://miracosta.instructure.com/api/v1/courses")
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .queryString("access_token", "your api key")
            .asJson();
    } catch(Exception e) {
      System.out.println("Something went wrong...");
      System.exit(0);
    }
    //System.out.println(jsonResponse.getBody());
    // 200 - Success
    System.out.println(jsonResponse.getStatus());

      
      //client.onDisconnect().block();
  }

}