package app.declarative;

import static ga.overfullstack.algebra.Strategies.accumulationStrategy;
import static ga.overfullstack.algebra.Strategies.failFastStrategy;
import static ga.overfullstack.algebra.Strategies.failFastStrategy2;
import static ga.overfullstack.algebra.Strategies.runAllValidationsFailFastImperative;
import static ga.overfullstack.app.common.DataSet.EXPECTED_DECLARATIVE_VALIDATION_RESULTS;
import static ga.overfullstack.app.common.DataSet.EXPECTED_DECLARATIVE_VALIDATION_RESULTS_2;
import static ga.overfullstack.app.common.DataSet.IMMUTABLE_EGG_CARTON;
import static ga.overfullstack.app.declarative.Config.EGG_VALIDATION_CHAIN;
import static ga.overfullstack.app.declarative.Config.getStreamBySize;
import static ga.overfullstack.app.domain.validation.ValidationFailures.NOTHING_TO_VALIDATE;

import ga.overfullstack.app.domain.validation.ValidationFailure;
import io.vavr.collection.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * This class contains validations as values.
 *
 * Requirements
 * - Partial Failures
 *
 * Problems solved:
 * - Octopus Orchestrator - 😵 dead
 * - Mutation to Transformation
 * - Unit-Testability - 👍
 *
 * Results:
 * - Complexity - Minimum
 * - Chaos to Order
 * </pre>
 */
class RailwayValidation2Test {
  private static final Logger LOGGER = LoggerFactory.getLogger(RailwayValidation2Test.class);
  /**
   * Again mixing How-to-do from What-to-do.
   */
  @Test
  void plainOldImperativeOrchestration() {
    final var validationResults = runAllValidationsFailFastImperative(
        IMMUTABLE_EGG_CARTON, EGG_VALIDATION_CHAIN, NOTHING_TO_VALIDATE, ValidationFailure::withThrowable);
    Assertions.assertEquals(EXPECTED_DECLARATIVE_VALIDATION_RESULTS.toJavaList(), validationResults);
  }

  /**
   * <pre>
   * - No need to comprehend every time, like nested for-loop
   * - No need to unit test
   * - Shared vocabulary
   * - Universal vocabulary
   * </pre>
   */
  @Test
  void failFast1() {
    final var validationResults = IMMUTABLE_EGG_CARTON.iterator()
        .map(failFastStrategy(EGG_VALIDATION_CHAIN, NOTHING_TO_VALIDATE, ValidationFailure::withThrowable))
        .toList();
    validationResults.forEach(result -> LOGGER.info(result.toString()));
    Assertions.assertEquals(EXPECTED_DECLARATIVE_VALIDATION_RESULTS, validationResults);
  }

  @Test
  void failFast2() {
    final var validationResults = IMMUTABLE_EGG_CARTON.iterator()
        .map(failFastStrategy2(EGG_VALIDATION_CHAIN, NOTHING_TO_VALIDATE, ValidationFailure::withThrowable))
        .toList();
    validationResults.forEach(result -> LOGGER.info(result.toString()));
    Assertions.assertEquals(EXPECTED_DECLARATIVE_VALIDATION_RESULTS_2, validationResults);
  }

  @Test
  void failFastNonBulk1() {
    final var validationResult =
        failFastStrategy(EGG_VALIDATION_CHAIN, NOTHING_TO_VALIDATE, ValidationFailure::withThrowable).apply(
            IMMUTABLE_EGG_CARTON.get());
    LOGGER.info(validationResult.toString());
  }

  @Test
  void failFastNonBulk2() {
    final var validationResult =
        failFastStrategy2(EGG_VALIDATION_CHAIN, NOTHING_TO_VALIDATE, ValidationFailure::withThrowable).apply(
            IMMUTABLE_EGG_CARTON.get());
    LOGGER.info(validationResult.toString());
  }

  @Test
  void noValidations1() {
    final var validationResult =
        failFastStrategy(List.empty(), NOTHING_TO_VALIDATE, ValidationFailure::withThrowable).apply(
            IMMUTABLE_EGG_CARTON);
    LOGGER.info(validationResult.toString());
  }

  @Test
  void noValidations2() {
    final var validationResult =
        failFastStrategy2(List.empty(), NOTHING_TO_VALIDATE, ValidationFailure::withThrowable).apply(
            IMMUTABLE_EGG_CARTON);
    LOGGER.info(validationResult.toString());
  }

  @Test
  void errorAccumulation() {
    final var validationResultsAccumulated = IMMUTABLE_EGG_CARTON.iterator()
        .map(accumulationStrategy(EGG_VALIDATION_CHAIN, NOTHING_TO_VALIDATE, ValidationFailure::withThrowable))
        .toList();
    validationResultsAccumulated.forEach(result -> LOGGER.info(result.toString()));
  }

  /**
   * Will switch to Parallel mode if EggCarton size is above `MAX_SIZE_FOR_PARALLEL`.
   */
  @Test
  void parallel1() {
    final var validationResults = getStreamBySize(IMMUTABLE_EGG_CARTON)
        .map(failFastStrategy(EGG_VALIDATION_CHAIN, NOTHING_TO_VALIDATE, ValidationFailure::withThrowable))
        .collect(Collectors.toList());
    validationResults.forEach(result -> LOGGER.info(result.toString()));
    Assertions.assertEquals(EXPECTED_DECLARATIVE_VALIDATION_RESULTS.toJavaList(), validationResults);
  }

  @Test
  void parallel2() {
    final var validationResults = getStreamBySize(IMMUTABLE_EGG_CARTON)
        .map(failFastStrategy2(EGG_VALIDATION_CHAIN, NOTHING_TO_VALIDATE, ValidationFailure::withThrowable))
        .collect(Collectors.toList());
    validationResults.forEach(result -> LOGGER.info(result.toString()));
    Assertions.assertEquals(EXPECTED_DECLARATIVE_VALIDATION_RESULTS_2.toJavaList(), validationResults);
  }

}
