package app.imperative;

import static ga.overfullstack.app.common.DataSet.EGG_CARTON;
import static ga.overfullstack.app.common.DataSet.EXPECTED_IMPERATIVE_VALIDATION_RESULTS;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 *
 * <pre>
 * Problems:
 * - Complexity
 * - Mutation
 * . Non-sharable
 * - Unit-Testability
 * - Validation Jenga
 * </pre>
 */
class ImperativeValidationTest {
  @Test
  void cyclomaticCode() {
    final var badEggFailureBucketMap =
        ga.overfullstack.app.imperative.ImperativeValidation.validateEggCartonImperatively(
            EGG_CARTON.toJavaList());
    Assertions.assertEquals(EXPECTED_IMPERATIVE_VALIDATION_RESULTS, badEggFailureBucketMap);
  }
}
