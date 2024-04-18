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
 * Validations are broken down to separate functions.
 */
public final class ImperativeValidation3 {
  private static final Logger LOGGER = LoggerFactory.getLogger(ImperativeValidation3.class);
  private static void updateFailureForEgg(Iterator<Egg> iterator, int eggIndex,
      Map<Integer, ValidationFailure> badEggFailureBucketMap, ValidationFailure ValidationFailure) {
    iterator.remove();
    badEggFailureBucketMap.put(eggIndex, ValidationFailure);
  }

  private static ValidationFailure validate1(Egg eggToBeValidated) {
    if (!Rules.simpleOperation1(eggToBeValidated)) {
      return ValidationFailures.NO_EGG_TO_VALIDATE_1;
    }
    return ValidationFailures.NONE;
  }

  private static ValidationFailure validate2(Egg eggToBeValidated) {
    try {
      if (!Rules.throwableOperation2(eggToBeValidated)) {
        return ValidationFailures.TOO_LATE_TO_HATCH_2;
      }
    } catch (Exception e) {
      return ValidationFailure.withErrorMessage(e.getMessage());
    }
    return ValidationFailures.NONE;
  }

  private static ValidationFailure validateParent3(Egg eggToBeValidated) {
    try {
      if (!Rules.throwableOperation3(eggToBeValidated)) {
        return ValidationFailures.ABOUT_TO_HATCH_P_3;
      }
    } catch (Exception e) {
      return ValidationFailure.withErrorMessage(e.getMessage());
    }
    return ValidationFailures.NONE;
  }

  private static ValidationFailure validateChild3(Egg eggToBeValidated) {
    final var parentValidationFailure = validateParent3(eggToBeValidated);
    if (parentValidationFailure != ValidationFailures.NONE) {
      return parentValidationFailure;
    }
    var yolkTobeValidated = eggToBeValidated.yolk();
    try {
      if (!Rules.throwableNestedOperation3(yolkTobeValidated)) {
        return ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3;
      }
    } catch (Exception e) {
      return ValidationFailure.withErrorMessage(e.getMessage());
    }
    return ValidationFailures.NONE;
  }

  /**
   * This Octopus turns into a monster someday
   */
  public static HashMap<Integer, ValidationFailure> validateEggCartonImperatively(List<Egg> eggList) {
    var badEggFailureBucketMap = new HashMap<Integer, ValidationFailure>();
    var eggIndex = 0;
    for (var iterator = eggList.iterator(); iterator.hasNext(); eggIndex++) {
      var eggToBeValidated = iterator.next();

      // Global state is dangerous. badEggFailureBucketMap and iterator being passed to each and every function, difficult to keep track of how they are being mutated during debugging.
      final var validate1Failure = validate1(eggToBeValidated);
      if (validate1Failure != ValidationFailures.NONE) {
        updateFailureForEgg(iterator, eggIndex, badEggFailureBucketMap, validate1Failure);
        continue;
      }

      // Adding a new validation in-between requires you to understand all the validations above and below, which slows down development and makes it prone to bugs.
      final var validate2Failure = validate2(eggToBeValidated);
      if (validate2Failure != ValidationFailures.NONE) {
        updateFailureForEgg(iterator, eggIndex, badEggFailureBucketMap, validate2Failure);
        continue;
      }

      final var validate3Failure = validateChild3(eggToBeValidated);
      if (validate3Failure != ValidationFailures.NONE) {
        updateFailureForEgg(iterator, eggIndex, badEggFailureBucketMap, validate3Failure);
        continue;
      }
    }

    for (var entry : badEggFailureBucketMap.entrySet()) {
      LOGGER.info(entry.toString());
    }
    return badEggFailureBucketMap;
  }

}
