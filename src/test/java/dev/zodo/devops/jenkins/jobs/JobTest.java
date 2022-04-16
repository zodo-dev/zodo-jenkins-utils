package dev.zodo.devops.jenkins.jobs;

import dev.zodo.devops.jenkins.utils.kubernetes.Container;
import dev.zodo.devops.jenkins.utils.kubernetes.PodTemplate;
import hudson.EnvVars;
import hudson.model.UpdateCenter;
import hudson.slaves.EnvironmentVariablesNodeProperty;
import javaposse.jobdsl.plugin.JenkinsJobManagement;
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Ignore
public class JobTest {

    //@Rule
    @ClassRule
    public static JenkinsRule jenkinsRule = new JenkinsRule();

    @Test
    public void jobPodTemplateContainerTest() throws IOException, ExecutionException, InterruptedException {
        PodTemplate podTemplate = PodTemplate.of().container(Container.of("busybox"));
        String filename = writeScriptTest("jobPodTemplateContainerTest.groovy",
                podTemplate.buildString(true));
//        Script script = loadScript(filename);
//        script.run();
        jobTest(JobTest.class.getResource("/jobs/sample.groovy").getFile());
        //PodTemplate;
        //jobTest(filename);
    }

    private void jobTest(String filename) throws IOException, ExecutionException, InterruptedException {
        EnvironmentVariablesNodeProperty prop = new EnvironmentVariablesNodeProperty();
        EnvVars env = prop.getEnvVars();
        env.put("DEPLOY_TARGET", "staging");
        jenkinsRule.jenkins.getGlobalNodeProperties().add(prop);
        UpdateCenter updateCenter = jenkinsRule.jenkins.getUpdateCenter();
        updateCenter.updateAllSites();
        Objects.requireNonNull(updateCenter.getPlugin("kubernetes")).doInstallNow();

        JenkinsJobManagement jobManagement = new JenkinsJobManagement(System.out, env, new File("."));
        WorkflowJob project = jenkinsRule.createProject(WorkflowJob.class, "test-pipeline");
        project.setDefinition(new CpsFlowDefinition(loadDataFromTestFile(filename), false));
        //DslScriptLoader dslScriptLoader = new DslScriptLoader(jobManagement);

        //dslScriptLoader.runScript(loadDataFromTestFile(filename));
        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(120000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        run.run();
        System.out.println("");
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
