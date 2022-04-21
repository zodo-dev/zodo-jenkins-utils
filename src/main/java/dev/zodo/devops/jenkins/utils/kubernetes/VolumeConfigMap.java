package dev.zodo.devops.jenkins.utils.kubernetes;

import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldProperty;
import dev.zodo.devops.jenkins.utils.kubernetes.interfaces.Volume;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true, prefix = "")
@Data(staticConstructor = "of")
public class VolumeConfigMap implements Volume {

    private final String dslName = "configMapVolume";

    @FieldProperty
    String mountPath;

    @FieldProperty
    String configMapName;

    @FieldProperty
    String subPath;

    @FieldProperty
    Boolean optional;
}
