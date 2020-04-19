package RedisCLI.java;

import RedisCLI.java.Commands.*;
import RedisCLI.java.Entity.ZsetComparator;
import RedisCLI.java.Entity.ZsetEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;

public class RedisCLI {
    public static String[] parseInputData(String input) {
        return input.split(" ");
    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, SortedSet<ZsetEntity>> zSet = new HashMap<>();
        try {
            map = mapper.readValue(new File("map.json"), new TypeReference<HashMap<String, String>>() {
            });
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            zSet = mapper.readValue(new File("zMap.json"), new TypeReference<HashMap<String, SortedSet<ZsetEntity>>>() {
            });
        } catch (Exception e) {
            System.out.println(e);
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("host:port->");
            String input = sc.nextLine();
            String[] inputArray = parseInputData(input);
            switch (inputArray[0]) {
                case "zadd":
                case "ZADD": {
                    Zadd.execute(inputArray, zSet);
                    break;
                }
                case "ZRANK":
                case "zrank": {
                    Zrank.execute(inputArray, zSet);
                    break;
                }
                case "ZRANGE":
                case "zrange": {
                    Zrange.execute(inputArray, zSet);
                    break;
                }
                case "GET":
                case "get": {
                    Get.execute(inputArray, map);
                    break;
                }
                case "SET":
                case "set": {
                    Set.execute(inputArray, map);
                    break;
                }
                case "EXPIRE":
                case "expire": {
                    Expire.execute(inputArray, map);
                    break;
                }
                case "EXIT":
                case "exit": {
                    Exit.execute(inputArray, map, zSet);
                    System.exit(0);
                    break;
                }
                default:
                    System.out.println("(error) ERR unknown command " + inputArray[0]);
            }
        }
    }
}
