package dev.zodo.devops.jenkins.utils.kubernetes;

import lombok.Builder;

import java.util.Map;

@Builder
public class EnvVars {
    Map<String, String> containerEnvVar;
    Map<String, String> envVar;
    Map<String, String> podEnvVar;
    // TODO implement
    //Map<String, String> secretEnvVar;

}
