package dev.zodo.devops.jenkins.utils.kubernetes;

import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldProperty;
import dev.zodo.devops.jenkins.utils.kubernetes.interfaces.WorkspaceVolume;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true, prefix = "")
@Data(staticConstructor = "of")
public class WorkspaceVolumeEmptyDir implements WorkspaceVolume {

    private final String dslName = "emptyDirWorkspaceVolume";

    @FieldProperty
    Boolean memory = false;
}
