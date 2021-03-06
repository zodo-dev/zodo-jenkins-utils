package dev.zodo.devops.jenkins.utils.kubernetes;

import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true, prefix = "")
@Data(staticConstructor = "of")
public class Port implements BuildString {
    Boolean sortByName;
    Boolean allowWrap = false;
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

}
