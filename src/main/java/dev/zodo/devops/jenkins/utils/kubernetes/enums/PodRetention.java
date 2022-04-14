package dev.zodo.devops.jenkins.utils.kubernetes.enums;

import dev.zodo.devops.jenkins.utils.kubernetes.interfaces.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PodRetention implements EnumValue {
    ALWAYS("always"),
    DEFAULT("default"),
    NEVER("never"),
    ON_FAILURE("onFailure");

    private final String value;
}
