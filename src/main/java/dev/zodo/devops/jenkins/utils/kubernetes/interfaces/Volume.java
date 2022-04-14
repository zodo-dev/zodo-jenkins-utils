package dev.zodo.devops.jenkins.utils.kubernetes.interfaces;

import dev.zodo.devops.jenkins.utils.kubernetes.BuildString;

public interface Volume extends BuildString {
    @Override
    default Boolean allowWrap() {
        return false;
    }
}
