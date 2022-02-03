/**
 * Build script for the Kotlin Gradle plugin.
 */
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(group = "org.liquibase", name = "liquibase-gradle-plugin", version = "2.0.4")
    }
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
    // Liquibase Plugin
    id("org.liquibase.gradle") version "2.1.1"
}

/**
 * The dependencies of the project.
 */
dependencies {
    // Load the dependencies needed for liquibase to run (changelog parsers and JDBC drivers)
    liquibaseRuntime(group = "org.liquibase", name = "liquibase-core", version = "4.3.5")
    liquibaseRuntime(group = "org.yaml", name = "snakeyaml", version = "1.29")
    liquibaseRuntime(group = "org.postgresql", name = "postgresql", version = "42.3.1")
}

/**
 * Configure the Liquibase plugin with passed properties.
 */
project.ext["env"] = System.getProperty("env")
when {
    project.ext["env"] == "custom" -> {
        // Env: custom - Allows all variables to be set as System properties
        project.ext["dbUrl"] = System.getProperty("dbUrl")
        project.ext["dbUsername"] = System.getProperty("dbUsername")
        project.ext["dbPassword"] = System.getProperty("dbPassword")
        project.ext["contexts"] = System.getProperty("contexts")
        project.ext["outputFile"] = System.getProperty("outputFile")
        project.ext["changeLogFile"] = "changelog/db.changelog-master.yaml"
    }
    else -> {
        // No env specified: Use the configs for local development
        project.ext["dbUrl"] = "jdbc:postgresql://localhost:5432/real-estatetor"
        project.ext["dbUsername"] = "jordi"
        project.ext["dbPassword"] = "postgrespwd"
        project.ext["contexts"] = "!testdata"
        project.ext["outputFile"] = "output/development.sql"
        project.ext["changeLogFile"] = "changelog/db.changelog-master.yaml"
    }
}

/**
 * The liquibase task.
 */
liquibase {
    activities.register("main") {
        this.arguments = mapOf(
            "logLevel" to "info",
            "changeLogFile" to project.ext["changeLogFile"],
            "url" to project.ext["dbUrl"],
            "username" to project.ext["dbUsername"],
            "password" to project.ext["dbPassword"],
            "contexts" to project.ext["contexts"],
            "outputFile" to project.ext["outputFile"],
            "outputDefaultCatalog" to false,
            "outputDefaultSchema" to false
        )
    }
    runList = "main"
}
