package dev.zodo.devops.jenkins.utils.kubernetes.interfaces;

import dev.zodo.devops.jenkins.utils.kubernetes.BuildString;

public interface EnvVarType extends BuildString {
    @Override
    default Boolean allowWrap() {
        return false;
    }
}
