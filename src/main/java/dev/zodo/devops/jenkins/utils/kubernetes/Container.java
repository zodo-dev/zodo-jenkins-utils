package dev.zodo.devops.jenkins.utils.kubernetes;

import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Setter
@Accessors(fluent = true, prefix = "")
@Data(staticConstructor = "of")
public class Container implements BuildString {
    Boolean sortByName;
    Boolean allowWrap;
    @FieldProperty
    final String name;
    @FieldProperty
    String image;
    @FieldProperty
    Boolean alwaysPullImage;
    @FieldProperty
    String args;
    @FieldProperty
    String command;
    @FieldProperty
    EnvVars envVars;
    // TODO Implement
    //@FieldProperty
    //LivenessProbe livenessProbe;
    @FieldProperty
    @Setter(AccessLevel.NONE)
    List<Port> ports;
    @FieldProperty
    Boolean privileged;
    @FieldProperty
    String resourceLimitCpu;
    @FieldProperty
    String resourceLimitEphemeralStorage;
    @FieldProperty
    String resourceLimitMemory;
    @FieldProperty
    String resourceRequestCpu;
    @FieldProperty
    String resourceRequestEphemeralStorage;
    @FieldProperty
    String resourceRequestMemory;
    @FieldProperty
    String runAsGroup;
    @FieldProperty
    String runAsUser;
    @FieldProperty
    String shell;
    @FieldProperty
    Boolean ttyEnabled;
    @FieldProperty
    String workingDir;

    public Container port(String portName, Integer containerPort, Integer hostPort) {
        if (ports == null) {
            ports = new ArrayList<>();
        }
        Port port = Port.of()
                .sortByName(sortByName)
                .name(portName)
                .containerPort(containerPort)
                .hostPort(hostPort);
        ports.add(port);
        return this;
    }

    @Override
    public String templateFormat(boolean wrap) {
        String wrapChar = wrap ? wrapChar() : "";
        return String.format("containerTemplate(%s%%s%s)", wrapChar, wrapChar);
    }
}
