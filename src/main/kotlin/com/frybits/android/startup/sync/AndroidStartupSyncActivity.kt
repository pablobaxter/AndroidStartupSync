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

import com.android.tools.idea.gradle.actions.SyncProjectAction
import com.android.tools.idea.gradle.project.GradleProjectInfo
import com.android.tools.idea.gradle.project.sync.GradleSyncStateHolder
import com.frybits.android.startup.sync.AndroidStartupSyncBundle.message
import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.util.ThreeState

/*
 * Sets the previously set flags on Android Studio start in order to prevent Gradle project loading
 */
class AndroidStartupSyncActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        val gradleProjectInfo = GradleProjectInfo.getInstance(project)
        val propertiesComponent = PropertiesComponent.getInstance(project)
        val gradleSyncStateHolder = GradleSyncStateHolder.getInstance(project)

        // If the value was never set, default to what GradleProjectInfo already uses
        if (propertiesComponent.isValueSet(DISABLE_STARTUP_SYNC)) {

            // Grab previously set property, and set it before Android Studio completes startup
            var isStartupSyncDisabled = propertiesComponent.getBoolean(DISABLE_STARTUP_SYNC)
            gradleProjectInfo.isNewProject = !isStartupSyncDisabled
            gradleProjectInfo.isSkipStartupActivity = isStartupSyncDisabled

            // Notify that startup sync was disabled if sync was needed
            if (isStartupSyncDisabled && gradleSyncStateHolder.isSyncNeeded().isAtLeast(ThreeState.UNSURE)) {
                val notification = NotificationGroupManager.getInstance()
                    .getNotificationGroup("frybits.android.startup.sync.notification")
                    .createNotification(
                        message("frybits.android.startup.sync.notification.title"),
                        NotificationType.INFORMATION
                    )
                notification.addAction(
                    object : SyncProjectAction(message("frybits.android.startup.sync.notification.action")) {
                        override fun doPerform(e: AnActionEvent, project: Project) {
                            super.doPerform(e, project)
                            notification.expire()
                        }
                    })

                notification
                    .notify(project)
            }
        }
    }
}
