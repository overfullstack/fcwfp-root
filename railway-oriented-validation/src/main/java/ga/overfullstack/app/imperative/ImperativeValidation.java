package ga.overfullstack.app.imperative;

import ga.overfullstack.app.domain.Egg;
import ga.overfullstack.app.domain.validation.ValidationFailure;
import ga.overfullstack.app.domain.validation.ValidationFailures;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * gakshintala created on 3/26/20.
 *
 * <pre>
 * Problems:
 * - Complexity
 * - Mutation
 * . Non-sharable
 * - Unit-Testability
 * - Validation Jenga
 * - Fixed Fail-Fast strategy
 * </pre>
 */
public final class ImperativeValidation {
  private static final Logger LOGGER = LoggerFactory.getLogger(ImperativeValidation.class);

  private ImperativeValidation() {}

  public static Map<Integer, ValidationFailure> validateEggCartonImperatively(List<Egg> eggList) {
    HashMap<Integer, ValidationFailure> badEggFailureBucketMap = new HashMap<>();
    int eggIndex = 0;
    for (Iterator<Egg> iterator = eggList.iterator(); iterator.hasNext(); eggIndex++) {
      Egg eggToBeValidated = iterator.next();
      if (!Rules.simpleOperation1(eggToBeValidated)) {
        iterator.remove(); // Mutation
        // How can you cleanly map validation-failure to which validation-method failed?
        badEggFailureBucketMap.put(eggIndex, ValidationFailures.NO_EGG_TO_VALIDATE_1);
        continue;
      }
      try {
        if (!Rules.throwableOperation2(eggToBeValidated)) {
          iterator.remove();
          badEggFailureBucketMap.put(eggIndex, ValidationFailures.TOO_LATE_TO_HATCH_2);
          continue;
        }
      } catch (Exception e) { // ! Repetition of same logic for exception handling
        iterator.remove();
        badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
        continue;
      }
      try { // Inter-dependent validations
        if (Rules.throwableOperation3(eggToBeValidated)) {
          var yolkTobeValidated = eggToBeValidated.yolk();
          try {
            if (!Rules.throwableNestedOperation3(yolkTobeValidated)) {
              iterator.remove();
              badEggFailureBucketMap.put(eggIndex, ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3);
            }
          } catch (Exception e) {
            iterator.remove();
            badEggFailureBucketMap.put(
                eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
          }
        } else {
          iterator.remove();
          badEggFailureBucketMap.put(eggIndex, ValidationFailures.ABOUT_TO_HATCH_P_3);
          continue;
        }
      } catch (Exception e) {
        iterator.remove();
        badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
      }
    }
    for (Map.Entry<Integer, ValidationFailure> entry : badEggFailureBucketMap.entrySet()) {
      LOGGER.info(entry.toString());
    }
    LOGGER.info(badEggFailureBucketMap.toString());
    return badEggFailureBucketMap;
  }
}
