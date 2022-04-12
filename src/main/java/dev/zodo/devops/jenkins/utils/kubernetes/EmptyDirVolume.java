package dev.zodo.devops.jenkins.utils.kubernetes;

import lombok.*;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true, prefix = "")
@Data(staticConstructor = "of")
public class EmptyDirVolume implements Volume {
    @FieldProperty
    String mountPath;
    @FieldProperty
    Boolean memory = false;
}
