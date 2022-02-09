<h3 align="center">Real-Estatetor</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]() 
  [![GitHub Issues](https://img.shields.io/github/issues/Jordi-Jaspers/Real-Estatetor.svg)](https://github.com/kylelobo/The-Documentation-Compendium/issues)
  [![GitHub Pull Requests](https://img.shields.io/github/issues-pr/Jordi-Jaspers/Real-Estatetor.svg)](https://github.com/kylelobo/The-Documentation-Compendium/pulls)
  [![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)

</div>

---
### Biography  

**Authors:**  
Jordi Jaspers [[Github](https://github.com/Jordi-Jaspers "Github Page"), [Linkedin](https://www.linkedin.com/in/jordi-jaspers/ "Linkedin Page")]  
  
**Date of initial commit:** 24/01/2022

**Description:**  
A simple Real-estate website GUI made in React and tailwind CSS. This will provide as the foundation for solidity and micronaut experiments.
---

## 📝 Table of Contents
- [Application](#application)
- [Notes](#notes)
- [Stack](#stack)
- [References](#references)

## Application <a name = "application"></a>
The application must have the following endpoints:

...

## Notes <a name = "notes"></a>
* Hawaii framework depends on Spring MVC dependencies, which will pull spring framework dependencies. Not very effecient for micronaut's native image support via graalVM.

```groovy
    // ======= HAWAII FRAMEWORK =======

    // contains the autoconfiguration for Hawaii logging.
    implementation(group = "org.hawaiiframework", name = "hawaii-starter-logging", version = "3.0.0.M24")

    // provides the core of hawaii framework such as the response entity exception handling.
    implementation("org.hawaiiframework", name = "hawaii-core", version = "3.0.0.M24")

    // provides an async framework which enables us to control query and rest endpoints timeouts.
    implementation("org.hawaiiframework", name = "hawaii-async", version = "3.0.0.M24")

    // contains the autoconfiguration of the exception mapping to http error codes. Also provides for JSON-org dependency.
    implementation(group = "org.hawaiiframework", name = "hawaii-starter-rest", version = "3.0.0.M24")

    // Provides basic Spring MVC capabilities, and is necessary for hawaii-framework.
    // To indicate this is a web/rest application, but do not load tomcat, we will replace it with Jetty later
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web", version = "2.6.2") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }

    // JCL 1.2 implemented over SLF4J
    // https://mvnrepository.com/artifact/org.slf4j/jcl-over-slf4j
    implementation("org.slf4j:jcl-over-slf4j:1.7.35")
```

* Java version 'sdk install java  22.0.0.2.r17-grl' needs to be installed. sdkman
* setup IDE config to gradle jdk 17-grl -> prefences -> build, execution, and deployment -> build tools -> gradle -> jdk
* set project structure to jdk 17-grl
* Small community, debugging is harder.
* Quarkus has no groovy support and is also slower then Micronaut.
* Security is not an out of the box feature like in spring boot.
* 

## ⛏️ Stack <a name = "stack"></a>
- [Micronaut](https://micronaut.io/) - Microservices framework for Spring Boot
- [Git](https://git-scm.com/) - Version Control
- [Solidity](https://soliditylang.org/) - Ethereum Smart Contract
- [React](https://reactjs.org/) - Frontend Framework

## ✍️ References <a name = "references"></a>
* Troubleshooting: <https://stackoverflow.com/>
* Spring Boot: <https://spring.io/projects/spring-boot>
* React course (Brad Traversy): <https://www.youtube.com/watch?v=w7ejDZ8SWv8>
* Git Ignore generator:<https://www.toptal.com/developers/gitignore>
* Micronaut Docs: <https://micronaut.io/docs/>
* Reactive DB Micronaut: <https://medium.com/javarevisited/reactive-database-access-with-r2dbc-micronaut-and-graalvm-ee9b5853260>
* GraalVM: <https://www.graalvm.org/>
* Solidity Docs: <https://solidity.readthedocs.io/en/develop/getting-started.html>
* Heroicons (icon resource): <https://heroicons.com/>
* Unsplash (image Resource): <https://unsplash.com/>
* Micronaut R2DBC guide: <https://micronaut-projects.github.io/micronaut-r2dbc/1.0.x/guide/>
