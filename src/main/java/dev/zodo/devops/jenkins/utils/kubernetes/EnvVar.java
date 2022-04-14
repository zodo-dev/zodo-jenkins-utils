package dev.zodo.devops.jenkins.utils.kubernetes;

import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true, prefix = "")
@Data(staticConstructor = "of")
public class EnvVar implements BuildString {
    Boolean allowWrap = false;
    @FieldProperty
    final String key;
    @FieldProperty
    final String value;
//    @Override
//    public String templateFormat(boolean wrap) {
//        String wrapChar = wrap ? wrapChar() : "";
//        return String.format("[%s%%s%s]", wrapChar, wrapChar);
//    }
}
