package ga.overfullstack.app.imperative;

import static ga.overfullstack.app.common.Constants.MAX_DAYS_TO_HATCH;
import static ga.overfullstack.app.common.Constants.MAX_DAYS_TO_SHIP;
import static ga.overfullstack.app.common.Constants.MIN_DAYS_TO_HATCH;
import static ga.overfullstack.app.domain.Color.GOLD;
import static ga.overfullstack.app.domain.Color.YELLOW;
import static ga.overfullstack.app.domain.Condition.BAD;

import ga.overfullstack.app.domain.Egg;
import ga.overfullstack.app.domain.Yolk;
import ga.overfullstack.app.domain.validation.ThrowableMsgs;

/** Operations used by Imperative Validations. Imagine these are out of our hands, like DB calls. */
final class Rules {
  private Rules() {}

  static boolean simpleOperation1(Egg eggToBeValidated) {
    return eggToBeValidated != null;
  }

  // These check positive cases, true = success ; false = ValidationFailure
  // -----------------------|5----------------|15-------------------|21-------------------
  // ----About to hatch----|------Valid-------|--Might never hatch--|--Too late to hatch--|

  static boolean throwableOperation2(Egg eggToBeValidated) {
    if (eggToBeValidated.daysToHatch() >= MAX_DAYS_TO_HATCH) {
      // Unchecked Exception. Caller would have no clue of this.
      throw new IllegalArgumentException(ThrowableMsgs.THROWABLE_OPERATION_2);
    } else {
      return eggToBeValidated.daysToHatch() <= MIN_DAYS_TO_HATCH; // Otherwise, Too late to hatch
    }
  }

  static boolean throwableOperation3(Egg eggToBeValidated) {
    if (eggToBeValidated.daysToHatch() <= 0) {
      throw new IllegalArgumentException(ThrowableMsgs.THROWABLE_VALIDATION_3);
    } else {
      return eggToBeValidated.daysToHatch() >= MAX_DAYS_TO_SHIP; // Otherwise, Might hatch too soon
    }
  }

  static boolean throwableNestedOperation3(Yolk yolkTobeValidated) {
    if (yolkTobeValidated == null) {
      throw new IllegalArgumentException(ThrowableMsgs.THROWABLE_NESTED_OPERATION_31);
    } else if (yolkTobeValidated.condition() == BAD) {
      throw new IllegalArgumentException(ThrowableMsgs.THROWABLE_NESTED_OPERATION_32);
    } else {
      return yolkTobeValidated.color() == GOLD || yolkTobeValidated.color() == YELLOW;
    }
  }
}
