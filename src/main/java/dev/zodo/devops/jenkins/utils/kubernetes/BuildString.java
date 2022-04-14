package dev.zodo.devops.jenkins.utils.kubernetes;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface BuildString {
    default String buildString() {
        return buildString(true);
    }

    default String buildString(boolean wrap) {
        String tplFormat = templateFormat(wrap);
        Comparator<Field> sortBy = Boolean.FALSE.equals(this.sortByName()) ? Utils.identityComparator : Comparator.comparing(Field::getName);
        int indentSize = wrap && this.allowWrap() ? this.indentSize() : 0;
        String str = Utils.indent(Stream.of(this.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(FieldProperty.class))
                .sorted(sortBy)
                .map(f -> Utils.convertValue(f, this, wrap && this.allowWrap(), this.wrapChar(), indentSize))
                .filter(Objects::nonNull)
                .collect(Collectors.joining(wrap ? "," + wrapChar() : ", ")), indentSize);
        return tplFormat == null ? str : String.format(tplFormat, str);
    }

    default int indentSize() {
        return 2;
    }

    default Boolean allowWrap() {
        return true;
    }

    default String templateFormat(boolean wrap) {
        return null;
    }

    default Boolean sortByName() {
        return false;
    }

    default String wrapChar() {
        return this.allowWrap() ? "\n" : "";
    }
}
