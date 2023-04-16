package me.ts.aprilfools.util;

public class ClassNameUtil {
    private static final String LUNAR_BASE_PACKAGE = "com/moonsworth/lunar/";
    private static final String LUNAR_ICHOR_PACKAGE = LUNAR_BASE_PACKAGE + "ichor/";
    private static final String LUNAR_GENESIS_PACKAGE = LUNAR_BASE_PACKAGE + "genesis/";

    public static boolean isLunarClass(String name) {
        return name.startsWith(LUNAR_BASE_PACKAGE);
    }

    public static boolean isLunarIchorClass(String name) {
        return name.startsWith(LUNAR_ICHOR_PACKAGE);
    }

    public static boolean isLunarGenesisClass(String name) {
        return name.startsWith(LUNAR_GENESIS_PACKAGE);
    }

    public static boolean isLunarBaseClass(String name) {
        return isLunarClass(name) && !isLunarIchorClass(name) && !isLunarGenesisClass(name);
    }
}
