package br.com.dayhan.cursomc.resources.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    private URL() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Integer> decodeIntList(String s) {
        return Arrays.stream(s.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static String decodeParam(String s) {
        return URLDecoder.decode(s, StandardCharsets.UTF_8);
    }
}
