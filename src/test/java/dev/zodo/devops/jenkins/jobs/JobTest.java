package dev.zodo.devops.jenkins.jobs;

import dev.zodo.devops.jenkins.utils.kubernetes.Container;
import dev.zodo.devops.jenkins.utils.kubernetes.PodTemplate;
import hudson.EnvVars;
import hudson.slaves.EnvironmentVariablesNodeProperty;
import javaposse.jobdsl.dsl.DslScriptLoader;
import javaposse.jobdsl.plugin.JenkinsJobManagement;
import org.junit.Before;
import org.junit.Rule;

import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public final class JobTest {

    @Rule
    //@ClassRule
    public JenkinsRule jenkinsRule;

    @Before
    public void init() {
        jenkinsRule = new JenkinsRule();
    }

    @Test
//    @WithPlugin({
//            "authentication-tokens-1.3.hpi",
//            "cloudbees-folder-6.9.hpi",
//            "credentials-2.3.0.hpi",
//            "credentials-binding-1.12.hpi",
//            "docker-commons-1.14.hpi",
//            "durable-task-1.33.hpi",
//            "jackson2-api-2.10.2.hpi",
//            "kubernetes-1.25.0.hpi",
//            "kubernetes-client-api-4.8.0-1.hpi",
//            "kubernetes-credentials-0.6.2.hpi",
//            "plain-credentials-1.5.hpi",
//            "structs-1.20.hpi",
//            "variant-1.3.hpi",
//            "workflow-api-2.37.hpi",
//            "workflow-step-api-2.22.hpi"
//    })
    public void jobPodTemplateContainerTest() throws IOException, ExecutionException, InterruptedException {
        PodTemplate podTemplate = PodTemplate.of().container(Container.of("busybox"));
        String filename = writeScriptTest("jobPodTemplateContainerTest.groovy",
                podTemplate.buildString(true));

        jobTest(JobTest.class.getResource("/jobs/sample.groovy").getFile());
        //PodTemplate;
        //jobTest(filename);
    }

    private void jobTest(String filename) throws IOException, ExecutionException, InterruptedException {
        EnvironmentVariablesNodeProperty prop = new EnvironmentVariablesNodeProperty();
        EnvVars env = prop.getEnvVars();
        env.put("DEPLOY_TARGET", "staging");
        jenkinsRule.jenkins.getGlobalNodeProperties().add(prop);
       // UpdateCenter updateCenter = jenkinsRule.jenkins.getUpdateCenter();
        //updateCenter.updateAllSites();
        //Objects.requireNonNull(updateCenter.getPlugin("kubernetes")).doInstallNow();

        JenkinsJobManagement jobManagement = new JenkinsJobManagement(System.out, env, new File("."));
        DslScriptLoader dslScriptLoader = new DslScriptLoader(jobManagement);

        //dslScriptLoader.runScript(loadDataFromTestFile(filename));
    }

    static private String loadDataFromTestFile(String name) throws IOException {
        File fileData = new File(name);
        return new String(Files.readAllBytes(fileData.toPath()));
    }

    static String writeScriptTest(String fileName, String scriptData) throws IOException {
        File parent = new File(Objects.requireNonNull(JobTest.class.getResource("/jobs")).getFile(), "generated/");
        if (!parent.exists()) {
            parent.mkdirs();
        }
        File out = new File(parent, fileName);
        Files.write(out.toPath(), scriptData.getBytes(StandardCharsets.UTF_8));
        return out.getAbsolutePath();
    }

}
