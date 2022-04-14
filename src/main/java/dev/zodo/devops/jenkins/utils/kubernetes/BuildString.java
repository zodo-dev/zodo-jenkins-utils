package dev.zodo.devops.jenkins.utils.kubernetes;

import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldProperty;
import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldPropertyNotImplementedYet;
import dev.zodo.devops.jenkins.utils.kubernetes.data.KeyValueData;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface BuildString {

    default Logger logger() {
        return Logger.getLogger(this.getClass().getName());
    }

    default String buildString(boolean wrap) {
        String tplFormat = templateFormat(wrap);
        Comparator<KeyValueData> sortBy = Boolean.FALSE.equals(this.sortByName()) ? Utils.identityComparator : Comparator.comparing(KeyValueData::getKey);
        int indentSize = wrap && this.allowWrap() ? this.indentSize() : 0;
        warnFieldPropertyNotImplementedYet();
        String str = Utils.indent(getFieldProperty().stream()
                .sorted(sortBy)
                .map(kvd -> Utils.convertValue(kvd.getKey(), kvd.getValue(), wrap && this.allowWrap(), this.wrapChar(), indentSize))
                .filter(Objects::nonNull)
                .collect(Collectors.joining(wrap && this.allowWrap() ? "," + wrapChar() : ", ")), indentSize);
        return tplFormat == null ? str : String.format(tplFormat, str);
    }

    default List<KeyValueData> getFieldProperty() {
        return Stream.of(this.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(FieldProperty.class))
                .map(f -> KeyValueData.of(f.getName(), Utils.extractFieldValue(f, this)))
                .filter(kv -> kv.getValue() != null)
                .collect(Collectors.toList());
    }

    default List<String> getFieldPropertyNotImplementedYet() {
        return Stream.of(this.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(FieldPropertyNotImplementedYet.class))
                .filter(f -> Utils.extractFieldValue(f, this) != null)
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    default void warnFieldPropertyNotImplementedYet() {
        List<String> fieldsNotImplement = getFieldPropertyNotImplementedYet();
        if (!fieldsNotImplement.isEmpty()) {
            logger().warning(String.format("Fields not implemented yet: [%s]", String.join(",", fieldsNotImplement)));
        }
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
