package ga.overfullstack.app.domain;

import static ga.overfullstack.app.common.Constants.MAX_DAYS_TO_HATCH;
import static ga.overfullstack.app.common.Constants.MAX_DAYS_TO_SHIP;
import static ga.overfullstack.app.common.Constants.MIN_DAYS_TO_HATCH;

import ga.overfullstack.app.domain.validation.ThrowableMsgs;

/** Operations used by Functional Validations. Imagine these are out of our hands, like DB calls. */
public final class Rules {
  private Rules() {}

  public static boolean simpleOperation1(ImmutableEgg eggToBeValidated) {
    return eggToBeValidated != null;
  }

  // These check positive cases, true = success ; false = ValidationFailure
  // -----------------------|5----------------|15-------------------|21-------------------
  // ----About to hatch----|------Valid-------|--Might never hatch--|--Too late to hatch--|

  public static boolean throwableOperation2(ImmutableEgg eggToBeValidated) {
    if (eggToBeValidated.daysToHatch() >= MAX_DAYS_TO_HATCH) { // Might never hatch
      throw new IllegalArgumentException(ThrowableMsgs.THROWABLE_OPERATION_2);
    } else {
      return eggToBeValidated.daysToHatch() <= MIN_DAYS_TO_HATCH; // Otherwise, Too late to hatch
    }
  }

  public static boolean throwableOperation3(ImmutableEgg eggToBeValidated) {
    if (eggToBeValidated.daysToHatch() <= 0) {
      throw new IllegalArgumentException(ThrowableMsgs.THROWABLE_VALIDATION_3);
    } else {
      return eggToBeValidated.daysToHatch() >= MAX_DAYS_TO_SHIP; // Otherwise, Might hatch too soon
    }
  }

  public static boolean throwableNestedOperation3(Yolk yolkTobeValidated) {
    if (yolkTobeValidated == null) {
      throw new IllegalArgumentException(ThrowableMsgs.THROWABLE_NESTED_OPERATION_31);
    } else if (yolkTobeValidated.condition() == Condition.BAD) {
      throw new IllegalArgumentException(ThrowableMsgs.THROWABLE_NESTED_OPERATION_32);
    } else {
      return yolkTobeValidated.color() == Color.GOLD || yolkTobeValidated.color() == Color.YELLOW;
    }
  }
}
