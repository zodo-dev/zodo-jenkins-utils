package dev.zodo.devops.jenkins.utils.kubernetes;

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
    boolean sortByName;
    @FieldProperty
    final String name;
    @FieldProperty
    String image;
    @FieldProperty
    Boolean alwaysPullImage;
    @FieldProperty
    String args = "2d";
    @FieldProperty
    String command = "sleep";
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
    public String templateFormat() {
        return "containerTemplate(\n%s\n)";
    }
}
