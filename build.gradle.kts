import io.gitlab.arturbosch.detekt.Detekt
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

val detektReportMerge by
  tasks.registering(ReportMergeTask::class) {
    output = project.layout.buildDirectory.file("reports/detekt/merge.sarif")
  }

subprojects {
  tasks {
    withType<Detekt>().configureEach {
      finalizedBy(detektReportMerge)
      reports { sarif.required = true }
    }
  }

  detektReportMerge {
    input.from(tasks.withType<Detekt>().map { it.sarifReportFile })
  }
}
