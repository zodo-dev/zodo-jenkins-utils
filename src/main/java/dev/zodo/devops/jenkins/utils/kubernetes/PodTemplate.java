package dev.zodo.devops.jenkins.utils.kubernetes;


import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldProperty;
import dev.zodo.devops.jenkins.utils.kubernetes.enums.MergeStrategy;
import dev.zodo.devops.jenkins.utils.kubernetes.enums.PodRetention;
import dev.zodo.devops.jenkins.utils.kubernetes.interfaces.Volume;
import dev.zodo.devops.jenkins.utils.kubernetes.interfaces.WorkspaceVolume;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Setter
@Accessors(prefix = "", fluent = true)
@Data(staticConstructor = "of")
public class PodTemplate implements BuildString {
    Boolean sortByName = true;
    Boolean allowWrap = true;

    @FieldProperty
    String inheritFrom = "default";

    @FieldProperty
    Integer activeDeadlineSeconds;

    @FieldProperty
    @Setter(AccessLevel.NONE)
    Map<String, String> annotations;

    @FieldProperty
    String cloud;

    @FieldProperty
    @Setter(AccessLevel.NONE)
    List<Container> containers;

    @FieldProperty
    EnvVars envVars;

    @FieldProperty
    Boolean hostNetwork;

    @FieldProperty
    List<String> imagePullSecrets;

    @FieldProperty
    Integer instanceCap;

    @FieldProperty
    String name;

    @FieldProperty
    String namespace;

    @FieldProperty
    String nodeSelector;

    @FieldProperty
    String nodeUsageMode;

    @FieldProperty
    PodRetention podRetention;

    @FieldProperty
    String runAsGroup;

    @FieldProperty
    String runAsUser;

    @FieldProperty
    String schedulerName;

    @FieldProperty
    String serviceAccount;

    @FieldProperty
    Boolean showRawYaml;

    @FieldProperty
    String slaveConnectTimeout;

    @FieldProperty
    String supplementalGroups;

    @FieldProperty
    List<Volume> volumes;

    @FieldProperty
    String workingDir;

    @FieldProperty
    WorkspaceVolume workspaceVolume;

    @FieldProperty
    String yaml;

    @FieldProperty
    MergeStrategy yamlMergeStrategy = MergeStrategy.MERGE;

    String label = "POD_LABEL";

    public PodTemplate container(Container container) {
        if (containers == null) {
            containers = new ArrayList<>();
        }
        if (container.sortByName == null) {
            container.sortByName(sortByName);
        }
        if (container.allowWrap == null) {
            container.allowWrap(allowWrap);
        }
        containers.add(container);
        return this;
    }

    public Container useContainer(String name) {
        if (containers == null) {
            containers = new ArrayList<>();
        }
        return containers.stream()
                .filter(cb -> cb.name.equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }

    public PodTemplate volume(Volume volume) {
        if (volumes == null) {
            volumes = new ArrayList<>();
        }
        volumes.add(volume);
        return this;
    }

    public PodTemplate annotation(String key, String value) {
        if (annotations == null) {
            annotations = new LinkedHashMap<>();
        }
        annotations.put(key, value);
        return this;
    }

    @Override
    public String templateFormat(boolean wrap) {
        String wrapChar = wrap ? wrapChar() : "";
        return String.format("podTemplate([%s%%s%s])", wrapChar, wrapChar);
    }

}
