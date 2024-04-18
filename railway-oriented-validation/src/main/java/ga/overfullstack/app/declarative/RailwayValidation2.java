package ga.overfullstack.app.declarative;

import ga.overfullstack.algebra.types.Validator;
import ga.overfullstack.app.domain.ImmutableEgg;
import ga.overfullstack.app.domain.Rules;
import ga.overfullstack.app.domain.Yolk;
import ga.overfullstack.app.domain.validation.ValidationFailure;
import ga.overfullstack.app.domain.validation.ValidationFailures;

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
public final class RailwayValidation2 {
  private RailwayValidation2() {
  }

  public static final Validator<ImmutableEgg, ValidationFailure> validate1Simple = validatedEgg -> validatedEgg
      .filterOrElse(Rules::simpleOperation1, ignore -> ValidationFailures.NO_EGG_TO_VALIDATE_1);

  public static final Validator<ImmutableEgg, ValidationFailure> validate2Throwable = validatedEgg -> validatedEgg
      .filterOrElse(Rules::throwableOperation2, ignore -> ValidationFailures.TOO_LATE_TO_HATCH_2);

  public static final Validator<ImmutableEgg, ValidationFailure> validateParent3 = validatedEgg -> validatedEgg
      .filterOrElse(Rules::throwableOperation3, ignore -> ValidationFailures.ABOUT_TO_HATCH_P_3);

  public static final Validator<Yolk, ValidationFailure> validateChild31 = validatedYolk -> validatedYolk
      .filterOrElse(Rules::throwableNestedOperation3, ignore -> ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3);

  /**
   * ----------------------------------- JUST DUPLICATES FOR DEMO -----------------------------------
   **/

  public static final Validator<Yolk, ValidationFailure> validateChild32 = validatedYolk -> validatedYolk
      .filterOrElse(Rules::throwableNestedOperation3, ignore -> ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3);

  public static final Validator<ImmutableEgg, ValidationFailure> validateParent41 = validatedEgg -> validatedEgg
      .filterOrElse(Rules::throwableOperation3, ignore -> ValidationFailures.ABOUT_TO_HATCH_P_3);

  public static final Validator<ImmutableEgg, ValidationFailure> validateParent42 = validatedEgg -> validatedEgg
      .filterOrElse(Rules::throwableOperation3, ignore -> ValidationFailures.ABOUT_TO_HATCH_P_3);

  // Child with multiple Parent Validations
  public static final Validator<Yolk, ValidationFailure> validateChild4 = validatedYolk -> validatedYolk
      .filterOrElse(Rules::throwableNestedOperation3, ignore -> ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3);

}
