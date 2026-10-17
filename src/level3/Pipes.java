package level3;
import java.io.FileReader;
import java.io.IOException ;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Pipes {
    JSONParser parser = new JSONParser();

    Object pipeObj = parser.parse(new FileReader("config-ap.json"));

    JSONObject pipeJsonObject = (JSONObject) pipeObj;

    String x = (String) pipeJsonObject.get("x");

    String y = (String) pipeJsonObject.get("y");

    String type = (String) pipeJsonObject.get("type");

    String item = (String) pipeJsonObject.get("item");

    public Pipes() throws IOException, ParseException {
    }

    public void Enemies() throws IOException, ParseException {
    }
}
