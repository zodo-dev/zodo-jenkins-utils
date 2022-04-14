package dev.zodo.devops.jenkins.utils.kubernetes.enums;

import dev.zodo.devops.jenkins.utils.kubernetes.interfaces.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MergeStrategy implements EnumValue {
    MERGE("merge"),
    OVERRIDE("override");

    private final String value;
}
