package app.imperative

import app.common.eggCarton
import app.common.expectedImperativeValidationResults
import io.github.oshai.kotlinlogging.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/* gakshintala created on 4/12/20 */
class ImperativeValidationTest {
  @Test
  fun cyclomaticCode() {
    val badEggFailureBucketMap =
      validateEggCartonImperatively(
        eggCarton.toMutableList()
      ) // sending a copy, a the iterator is common
    logger.info { badEggFailureBucketMap }
    Assertions.assertEquals(expectedImperativeValidationResults, badEggFailureBucketMap)
  }

  private val logger = KotlinLogging.logger {}
}
