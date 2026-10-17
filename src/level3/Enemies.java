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
public class Enemies {
    JSONParser parser = new JSONParser();

    Object enemyObj = parser.parse(new FileReader("config-ap.json"));

    JSONObject enemyJsonObject = (JSONObject) enemyObj;

    String x = (String) enemyJsonObject.get("x");

    String y = (String) enemyJsonObject.get("y");

    String type = (String) enemyJsonObject.get("type");

    String item = (String) enemyJsonObject.get("item");

    public Enemies() throws IOException, ParseException {
    }
}
