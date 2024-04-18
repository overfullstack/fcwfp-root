package ga.overfullstack.app.imperative;

import ga.overfullstack.app.domain.Egg;
import ga.overfullstack.app.domain.Yolk;
import ga.overfullstack.app.domain.validation.ValidationFailure;
import ga.overfullstack.app.domain.validation.ValidationFailures;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public final class ImperativeValidation2 {
  private static final Logger LOGGER = LoggerFactory.getLogger(ImperativeValidation2.class);

  private ImperativeValidation2() {}

  // Can't ensure the uniformity of signature among validations, which can increase the complexity.
  private static boolean validate1Simple(
      Map<Integer, ValidationFailure> badEggFailureBucketMap,
      int eggIndex,
      Iterator<Egg> iterator,
      Egg eggToBeValidated) {
    if (!Rules.simpleOperation1(eggToBeValidated)) {
      iterator.remove();
      badEggFailureBucketMap.put(eggIndex, ValidationFailures.NO_EGG_TO_VALIDATE_1);
      return false;
    }
    return true;
  }

  private static boolean validate2Throwable(
      Map<Integer, ValidationFailure> badEggFailureBucketMap,
      int eggIndex,
      Iterator<Egg> iterator,
      Egg eggToBeValidated) {
    try {
      if (!Rules.throwableOperation2(eggToBeValidated)) {
        iterator.remove();
        badEggFailureBucketMap.put(eggIndex, ValidationFailures.TOO_LATE_TO_HATCH_2);
        return false;
      }
    } catch (Exception e) {
      iterator.remove();
      badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
      return false;
    }
    return true;
  }

  // Parent with multiple Child Validations
  private static boolean validateParent3(
      Map<Integer, ValidationFailure> badEggFailureBucketMap,
      int eggIndex,
      Iterator<Egg> iterator,
      Egg eggToBeValidated) {
    try {
      if (Rules.throwableOperation3(eggToBeValidated)) {
        var yolkTobeValidated = eggToBeValidated.yolk();
        if (!validateChild31(badEggFailureBucketMap, eggIndex, iterator, yolkTobeValidated)) {
          return false;
        }
        if (!validateChild32(badEggFailureBucketMap, eggIndex, iterator, yolkTobeValidated)) {
          return false;
        }
      } else {
        iterator.remove();
        badEggFailureBucketMap.put(eggIndex, ValidationFailures.ABOUT_TO_HATCH_P_3);
        return false;
      }
    } catch (Exception e) {
      iterator.remove();
      badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
      return false;
    }
    return true;
  }

  private static boolean validateChild31(
      Map<Integer, ValidationFailure> badEggFailureBucketMap,
      int eggIndex,
      Iterator<Egg> iterator,
      Yolk yolkTobeValidated) {
    try {
      if (!Rules.throwableNestedOperation3(yolkTobeValidated)) {
        iterator.remove();
        badEggFailureBucketMap.put(eggIndex, ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3);
        return false;
      }
    } catch (Exception e) {
      iterator.remove();
      badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
      return false;
    }
    return true;
  }

  private static boolean validateChild32(
      Map<Integer, ValidationFailure> badEggFailureBucketMap,
      int eggIndex,
      Iterator<Egg> iterator,
      Yolk yolkTobeValidated) {
    try {
      if (!Rules.throwableNestedOperation3(yolkTobeValidated)) {
        iterator.remove();
        badEggFailureBucketMap.put(eggIndex, ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3);
        return false;
      }
    } catch (Exception e) {
      iterator.remove();
      badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
      return false;
    }
    return true;
  }

  // Child with multiple Parent Validations
  private static boolean validateChild4(
      Map<Integer, ValidationFailure> badEggFailureBucketMap,
      int eggIndex,
      Iterator<Egg> iterator,
      Egg eggToBeValidated) {
    if (!validateParent41(badEggFailureBucketMap, eggIndex, iterator, eggToBeValidated)) {
      return false;
    }
    if (!validateParent42(badEggFailureBucketMap, eggIndex, iterator, eggToBeValidated)) {
      return false;
    }
    var yolkTobeValidated = eggToBeValidated.yolk();
    try {
      if (!Rules.throwableNestedOperation3(yolkTobeValidated)) {
        iterator.remove();
        badEggFailureBucketMap.put(eggIndex, ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3);
        return false;
      }
    } catch (Exception e) {
      iterator.remove();
      badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
      return false;
    }
    return true;
  }

  private static boolean validateParent41(
      Map<Integer, ValidationFailure> badEggFailureBucketMap,
      int eggIndex,
      Iterator<Egg> iterator,
      Egg eggToBeValidated) {
    try {
      if (!Rules.throwableOperation3(eggToBeValidated)) {
        iterator.remove();
        badEggFailureBucketMap.put(eggIndex, ValidationFailures.ABOUT_TO_HATCH_P_3);
        return false;
      }
    } catch (Exception e) {
      iterator.remove();
      badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
      return false;
    }
    return true;
  }

  private static boolean validateParent42(
      Map<Integer, ValidationFailure> badEggFailureBucketMap,
      int eggIndex,
      Iterator<Egg> iterator,
      Egg eggToBeValidated) {
    try {
      if (!Rules.throwableOperation3(eggToBeValidated)) {
        iterator.remove();
        badEggFailureBucketMap.put(eggIndex, ValidationFailures.ABOUT_TO_HATCH_P_3);
        return false;
      }
    } catch (Exception e) {
      iterator.remove();
      badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
      return false;
    }
    return true;
  }

  /** This Octopus turns into a monster someday */
  public static HashMap<Integer, ValidationFailure> validateEggCartonImperatively(
      List<Egg> eggList) {
    // R3 - Trying to be the owner of all state.
    var badEggFailureBucketMap = new HashMap<Integer, ValidationFailure>();
    int eggIndex = 0;
    for (Iterator<Egg> iterator = eggList.iterator();
        iterator.hasNext();
        eggIndex++) { // R-1: Iterate through eggs
      var eggToBeValidated = iterator.next();

      // Global state is dangerous. badEggFailureBucketMap and iterator being passed to each and
      // every function, difficult to keep track of how they are being mutated during debugging.
      if (!validate1Simple(badEggFailureBucketMap, eggIndex, iterator, eggToBeValidated)) {
        continue; // R-2: Manage fail-fast
      }

      // Adding a new validation in-between requires you to understand all the validations above and
      // below, which slows down development and makes it prone to bugs.
      if (!validate2Throwable(badEggFailureBucketMap, eggIndex, iterator, eggToBeValidated)) {
        continue;
      }

      // Parent with multiple Child Validations
      if (!validateParent3(badEggFailureBucketMap, eggIndex, iterator, eggToBeValidated)) {
        continue;
      }

      // Child with multiple Parent Validations
      if (!validateChild4(badEggFailureBucketMap, eggIndex, iterator, eggToBeValidated)) {
        continue;
      }
    }

    for (Map.Entry<Integer, ValidationFailure> entry : badEggFailureBucketMap.entrySet()) {
      LOGGER.info(entry.toString());
    }
    return badEggFailureBucketMap;
  }
}
