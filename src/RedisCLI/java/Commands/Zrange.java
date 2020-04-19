package RedisCLI.java.Commands;

import RedisCLI.java.Entity.ZsetEntity;

import java.util.*;

public class Zrange {
    private static boolean isValid(String[] stringArray) {
        if (stringArray.length != 4 && stringArray.length != 5) {
            System.out.println("(error) ERR wrong number of arguments for 'zrange' command");
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

                int ind_start = Integer.parseInt(stringArray[2]), ind_end = Integer.parseInt(stringArray[3]);
                if (ind_start < 0)
                    ind_start += sortedSet.size();
                if (ind_end < 0)
                    ind_end += (sortedSet.size());
                if (ind_end > sortedSet.size() - 1)
                    ind_end = sortedSet.size() - 1;
                if (ind_start < 0)
                    ind_start = 0;
                if (ind_start > ind_end) {
                    System.out.println("(nil)");
                    return;
                }

                if (stringArray.length == 5) {
                    int j = 1;
                    for (int i = ind_start; i <= ind_end; i++) {
                        System.out.println(j + ")" + ((ZsetEntity) (sortedSet.toArray())[i]).getKey());
                        j++;
                        System.out.println(j + ")" + ((ZsetEntity) (sortedSet.toArray())[i]).getValue());
                        j++;
                    }
                } else {
                    for (int i = ind_start; i <= ind_end; i++) {
                        System.out.println(i + 1 + ")" + ((ZsetEntity) (sortedSet.toArray())[i]).getKey());
                    }
                }

            } else {
                System.out.println("(nil)");
            }
            return;

        }
    }
}
