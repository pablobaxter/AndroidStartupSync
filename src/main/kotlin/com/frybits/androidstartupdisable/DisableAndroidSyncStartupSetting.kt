package com.frybits.androidstartupdisable

import com.android.tools.idea.gradle.project.GradleProjectInfo
import com.frybits.androidstartupdisable.DisableAndroidSyncBundle.message
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.options.BoundSearchableConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel

class DisableAndroidSyncStartupSetting(private val project: Project) : BoundSearchableConfigurable(
    message("frybits.disable.sync.name"),
    "Settings_Build_Tools",
    "build.tools"
) {

    override fun createPanel(): DialogPanel = panel {
        val gradleProjectInfo = GradleProjectInfo.getInstance(project)
        val propertiesComponent = PropertiesComponent.getInstance(project)

        var isStartupSyncDisabled = propertiesComponent.getBoolean(DISABLE_STARTUP_SYNC)

        row {
            checkBox(message("frybits.disable.sync.radio.button.title"))
                .bindSelected({ isStartupSyncDisabled }, { isStartupSyncDisabled = it })
        }
        onApply {
            gradleProjectInfo.isNewProject = !isStartupSyncDisabled
            gradleProjectInfo.isSkipStartupActivity = isStartupSyncDisabled
            propertiesComponent.setValue(DISABLE_STARTUP_SYNC, isStartupSyncDisabled)
        }
    }
}
