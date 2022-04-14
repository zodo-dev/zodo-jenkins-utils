package dev.zodo.devops.jenkins.utils.kubernetes;

import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldProperty;
import dev.zodo.devops.jenkins.utils.kubernetes.interfaces.Volume;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true, prefix = "")
@Data(staticConstructor = "of")
public class VolumeDynamicPvc implements Volume {
    @FieldProperty
    String mountPath;

    @FieldProperty
    String accessModes;

    @FieldProperty
    String requestsSize;

    @FieldProperty
    String storageClassName;
}
