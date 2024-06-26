/* gakshintala created on 4/12/20 */
package ga.overfullstack.app.declarative

import algebra.accumulationStrategy
import algebra.failFastStrategy
import algebra.failFastStrategy2
import algebra.runAllValidationsFailFastStrategyImperative
import algebra.validateAndAccumulateErrors
import algebra.validateAndFailFast
import app.common.eggCartonImmutable
import app.common.expectedDeclarativeValidationResults
import app.declarative.EGG_VALIDATION_CHAIN
import app.domain.Egg
import app.domain.validation.ValidationFailure
import app.domain.validation.ValidationFailures.NOTHING_TO_VALIDATE
import arrow.core.Either
import arrow.fx.coroutines.parMap
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

private val logger = KotlinLogging.logger {}

class RailwayValidationTest {
  @Test
  fun `plain Old Imperative Orchestration`() = runBlocking {
    val validationResults =
      runAllValidationsFailFastStrategyImperative(
        eggCartonImmutable,
        EGG_VALIDATION_CHAIN,
        (ValidationFailure)::withException,
        NOTHING_TO_VALIDATE
      )
    logger.info { validationResults }
    Assertions.assertEquals(expectedDeclarativeValidationResults, validationResults)
  }

  @Test
  fun `Fail Fast`() = runBlocking {
    // No CoroutineScope, so all suspend validations run sequentially blocking each-other.
    // This can be optimized to run on default dispatcher.
    // Individual validations can run on their own IO dispatchers if needed, for IO operations.
    val validationResults =
      eggCartonImmutable
        .parMap(
          EmptyCoroutineContext,
          failFastStrategy(
            EGG_VALIDATION_CHAIN,
            (ValidationFailure)::withException,
            NOTHING_TO_VALIDATE
          )
        )
        .toList()
    logger.info { validationResults }
    Assertions.assertEquals(expectedDeclarativeValidationResults, validationResults)
  }

  @Test
  fun `Fail Fast In Parallel`() = runBlocking {
    val validationResults =
      // Apply all validations FF on each egg, iterating eggs in parallel, with Default Thread Pool
      validateAndFailFast(
        eggCartonImmutable,
        EGG_VALIDATION_CHAIN,
        (ValidationFailure)::withException,
        NOTHING_TO_VALIDATE
      )
    logger.info { validationResults }
    Assertions.assertEquals(expectedDeclarativeValidationResults, validationResults)
  }

  @Test
  fun `Fail Fast 2`() = runBlocking {
    // No CoroutineScope, so all suspend validations run sequentially blocking each-other.
    // This can be optimized to run on default dispatcher.
    // Individual validations can run on their own IO dispatchers if needed, for IO operations.
    val validationResults =
      eggCartonImmutable
        .parMap(
          EmptyCoroutineContext,
          failFastStrategy2(
            EGG_VALIDATION_CHAIN,
            (ValidationFailure)::withException,
            NOTHING_TO_VALIDATE
          )
        )
        .toList()
    logger.info { validationResults }
    Assertions.assertEquals(expectedDeclarativeValidationResults, validationResults)
  }

  @Test
  fun `Error Accumulation`() = runBlocking {
    val validationResultsAccumulated =
      eggCartonImmutable
        .parMap(
          EmptyCoroutineContext,
          accumulationStrategy(
            EGG_VALIDATION_CHAIN,
            (ValidationFailure)::withException,
            NOTHING_TO_VALIDATE
          )
        )
        .toList()
    logger.info { validationResultsAccumulated }
  }

  @Test
  fun `Error Accumulation in Parallel`() = runBlocking {
    val validationResultsAccumulatedInParallel: List<List<Either<ValidationFailure, Egg?>>> =
      validateAndAccumulateErrors(
        eggCartonImmutable,
        EGG_VALIDATION_CHAIN,
        (ValidationFailure)::withException,
        NOTHING_TO_VALIDATE
      )
    logger.info { validationResultsAccumulatedInParallel }
  }
}
