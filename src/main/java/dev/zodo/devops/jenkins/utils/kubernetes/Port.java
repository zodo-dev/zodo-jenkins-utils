package dev.zodo.devops.jenkins.utils.kubernetes;

import lombok.Builder;

@Builder
public class Port implements BuildString {
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
