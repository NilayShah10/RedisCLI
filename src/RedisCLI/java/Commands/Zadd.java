package RedisCLI.java.Commands;

import RedisCLI.java.Entity.ZsetComparator;
import RedisCLI.java.Entity.ZsetEntity;

import java.util.*;
import java.util.regex.Pattern;

public class Zadd {
    static int binarySearch(Object[] arr, int l, int r, String x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            // If the element is found at the middle
            if (((ZsetEntity) (arr[mid])).getKey().equals(x))
                return mid;
            // If element is smaller than mid, then
            if (((ZsetEntity) arr[mid]).getKey().compareTo(x) > 0)
                return binarySearch(arr, l, mid - 1, x);
            // Else the element can only be present
            return binarySearch(arr, mid + 1, r, x);
        }
        // We reach here when element is not present in array
        return -1;
    }

    private static boolean isValid(String[] stringArray) {
        if (stringArray.length < 4) {
            System.out.println("(error) ERR wrong number of arguments for 'zadd' command");
            return false;
        } else {
            // Checking is value is numric or not
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
            for (int i = 2; i < stringArray.length; i = i + 2) {
                if (!pattern.matcher(stringArray[i]).matches()) {
                    System.out.println("(error) ERR value is not a valid float");
                    return false;
                }
            }
        }
        return true;
    }

    public static void execute(String[] stringArray, HashMap<String, SortedSet<ZsetEntity>> map) {
        if (!isValid(stringArray)) {
            return;
        } else {
            String zkey = stringArray[1];
            if (!map.containsKey(zkey)) {
                SortedSet<ZsetEntity> sm = new TreeSet<>(new ZsetComparator());
                map.put(zkey, sm);
            }
            SortedSet<ZsetEntity> sortedSet = map.get(zkey);
            int count_exec = 0;
            for (int i = 2; i < stringArray.length - 1; i = i + 2) {
                Integer value = Integer.parseInt(stringArray[i]);
                String key = stringArray[i + 1];
                // check wether key is already in set or not.
                Integer index = binarySearch(sortedSet.toArray(), 0, sortedSet.size() - 1, key);
                if (index == -1) {
                    ZsetEntity entity = new ZsetEntity(key, value);
                    sortedSet.add(entity);
                    count_exec++;
                }
            }
            System.out.println("(integer) " + (count_exec));
            return;
        }
    }
}
