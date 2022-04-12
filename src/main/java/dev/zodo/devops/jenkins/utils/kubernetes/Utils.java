package dev.zodo.devops.jenkins.utils.kubernetes;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Utils {

    static String extractValue(Object value, boolean wrap) {
        if (value == null) {
            return null;
        }

        String wrapChar = wrap ? "\n" : "";

        if (value instanceof BuildString) {
            return ((BuildString) value).buildString(wrap);
        }
        if (value instanceof List) {
            List<Object> buildStrings = (List<Object>) value;
            String formatedValue = buildStrings.stream()
                    .map(v -> String.format("[%s]", Utils.extractValue(v, wrap)))
                    .collect(Collectors.joining(String.format(",%s", wrapChar)));
            return String.format("%s%s", wrapChar, Utils.indent(formatedValue), wrapChar);
        }
        return String.format("'%s'", value);
    }

    static String convertValue(Field field, Object obj, boolean wrap) {
        try {
            Object value = extractValue(field.get(obj), wrap);
            if (value == null) {
                return null;
            }
            return String.format("%s: %s", field.getName(), value);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    static String indent(String str) {
        return indent(str, 2);
    }

    static String indent(String str, int size) {
        String strPad = size > 0 ? String.format("%1$" + size + "s", "") : "";
        return Arrays.stream(str.split("\n")).map(line -> strPad + line)
                .collect(Collectors.joining("\n"));
    }
}
