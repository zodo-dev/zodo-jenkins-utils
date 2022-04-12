Jenkins Utilities
==========
[![License (LGPL version 3)](https://img.shields.io/badge/license-GNU%20LGPL%20version%203.0-blue.svg)](https://github.com/zodo-dev/zodo-jenkins-utils/blob/develop/LICENCE)
![Auto build CI](https://github.com/zodo-dev/zodo-jenkins-utils/workflows/Auto%20build%20CI/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=zodo-dev%3Azodo-jenkins-utils&metric=alert_status)](https://sonarcloud.io/dashboard?id=zodo-dev%3Azodo-jenkins-utils)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=zodo-dev%3Azodo-jenkins-utils&metric=coverage)](https://sonarcloud.io/dashboard?id=zodo-dev%3Azodo-jenkins-utils)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.zodo/zodo-jenkins-utils/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.zodo/zodo-jenkins-utils/)
[![Javadoc](http://www.javadoc.io/badge/dev.zodo/zodo-jenkins-utils.svg)](http://www.javadoc.io/doc/dev.zodo/zodo-jenkins-utils)

---
Jenkins utilities

Documentation
---

Doc: https://zodo-dev.github.io/zodo-jenkins-utils/

Wiki: https://github.com/zodo-dev/zodo-jenkins-utils/wiki

Using zodo-jenkins-utils
---

Maven:

``` xml
<dependency>
  <groupId>dev.zodo</groupId>
  <artifactId>zodo-jenkins-utils</artifactId>
  <version>1.0.0</version>
</dependency>
```

Gradle:

```groovy
compile "dev.zodo:zodo-jenkins-utils:1.0.0"
```

If you want to use snapshots first config OSS Sonatype Snapshots repository:

Maven:

``` xml
<repositories>
    <repository>
        <id>oss-snapshots</id>
        <name>OSS Snapshots</name>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

Gradle:

```groovy
repositories {
    maven {
        url 'https://oss.sonatype.org/content/repositories/snapshots'
    }
}
```

And then the dependency:

``` xml
<dependency>
  <groupId>dev.zodo</groupId>
  <artifactId>zodo-jenkins-utils</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

Gradle:

```groovy
compile 'dev.zodo:zodo-jenkins-utils:1.0.0-SNAPSHOT'
```

Documentation and samples
---

For documentation and samples check out our [wiki](https://github.com/zodo-dev/zodo-jenkins-utils/wiki)

Need help or found an issue?
---

When reporting an issue through the [issue tracker](https://github.com/zodo-dev/zodo-jenkins-utils/issues?state=open)
on GitHub, please use the following guidelines:

* Check existing issues to see if it has been addressed already
* The version of zodo-jenkins-utils you are using
* A short description of the issue you are experiencing and the expected outcome
* Description of how someone else can reproduce the problem
* Paste error output or logs in your issue or in a Gist. If pasting them in the GitHub issue, wrap 
it in three backticks: ```  so that it renders nicely
* Write a unit test to show the issue!

License
---

This project and its documentation are licensed under the LGPL license. Refer to [LICENSE](LICENSE) for more information.
