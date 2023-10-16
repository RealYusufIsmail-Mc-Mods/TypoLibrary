package com.khanhtypo.typolib.utils;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public final class TextUtils {
    public static String transferKeyToName(ResourceKey<?> resourceKey) {
        return transferIdPathToName(resourceKey.location());
    }

    public static String transferIdPathToName(ResourceLocation resourceLocation) {
        return performTransfer(resourceLocation.getPath());
    }

    private static String performTransfer(String path) {
        int pathLength = path.length();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Character.toUpperCase(path.charAt(0)));
        for (int i = 1; i < pathLength; i++) {
            if (path.charAt(i) == '_') {
                stringBuilder.append(' ');
            } else if (path.charAt(i - 1) == ' ') {
                stringBuilder.append(Character.toUpperCase(path.charAt(i)));
            } else stringBuilder.append(path.charAt(i));
        }
        return stringBuilder.toString();
    }
}
