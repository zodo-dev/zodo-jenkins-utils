package dev.zodo.devops.jenkins.utils.kubernetes;

public interface Volume extends BuildString {
    @Override
    default Boolean allowWrap() {
        return false;
    }
}
