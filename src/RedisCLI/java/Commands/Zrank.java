package RedisCLI.java.Commands;

import RedisCLI.java.Entity.ZsetEntity;

import java.util.*;

public class Zrank {

    private static boolean isValid(String[] stringArray) {
        if (stringArray.length != 3) {
            System.out.println("(error) ERR wrong number of arguments for 'zrank' command");
            return false;
        }
        return true;
    }

    public static void execute(String[] stringArray, HashMap<String, SortedSet<ZsetEntity>> map) {
        if (!isValid(stringArray)) {
            return;
        } else {
            String zkey = stringArray[1];
            if (map.containsKey(zkey)) {
                SortedSet<ZsetEntity> sortedSet = map.get(zkey);
                String key = stringArray[2];
                Integer index = Zadd.binarySearch(sortedSet.toArray(), 0, sortedSet.size() - 1, key);
                if (index != -1)
                    System.out.println("(integer) " + index);
                else
                    System.out.println("(nil)");
            } else {
                System.out.println("(nil)");
            }
            return;
        }
    }
}
