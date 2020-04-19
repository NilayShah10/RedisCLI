package RedisCLI.java.Commands;

import java.util.HashMap;

public class Get {

    private static boolean isValid(String[] stringArray) {
        if (stringArray.length != 2) {
            System.out.println("(error) ERR wrong number of arguments for 'get' command");
            return false;
        }
        return true;
    }

    public static void execute(String[] stringArray, HashMap<String, String> map) {
        if (!isValid(stringArray)) {
            return;
        } else {
            String key = stringArray[1];
            if (map.containsKey(key)) {
                System.out.println('"' + map.get(key) + '"');
            } else {
                System.out.println("(null)");
                return;
            }
            return;
        }
    }
}