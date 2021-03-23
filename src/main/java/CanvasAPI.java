import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CanvasAPI {
    private String key;
    private String todayDate;
    private String[] todayDateArr;

    public CanvasAPI(String key) {
        this.key = key;
        // Date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        this.todayDate = dtf.format(now);
        System.out.println(todayDate);
        this.todayDateArr = this.todayDate.split("-");
        // Time - UTC
        Clock clock = Clock.systemUTC();
        LocalTime nowTime = LocalTime.now(clock);
        System.out.println(nowTime.format(
                DateTimeFormatter.ofPattern("HH:mm:ss")
        ));
    }

    public void getAssigments() {
        HttpResponse<JsonNode> jsonResponse = null;

        try {
            jsonResponse = Unirest.get("https://miracosta.instructure.com/api/v1/courses/24908/assignments")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .queryString("access_token", this.key)
                    .queryString("bucket", "future")
                    .asJson();

            JSONArray responseJSONString = jsonResponse.getBody().getArray();

            for(int i = 0; i < responseJSONString.length(); i++ ) {
                //System.out.println(responseJSONString.get(i));
                // CanvasAPI returns an array of JSON Objects
                // So we get the i-position object in array
                // and parse into a JSONObject
                JSONObject abc = (JSONObject)responseJSONString.get(i);
                // Skips if due date is null
                if(abc.isNull("due_at") ) continue;
                // Assignment Due Date and Time
                System.out.println( abc.get("due_at"));
                String due = (String)abc.get("due_at");
                // Assignment Due Date
                String date = due.substring(0, due.indexOf('T'));
                String[] dateArr = date.split("-");
                // Assignment Due Time
                String time = due.substring(due.indexOf('T') + 1);
                System.out.println("Due on " + date + " at " + time);

                // Checks if the due date is the same month as today
                if(dateArr[1].equals(todayDateArr[1])) System.out.println("this month");
                // AM STUCK HERE
            }
        } catch(Exception e) {
            System.out.println("Something went wrong...");
            System.exit(0);
        }
        System.out.println(jsonResponse.getBody());
        // 200 - Success
        System.out.println(jsonResponse.getStatus());
    }
}
