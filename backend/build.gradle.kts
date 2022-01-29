// Imports:
import kotlin.text.Charsets.UTF_8

// Both 'group' and 'version' are default Gradle properties, so they need to be set here
group = "com.realestatetor"
version = "0.0.1-SNAPSHOT"

// Other, non-default Gradle, properties need to be defined here
val artifactName = "real-estatetor"
val artifactDescription = "Micronaut application for Real Estate"
var vendor = "jordi-jaspers"
val user = null
val pwd = null

/**
 * Setting the metadata for the project.
 */
application {
    applicationName = artifactName
    version = project.version.toString()
    group = project.group.toString()
    description = artifactDescription
    mainClass.set("com.realestatetor.Application")
}

/**
 * Create manifest settings.
 * ShadowJar is the spring boot jar alternative to the manifest.
 */
tasks.getByName<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    manifest {
        attributes("Specification-Title" to artifactDescription)
        attributes("Specification-Vendor" to vendor)

        attributes("Name" to artifactDescription)
        attributes("Implementation-Title" to artifactDescription)
        attributes("Implementation-Version" to project.version.toString())
        attributes("Implementation-Vendor" to vendor)
    }
}

/**
 * Java 17 is long term supported and therefore chosen as the default.
 */
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

/**
 * The repositories used to download the dependencies.
 */
repositories {
    mavenLocal()
    mavenCentral()
}

/**
 * Project Plugins
 */
plugins {
    // Quality plugin for checkstyle.
    id("checkstyle")

    // Quality plugin for PMD.
    id("pmd")

    // Adds intelliJ tasks to the build file and creates the settings in intellij correctly
    id("idea")

    // Our tests are using groovy with the spock framework, this adds the compileTestGroovy task and the watch to the test/groovy folder
    id("groovy")

    // non standard plugins quality plugins for gradle
    id("com.github.spotbugs") version "4.7.2"

    // this plugin adds the integration test task to our build file with the default 'itest' directory.
    // see https://github.com/Softeq/itest-gradle-plugin
    id("com.softeq.gradle.itest") version "1.0.4"

    // embedded java code coverage by testing, formerly known as EclEmma.
    id("jacoco")

    // The project-report plugin provides file reports on dependencies, tasks, etc.
    // See https://docs.gradle.org/current/userguide/project_report_plugin.html.
    id("project-report")

    // Gradle plugin for creating fat/uber JARs with support for package relocation.
    // see https://github.com/johnrengelman/shadow
    id("com.github.johnrengelman.shadow") version "7.1.1"

    // Gradle Plugins for Micronaut
    // https://micronaut.io/
    id("io.micronaut.application") version "3.2.0"
}

/**
 * The dependencies of the project.
 */
dependencies {
    // ======= COMPILE ONLY DEPENDENCIES =======

    // This brings in the SuppressFBWarnings
    compileOnly(group = "com.github.spotbugs", name = "spotbugs-annotations", version = "4.3.0")

    // ======= RUNTIME ONLY DEPENDENCIES =======

    // Reactive Database protocol
    runtimeOnly(group = "io.r2dbc", name = "r2dbc-postgresql")

    // ======= ANNOTATION PROCESSORS =======

    // Provides Micronaut's specific annotations.
    annotationProcessor(group = "io.micronaut", name = "micronaut-http-validation")
    annotationProcessor(group = "io.micronaut.data", name = "micronaut-data-processor")

    // Provides annotations used by Mapstruct.
    annotationProcessor(group = "org.mapstruct", name = "mapstruct-processor", version = "1.4.2.Final")

    // ======= IMPLEMENTATION DEPENDENCIES =======

    //Necessary Micronaut dependencies
    implementation(group = "io.micronaut", name = "micronaut-http-client")
    implementation(group = "io.micronaut", name = "micronaut-jackson-databind")
    implementation(group = "io.micronaut", name = "micronaut-runtime")
    implementation(group = "io.micronaut", name = "micronaut-validation")

    // Reactive Datasource connection dependencies.
    implementation(group = "io.micronaut.r2dbc", name = "micronaut-r2dbc-core")

    // ORM-library for Micronaut
    implementation(group = "io.micronaut.sql", name = "micronaut-hibernate-jpa")

    // Jakarta Annotations defines a collection of annotations representing common semantic concepts that enable a declarative style of programming that applies across a variety of Java technologies.
    implementation(group = "jakarta.annotation", name = "jakarta.annotation-api")

    // mapstruct is used to generate code to map from domain model classes to rest application model classes
    implementation(group = "org.mapstruct", name = "mapstruct", version = "1.4.2.Final")

    // Logstash encoder for logging in JSON format
    implementation(group = "net.logstash.logback", name = "logstash-logback-encoder", version = "6.3")

    // Netty is an asynchronous event-driven network application framework for rapid development of maintainable high performance protocol servers & clients.
    implementation(group = "io.projectreactor.netty", name = "reactor-netty", version = "1.0.15")

    // General data-binding functionality for Jackson: works on core streaming API
    implementation(group = "com.fasterxml.jackson.core", name = "jackson-databind", version = "2.13.1")

    // Apache Commons Lang, a package of Java utility classes for the classes that are in java.lang's hierarchy.
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation(group = "org.apache.commons", name = "commons-lang3", version = "3.12.0")

    // ======= TEST DEPENDENCIES =======

    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:r2dbc")
    testImplementation("org.testcontainers:spock")
    testImplementation("org.testcontainers:testcontainers")

    // due to the dependency to spock, we also need groovy
    testImplementation(group = "org.codehaus.groovy", name = "groovy-all", version = "3.0.8")

    // www.spockframework.org is the groovy based test framework providing the specifications for our tests.
    testImplementation(group = "org.spockframework", name = "spock-core", version = "2.1-M2-groovy-3.0")

    // Spock Spring bindings
    // See https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing-spring-boot-applications-with-spock
    testImplementation(group = "org.spockframework", name = "spock-spring", version = "2.1-M2-groovy-3.0")

    // Reactor Test support.
    testImplementation(group = "io.projectreactor", name = "reactor-test", version = "3.4.14")
}

