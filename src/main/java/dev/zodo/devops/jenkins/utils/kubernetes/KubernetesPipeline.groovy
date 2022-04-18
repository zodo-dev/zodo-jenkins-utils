package dev.zodo.devops.jenkins.utils.kubernetes

class KubernetesPipeline {
  private PodTemplate podTemplate

  PodTemplate podTemplate() {
    if (this.podTemplate == null) {
      this.podTemplate = PodTemplate.of();
    }
    return this.podTemplate
  }
}
