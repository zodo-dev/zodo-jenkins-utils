package dev.zodo.devops.jenkins.utils.kubernetes.interfaces;

import dev.zodo.devops.jenkins.utils.kubernetes.BuildString;

public interface Volume extends BuildString {
    String dslName();

    @Override
    default Boolean allowWrap() {
        return false;
    }

    @Override
    default String templateFormat(boolean wrap) {
        String wrapChar = wrap ? wrapChar() : "";
        return String.format("%s(%s%%s%s)", dslName(), wrapChar, wrapChar);
    }
}