/**
 * Make the itest source set contain the main and test classpath, so we can use test (helper) code in itest as well.
 */
itestSourceSet {
    compileClasspath = sourceSets["main"].compileClasspath +
            sourceSets["main"].output +
            sourceSets["test"].compileClasspath +
            sourceSets["test"].output
    runtimeClasspath = sourceSets["main"].runtimeClasspath +
            sourceSets["main"].output +
            sourceSets["test"].runtimeClasspath +
            sourceSets["test"].output
}

/**
 * Override default java compiler settings
 */
tasks.withType<JavaCompile> {
    // override default false
    options.isDeprecation = true
    // defaults to use the platform encoding
    options.encoding = UTF_8.name()
    // add Xlint to our compiler options (but disable processing because of Spring warnings in code)
    // and make warnings be treated like errors
    options.compilerArgs.addAll(arrayOf("-Xlint:all", "-Xlint:-processing", "-Werror"))
}

/**
 * Spock 2 uses the new Junit platform to execute the tests.
 * @see "https://discuss.gradle.org/t/spock-tests-dont-run-with-gradle-7-groovy-3/40139"
 */
tasks.withType<Test> {
    useJUnitPlatform()
}

/**
 * Configuration of checkstyle.
 */
checkstyle {
    // The checkstyle configuration file.
    configFile = file("config/checkstyle/checkstyle.xml")
}

/**
 * Configuration of PMD.
 */
pmd {
    // as a development team we want pmd failures to break the build and keep the code clean.
    isIgnoreFailures = false
    // directly show the failures in the output
    isConsoleOutput = true
    // the configuration of the custom rules
    ruleSetConfig = resources.text.fromFile(projectDir.path + "/config/pmd/pmd.xml")
    // clear the default list of rules, otherwise this will override our custom configuration.
    ruleSets = listOf<String>()
}

/**
 * Configuration of spotbugs with our exclusion configuration to enable the report format on CI in XML and local in HTML.
 */
spotbugs {
    val format = findProperty("spotbugsReportFormat")
    val xmlFormat = (format == "xml")
    showProgress.set(true)
    excludeFilter.set(project.file("config/spotbugs/exclude.xml"))

    tasks.spotbugsMain {
        reports.create("html") {
            isEnabled = !xmlFormat
        }
        reports.create("xml") {
            isEnabled = xmlFormat
        }
    }
    tasks.spotbugsTest {
        reports.create("html") {
            isEnabled = !xmlFormat
        }
        reports.create("xml") {
            isEnabled = xmlFormat
        }

    }
}

/**
 * Generates test report after test task.
 * Produces 'jacocoTestReport.xml' in `build/reports/jacoco/test/`.
 */
tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

/**
 * Make sure the report is in XML format, instead of binary.
 */
tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("spock2")
    processing {
        incremental(true)
        annotations("com.realestatetor.*")
    }
}
