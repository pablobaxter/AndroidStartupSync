package com.frybits.androidstartupdisable

import com.android.tools.idea.gradle.project.GradleProjectInfo
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class DisableAndroidSyncStartup : ProjectActivity {
    override suspend fun execute(project: Project) {
        val gradleProjectInfo = GradleProjectInfo.getInstance(project)
        gradleProjectInfo.isNewProject = false
        gradleProjectInfo.isSkipStartupActivity = true
    }
}
