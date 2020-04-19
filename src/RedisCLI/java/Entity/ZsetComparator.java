package RedisCLI.java.Entity;

import java.util.Comparator;

public class ZsetComparator implements Comparator<ZsetEntity> {
    @Override
    public int compare(ZsetEntity entity1, ZsetEntity entity2) {
        return entity1.key.compareTo(entity2.key);
    }
}
