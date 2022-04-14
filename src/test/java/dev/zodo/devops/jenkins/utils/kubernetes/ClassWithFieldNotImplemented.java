package dev.zodo.devops.jenkins.utils.kubernetes;

import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldProperty;
import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldPropertyNotImplementedYet;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true, prefix = "")
@Data(staticConstructor = "of")
class ClassWithFieldNotImplemented implements BuildString {
    @FieldPropertyNotImplementedYet
    String notImplemented;

    @FieldProperty
    String implemented;

}
