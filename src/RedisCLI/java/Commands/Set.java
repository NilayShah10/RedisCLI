package RedisCLI.java.Commands;

import java.util.HashMap;

public class Set {
    private static boolean isValid(String[] stringArray) {
        if (stringArray.length != 3) {
            System.out.println("(error) ERR wrong number of arguments for 'set' command");
            return false;
        }
        return true;
    }

    public static void execute(String[] stringArray, HashMap<String, String> map) {
        if (!isValid(stringArray)) {
            return;
        } else {
            String key = stringArray[1];
            String value = stringArray[2];
            if (map.containsKey(key)) {
                map.replace(key, value);
            } else {
                map.put(key, value);
            }
            System.out.println("OK");
            return;
        }
    }
}
