plugins {
  id("fcwfp.sub-conventions")
  id("fcwfp.kt-conventions")
}

dependencies {
  implementation(libs.bundles.arrow)
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.java.vavr)
  implementation(libs.kotlin.vavr)
  implementation(libs.spring.beans)
  implementation(libs.pprint)
  implementation(libs.bundles.kotlin.logging)
  testImplementation(libs.assertj.core)
}
