package dev.zodo.devops.jenkins.utils.kubernetes;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface Utils {

    static String extractValue(Object value, boolean wrap, String wrapChar, int indentSize) {
        if (value == null) {
            return null;
        }

        if (value instanceof Map) {
            String mapString = ((Map<?, ?>) value).entrySet().stream()
                    .map(e -> String.format("%s: '%s'", e.getKey(), e.getValue()))
                    .collect(Collectors.joining(String.format(", %s", wrapChar)));
            return String.format("[%s%s%s]", wrapChar, Utils.indent(mapString, indentSize), wrapChar);
        }
        if (value instanceof BuildString) {
            return ((BuildString) value).buildString(wrap);
        }
        if (value instanceof List) {
            List<?> buildStrings = (List<?>) value;
            String formatedValue = buildStrings.stream()
                    .map(v -> String.format("[%s]", Utils.extractValue(v, wrap, wrapChar, indentSize)))
                    .collect(Collectors.joining(String.format(",%s", wrapChar)));
            return String.format("%s%s", wrapChar, Utils.indent(formatedValue, indentSize));
        }
        return String.format("'%s'", value);
    }

    static String convertValue(Field field, Object obj, boolean wrap, String wrapChar, int indentSize) {
        try {
            Object value = extractValue(field.get(obj), wrap, wrapChar, indentSize);
            if (value == null) {
                return null;
            }
            return String.format("%s: %s", field.getName(), value);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    static String indent(String str, int size) {
        String strPad = size > 0 ? String.format("%1$" + size + "s", "") : "";
        return Arrays.stream(str.split("\n")).map(line -> strPad + line)
                .collect(Collectors.joining("\n"));
    }

    Comparator<Field> identityComparator = (p1, p2) -> 0;
}
