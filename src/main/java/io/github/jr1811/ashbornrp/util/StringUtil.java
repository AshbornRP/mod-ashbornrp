package io.github.jr1811.ashbornrp.util;

import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringUtil {
    public static String cleanString(Identifier identifier, boolean reverse) {
        List<String> input = List.of(identifier.getPath().split("/"));
        List<String> words = Arrays.asList(input.get(input.size() - 1).split("_"));
        if (reverse) Collections.reverse(words);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            char capitalized = Character.toUpperCase(word.charAt(0));
            output.append(capitalized).append(word.substring(1));
            if (i < words.size() - 1) {
                output.append(" ");
            }
        }
        return output.toString();
    }
}
