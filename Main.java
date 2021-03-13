import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import java.io.File;
import org.ini4j.*;
import java.util.HashMap; 
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
    HashMap<String, String> keyMap = initKeys();
    
    // Initializing Discord Bot
    GatewayDiscordClient client = DiscordClientBuilder.create(keyMap.get("discord_key"))
      .build()
      .login()
      .block();

    client.onDisconnect().block();

    //HttpResponse<JsonNode> jsonResponse = null;

    /* try {
      jsonResponse = Unirest.get("https://miracosta.instructure.com/api/v1/courses")
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .queryString("access_token", "your api key")
            .asJson();
    } catch(Exception e) {
      System.out.println("Something went wrong...");
      System.exit(0);
    } */
    //System.out.println(jsonResponse.getBody());
    // 200 - Success
    //System.out.println(jsonResponse.getStatus());
  }

  public static HashMap<String, String> initKeys() {
    HashMap<String, String> keyMap = new HashMap<>();

    try {
      Wini ini = new Wini(new File("./keys.ini"));
      keyMap.put("discord_key", ini.get("api-keys", "discord_key"));
      keyMap.put("canvas_key", ini.get("api-keys", "canvas_key"));
      // To catch basically any error related to finding the file e.g
      // (The system cannot find the file specified)
    }catch(Exception e){
      System.err.println(e.getMessage());
      // If keys cannot successfully be retrieved, then the 
      // whole program will crash regardless
      System.exit(0);
    }

    return keyMap;
  }

}