import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

plugins {
  id("fcwfp.root-conventions")
  id(libs.plugins.kover.get().pluginId)
  id(libs.plugins.detekt.get().pluginId) apply false
}

dependencies { subprojects.forEach { kover(project(":${it.name}")) } }

koverReport { defaults { html { onCheck = true } } }

allprojects {
  repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://repo.spring.io/milestone")
  }
}

subprojects {
  tasks.withType<Detekt>().configureEach { reports { html.required = true } }
  plugins.withType<DetektPlugin> {
    tasks.withType<Detekt> detekt@{
      finalizedBy(detektReportMerge)
      detektReportMerge.configure { input.from(this@detekt.htmlReportFile) }
    }
  }
}
