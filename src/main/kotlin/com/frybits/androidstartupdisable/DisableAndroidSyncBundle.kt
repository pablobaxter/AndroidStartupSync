package com.frybits.androidstartupdisable

import com.intellij.DynamicBundle
import org.jetbrains.annotations.PropertyKey
import java.util.function.Supplier

private const val PATH_TO_BUNDLE = "messages.DisableAndroidSync"
internal const val DISABLE_STARTUP_SYNC = "frybits.disable.startup.sync"

object DisableAndroidSyncBundle : DynamicBundle(PATH_TO_BUNDLE) {

    fun message(@PropertyKey(resourceBundle = PATH_TO_BUNDLE) key: String, vararg params: Any): String {
        return getMessage(key, params)
    }

    fun messagePointer(@PropertyKey(resourceBundle = PATH_TO_BUNDLE) key: String, vararg params: Any): Supplier<String> {
        return getLazyMessage(key, params)
    }
}
