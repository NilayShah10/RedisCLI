package RedisCLI.java.Commands;

import RedisCLI.java.Entity.ZsetEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.io.File;
import java.io.IOException;

public class Exit {
    private static boolean isValid(String[] stringArray) {
        if (stringArray.length != 1) {
            System.out.println("(error) ERR wrong number of arguments for 'exit' command");
            return false;
        }
        return true;
    }

    public static void execute(String[] stringArray, HashMap<String, String> map,
            HashMap<String, SortedSet<ZsetEntity>> zMap) {
        if (!isValid(stringArray)) {
            return;
        } else {
            try {
                // Open file to write the data.
                PrintWriter out = new PrintWriter("map.json");
                out.close();
                out = new PrintWriter("zMap.json");
                out.close();
                ObjectMapper mapper = new ObjectMapper();
                try {
                    mapper.writeValue(new File("map.json"), map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    mapper.writeValue(new File("zMap.json"), zMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                System.out.println(e);
            }
            return;
        }
    }
}
