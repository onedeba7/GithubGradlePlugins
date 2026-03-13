package io.github.onedeba7.gradle

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GithubPluginTest {

    def project

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: 'io.github.onedeba7.gradle'

    }

    @Test
    void shouldHasGithubReleaseTask() {
        Assert.assertTrue(project.tasks.githubRelease instanceof ReleaseTask)
    }
}

