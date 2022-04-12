package dev.zodo.devops.jenkins.utils.kubernetes;

public interface Volume extends BuildString {
    @Override
    default boolean allowWrap() {
        return false;
    }
}
