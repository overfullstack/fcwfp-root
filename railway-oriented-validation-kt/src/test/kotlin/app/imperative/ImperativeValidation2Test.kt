/* gakshintala created on 4/13/20 */
package app.imperative

import app.common.eggCarton
import app.common.expectedImperativeValidationResults
import io.github.oshai.kotlinlogging.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ImperativeValidation2Test {
    @Test
    fun `Octopus Orchestrator`() {
        val badEggFailureBucketMap =
            validateEggCartonImperatively2(eggCarton.toMutableList()) // sending a copy, a the iterator is common
        logger.info { badEggFailureBucketMap }
        Assertions.assertEquals(expectedImperativeValidationResults, badEggFailureBucketMap)
    }

  private val logger = KotlinLogging.logger {}
}
