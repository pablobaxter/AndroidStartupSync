package com.frybits.androidstartupdisable

import com.android.tools.idea.gradle.project.GradleProjectInfo
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class DisableAndroidSyncStartupActivity: ProjectActivity {
    override suspend fun execute(project: Project) {
        val gradleProjectInfo = GradleProjectInfo.getInstance(project)
        val propertiesComponent = PropertiesComponent.getInstance(project)

        var isStartupSyncDisabled = propertiesComponent.getBoolean(DISABLE_STARTUP_SYNC)
        if (propertiesComponent.isValueSet(DISABLE_STARTUP_SYNC)) {
            gradleProjectInfo.isNewProject = !isStartupSyncDisabled
            gradleProjectInfo.isSkipStartupActivity = isStartupSyncDisabled
        }
    }
}
