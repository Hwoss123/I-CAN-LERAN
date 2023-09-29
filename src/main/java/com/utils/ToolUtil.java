package com.utils;

import java.util.UUID;

public class ToolUtil {
    public static String simpleUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
