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

public class Level3Reader {


        public static void main(String[] args) {

            JSONParser parser = new JSONParser();

            try {
                Object obj = parser.parse(new FileReader("config-ap.json"));

                JSONObject jsonObject =  (JSONObject) obj;

                String length = (String) jsonObject.get("length");
                System.out.println(length);

                String time = (String) jsonObject.get("time");
                System.out.println(time);





                // loop array
                JSONArray blocks = (JSONArray) jsonObject.get("blocks");
                Iterator<Blocks> block =  blocks.iterator();
                while (block.hasNext()) {
                    System.out.println();
                }



                JSONArray enemeies = (JSONArray) jsonObject.get("enemeies");
                Iterator<Enemies> enemy =  enemeies.iterator();
                while (enemy.hasNext()) {
                    System.out.println(enemy.next());
                }

                JSONArray pipes = (JSONArray) jsonObject.get("pipes");
                Iterator<Pipes> pipe =  pipes.iterator();
                while (pipe.hasNext()) {
                    System.out.println(pipe.next());
                }





            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


