import com.android.build.api.dsl.LibraryExtension
import com.camgist.convention.ExtensionType
import com.camgist.convention.configureBuildTypes
import com.camgist.convention.configureKotlinAndroid
import com.camgist.convention.configureKotlinJvm
import com.camgist.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("org.jetbrains.kotlin.jvm")
            }

            configureKotlinJvm()

        }
    }
}