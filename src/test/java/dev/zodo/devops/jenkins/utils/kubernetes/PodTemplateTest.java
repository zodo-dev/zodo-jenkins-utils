package dev.zodo.devops.jenkins.utils.kubernetes;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PodTemplateTest {

    @ParameterizedTest(name = "testPodTemplate wrap: {0}")
    @ValueSource(booleans = {false, true})
    public void testPodTemplate(boolean wrap) {
        PodTemplate podTemplate = PodTemplate.of()
                .sortByName(true)
                .allowWrap(wrap);
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
                .volume(EmptyDirVolume.of()
                        .mountPath("abc")
                        .memory(false))
                .volume(ConfigMapVolume.of()
                        .mountPath("abc")
                        .subPath("/"))
                .annotation("name", "John")
                .annotation("lastName", "Wick")
        ;
        System.out.println(podTemplate.buildString(wrap));
    }

}