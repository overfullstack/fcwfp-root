package app.imperative;

import static ga.overfullstack.app.common.DataSet.EGG_CARTON;
import static ga.overfullstack.app.common.DataSet.EXPECTED_IMPERATIVE_VALIDATION_RESULTS;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Validations are broken down to separate functions.
 */
public class ImperativeValidation3Test {
  /**
   * This Octopus turns into a monster someday
   */
  @Test
  void octopusOrchestrator() {
    final var badEggFailureBucketMap = ga.overfullstack.app.imperative.ImperativeValidation3.validateEggCartonImperatively(
        EGG_CARTON.toJavaList()); // sending copy to use different iterator
    Assertions.assertEquals(EXPECTED_IMPERATIVE_VALIDATION_RESULTS, badEggFailureBucketMap);
  }

}
