package RedisCLI.java.Commands;

import RedisCLI.java.Threads.ExpireThread;

import java.util.HashMap;

public class Expire {

    private static boolean isValid(String[] stringArray) {
        if (stringArray.length != 3) {
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
            Long miliSeconds = Long.parseLong(stringArray[2]);
            if (map.containsKey(key)) {
                Thread thread = new Thread(new ExpireThread(key, miliSeconds, map));
                thread.start();
                System.out.println("(integer) 1");
            } else {
                System.out.println("(integer) 0");
                return;
            }
            return;
        }
    }
}
