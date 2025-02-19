/*
 *  Copyright 2025 Pablo Baxter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.frybits.android.startup.sync

import com.android.tools.idea.gradle.project.GradleProjectInfo
import com.frybits.android.startup.sync.AndroidStartupSyncBundle.message
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.options.BoundSearchableConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel

/*
 * Adds the UI to the IntelliJ settings panel allowing per project enable/disable of startup sync
 */
class AndroidStartupSyncSetting(private val project: Project) : BoundSearchableConfigurable(
    message("frybits.android.startup.sync.name"),
    "Settings_Build_Tools",
    "build.tools"
) {

    override fun createPanel(): DialogPanel = panel {
        val gradleProjectInfo = GradleProjectInfo.getInstance(project)
        val propertiesComponent = PropertiesComponent.getInstance(project)

        var isStartupSyncDisabled = propertiesComponent.getBoolean(DISABLE_STARTUP_SYNC)

        row {
            checkBox(message("frybits.android.startup.sync.radio.button.title"))
                .bindSelected({ isStartupSyncDisabled }, { isStartupSyncDisabled = it })
        }
        onApply {
            gradleProjectInfo.isNewProject = !isStartupSyncDisabled
            gradleProjectInfo.isSkipStartupActivity = isStartupSyncDisabled
            propertiesComponent.setValue(DISABLE_STARTUP_SYNC, isStartupSyncDisabled)
        }
    }
}
