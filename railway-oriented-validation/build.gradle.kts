plugins { id("fcwfp.sub-conventions") }

dependencies {
  implementation(libs.java.vavr)
  implementation(libs.kotlin.vavr)
  implementation(libs.jackson.databind)
  implementation(libs.apache.commons.collections4)
  implementation(libs.guava)
  implementation(libs.revoman)
  implementation(libs.bundles.vador)
  implementation(libs.spring.beans)

  testImplementation(libs.assertj.vavr)
  testImplementation(libs.assertj.core)
}
