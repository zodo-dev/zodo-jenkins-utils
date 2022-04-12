package dev.zodo.devops.jenkins.utils.kubernetes;

import lombok.*;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true, prefix = "")
@Data(staticConstructor = "of")
public class DynamicPvcVolume implements Volume {
    @FieldProperty
    String mountPath;
    @FieldProperty
    String accessModes;
    @FieldProperty
    String requestsSize;
    @FieldProperty
    String storageClassName;
}
