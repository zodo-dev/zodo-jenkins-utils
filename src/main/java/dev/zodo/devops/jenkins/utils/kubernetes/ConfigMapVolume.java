package dev.zodo.devops.jenkins.utils.kubernetes;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true, prefix = "")
@Data(staticConstructor = "of")
public class ConfigMapVolume implements Volume {
    @FieldProperty
    String mountPath;
    @FieldProperty
    String configMapName;
    @FieldProperty
    String subPath;
    @FieldProperty
    Boolean optional;
}
