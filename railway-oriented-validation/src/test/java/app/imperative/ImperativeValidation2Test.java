package app.imperative;

import static ga.overfullstack.app.common.DataSet.EGG_CARTON;
import static ga.overfullstack.app.common.DataSet.EXPECTED_IMPERATIVE_VALIDATION_RESULTS;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 *
 * <pre>
 * Validations are broken down to separate functions.
 *
 * Problems:
 * - Octopus Orchestration
 * - Mutation
 * - Unit-Testability
 * . Non-sharable
 * - Don't attempt to run in Parallel
 *
 * Major Problems
 * - Management of Validation Order - how-to-do
 * - Complexity
 * - Chaos
 * </pre>
 */
class ImperativeValidation2Test {

  @Test
  void octopusOrchestrator() {
    final var badEggFailureBucketMap =
        ga.overfullstack.app.imperative.ImperativeValidation2.validateEggCartonImperatively(
            EGG_CARTON.toJavaList()); // Sending a copy to use different iterator
    Assertions.assertEquals(EXPECTED_IMPERATIVE_VALIDATION_RESULTS, badEggFailureBucketMap);
  }
}
