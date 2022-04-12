package dev.zodo.devops.jenkins.utils.kubernetes;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true, prefix = "")
@Data(staticConstructor = "of")
public class Port implements BuildString {
    boolean sortByName;
    @FieldProperty
    String name;
    @FieldProperty
    Integer containerPort;
    @FieldProperty
    Integer hostPort;

    @Override
    public int indentSize() {
        return 0;
    }

    @Override
    public boolean allowWrap() {
        return false;
    }
}
