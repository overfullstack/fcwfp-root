plugins {
  id("fcwfp.sub-conventions")
  id("fcwfp.kt-conventions")
}

dependencies {
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.klaxon)
  implementation(libs.java.vavr)
  implementation(libs.kotlin.vavr)
  implementation(libs.bundles.apache.log4j)
  implementation(libs.bundles.kotlin.logging)
  implementation(libs.pprint)
  testImplementation(libs.truth)
}
