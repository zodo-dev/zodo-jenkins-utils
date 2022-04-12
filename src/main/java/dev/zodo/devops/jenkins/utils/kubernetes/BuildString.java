package dev.zodo.devops.jenkins.utils.kubernetes;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface BuildString {
    default String buildString() {
        return buildString(false);
    }

    default String buildString(boolean wrap) {
        String tplFormat = templateFormat();
        String str = Utils.indent(Stream.of(this.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(FieldProperty.class))
                .map(f -> Utils.convertValue(f, this, wrap && this.allowWrap()))
                .filter(Objects::nonNull)
                .collect(Collectors.joining(wrap && this.allowWrap() ? ",\n" : ", ")), this.indentSize());
        return tplFormat == null ? str : String.format(tplFormat, str);
    }

    default int indentSize() {
        return 2;
    }

    default boolean allowWrap() {
        return true;
    }

    default String templateFormat() {
        return null;
    }
}
