package dev.zodo.devops.jenkins.utils.kubernetes;

import dev.zodo.devops.jenkins.utils.kubernetes.enums.MergeStrategy;
import dev.zodo.devops.jenkins.utils.kubernetes.enums.PodRetention;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

class PodTemplateTest {

    @ParameterizedTest(name = "testPodTemplate wrap: {0}")
    @ValueSource(booleans = {false, true})
    public void testPodTemplate(boolean wrap) {
        PodTemplate podTemplate = PodTemplate.of()
                .sortByName(true)
                .allowWrap(wrap)
                .yamlMergeStrategy(MergeStrategy.OVERRIDE)
                .podRetention(PodRetention.ON_FAILURE);
        podTemplate
                .container(
                        Container.of("meteor")
                                .command("cmd")
                                .args("2d")
                                .privileged(true)
                                .port("http", 80, 8080)
                                .port("https", 443, 8443))
                .container(Container.of("docker")
                        .command("cmd")
                        .args("2d")
                        .privileged(true)
                        .port("http", 80, 8080)
                        .port("https", 443, 8443))
                .volume(VolumeEmptyDir.of()
                        .mountPath("abc")
                        .memory(false))
                .volume(VolumeConfigMap.of()
                        .mountPath("abc")
                        .subPath("/"))
                .workspaceVolume(WorkspaceVolumeEmptyDir.of().memory(true))
                .annotation("name", "John")
                .annotation("lastName", "Wick")
                .envVars(EnvVars.of()
                        .containerEnvVar(EnvVar.of("CONTAINER_ENV", "ABC"))
                        .envVar(EnvVar.of("ENV", "ABC"))
                        .podEnvVar(EnvVar.of("POD_ENV", "ABC"))
                )
        ;
        System.out.println(podTemplate.buildString(wrap));
    }

    @Test
    public void testPodEnvVar() {
        PodTemplate podTemplate = PodTemplate.of()
                .sortByName(true)
                .allowWrap(false)
                .yamlMergeStrategy(MergeStrategy.OVERRIDE)
                .podRetention(PodRetention.ON_FAILURE);
        podTemplate
                .envVars(EnvVars.of()
                        .containerEnvVar(EnvVar.of("CONTAINER_ENV", "ABC"))
                        .envVar(EnvVar.of("ENV", "ABC"))
                        .podEnvVar(EnvVar.of("POD_ENV", "ABC"))
                )
        ;
        System.out.println(podTemplate.buildString(false));
    }

    @Test
    public void testPodContainer() {
        PodTemplate podTemplate = PodTemplate.of()
                .sortByName(true)
                .allowWrap(true);
        podTemplate
                .container(
                        Container.of("meteor")
                                .command("cmd")
                                .args("2d")
                                .privileged(true)
                                .port("http", 80, 8080)
                                .port("https", 443, 8443))
                .container(Container.of("docker")
                        .command("cmd")
                        .args("2d")
                        .privileged(true)
                        .port("http", 80, 8080)
                        .port("https", 443, 8443))
        ;
        System.out.println(podTemplate.buildString(true));
    }


    @Test
    public void checkFieldsNorImplementedYet() {
        String[] expected = new String[]{"notImplemented"};
        List<String> notImplementedFields = ClassWithFieldNotImplemented.of()
                .notImplemented("notImplemented")
                .implemented("implemented")
                .getFieldPropertyNotImplementedYet();
        assertThat(notImplementedFields, containsInAnyOrder(expected));
    }


}