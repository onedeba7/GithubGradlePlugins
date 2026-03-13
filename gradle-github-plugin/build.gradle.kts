import org.gradle.plugin.compatibility.compatibility
plugins {

  
  
  // Apply the signing plugin (signing) if you want the published artifacts of your plugin be automatically signed.

    id ("java")
    id ("groovy")
    id ("com.gradle.plugin-publish")  
    id ("maven-publish") // Add version
    id ("java-gradle-plugin")
  //  id ("signing")
   // id("com.vanniktech.maven.publish")
    
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("org.codehaus.groovy.modules.http-builder:http-builder:0.7.1")
    implementation("org.zeroturnaround:zt-zip:1.8")
    testImplementation("junit:junit:4.11")
}


version = "1.0.0"
group = "io.github.onedeba7"
//artifact = "gradle-github-plugin"


// All properties are mandatory.
gradlePlugin {
  website.set("https://github.com/onedeba7/GithubGradlePlugins")
  vcsUrl.set("https://github.com/onedeba7/GithubGradlePlugins")
  plugins {
    create("GradleGithubPlugin") {
      id = "io.github.onedeba7.gradle-github-plugin"
      implementationClass = "io.github.onedeba7.gradle.GithubPlugin"
      displayName = "Gradle Greeting plugin"
      description = "Gradle plugin to say hello!"
      tags.set(listOf("github", "release", "releases"))

      compatibility { // with the publishing plugin v2.1.0+
        features {
          // Declare the feature supported.
          configurationCache = true
        }
      }
    }
  }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            // Maven coordinates: groupId:artifactId:version
              groupId = "io.github.onedeba7"           // Your group ID
              artifactId = "gradle-github-plugin"                  // Your artifact ID
              version = "1.0.0"                         // Library version

            
                from(components["java"])
            

            // Configure POM file
            pom {
                name.set("GradleGithubPlugin")
                description.set("Upload To GitHub Release.")
                inceptionYear.set("2026")
                url.set("https://github.com/onedeba7/GithubGradlePlugins/")
                
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                
                developers {
                    developer {
                      id.set("onedeba7")
                      name.set("Debacodex")
                      url.set("https://github.com/GithubGradlePlugins/")
                    }
                }
                
                scm {
                    url.set("https://github.com/onedeba7/GithubGradlePlugins/")
                    connection.set("scm:git:git://github.com/onedeba7/GithubGradlePlugins.git")
                    developerConnection.set("scm:git:ssh://git@github.com/onedeba7/GithubGradlePlugins.git")
                }
            }
        }
    }

    repositories {
        maven {
            val releasesRepoUrl = layout.buildDirectory.dir("repos/releases")
            val snapshotsRepoUrl = layout.buildDirectory.dir("repos/snapshots")
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
        }
    }
}
/*
signing {
    sign(publishing.publications["release"])
}


*/


