package dev.zodo.devops.jenkins.utils.kubernetes;

import org.junit.jupiter.api.Test;

class PodTemplateTest {

    @Test
    public void testPodTemplate() {
        PodTemplate podTemplate = new PodTemplate();
        podTemplate.container("meteor")
                .command("cmd")
                .args("2d")
                .privileged(true)
                .port("http", 80, 8080)
                .port("https", 443, 8443)
        ;
        podTemplate.container("docker")
                .command("cmd")
                .args("2d")
                .privileged(true)
                .port("http", 80, 8080)
                .port("https", 443, 8443)
        ;
        podTemplate
                .volume(EmptyDirVolume.of().mountPath("abc").memory(false))
                .volume(ConfigMapVolume.of().mountPath("abc").subPath("/"))
        ;
        System.out.println(podTemplate.buildString(true));
    }

}