package dev.zodo.devops.jenkins.utils.kubernetes;

import org.junit.jupiter.api.Test;

class ContainerTest {
    @Test
    public void testPodTemplate() {
        Container container = Container.of("meteor")
                .command("cmd")
                .args("2d")
                .privileged(true)
                .port("http", 80, 8080)
                .port("https", 443, 8443);
        System.out.println(container.buildString());
    }
}