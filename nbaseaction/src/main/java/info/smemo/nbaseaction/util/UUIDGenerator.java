package info.smemo.nbaseaction.util;

import java.util.UUID;

public class UUIDGenerator {

    /**
     * 生成36位的UUID
     */
    public static String generate36UUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

}
