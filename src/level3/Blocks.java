package level3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException ;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Blocks {
    JSONParser parser = new JSONParser();

        Object blockObj = parser.parse(new FileReader("config-ap.json"));

        JSONObject blockJsonObject = (JSONObject) blockObj;

        String x = (String) blockJsonObject.get("x");

        String y = (String) blockJsonObject.get("y");

        String type = (String) blockJsonObject.get("type");

        String item = (String) blockJsonObject.get("item");


    public Blocks() throws IOException, ParseException {
    }
}
