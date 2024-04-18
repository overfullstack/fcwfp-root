package ga.overfullstack.app.common;

import ga.overfullstack.app.domain.Color;
import ga.overfullstack.app.domain.Condition;
import ga.overfullstack.app.domain.Egg;
import ga.overfullstack.app.domain.ImmutableEgg;
import ga.overfullstack.app.domain.Yolk;
import ga.overfullstack.app.domain.validation.ThrowableMsgs;
import ga.overfullstack.app.domain.validation.ValidationFailure;
import ga.overfullstack.app.domain.validation.ValidationFailures;
import io.vavr.collection.List;
import io.vavr.control.Either;
import java.util.Map;

public class DataSet {
  public static final List<Egg> EGG_CARTON =
      List.of(
          null, // No egg to validate
          new Egg(1, new Yolk(Condition.GOOD, Color.GOLD)), // About to hatch
          new Egg(8, new Yolk(Condition.BAD, Color.ORANGE)), // Yolk is bad
          new Egg(25, new Yolk(Condition.GOOD, Color.ORANGE)), // Might never hatch
          new Egg(5, new Yolk(Condition.GOOD, Color.YELLOW)), // Valid ✅
          new Egg(-1, new Yolk(Condition.BAD, Color.GOLD)), // Chicken might already be out
          new Egg(16, new Yolk(Condition.GOOD, Color.GOLD)), // Too late to hatch
          new Egg(14, new Yolk(Condition.GOOD, Color.GOLD)), // Valid ✅
          new Egg(0, new Yolk(Condition.BAD, Color.YELLOW)), // Chicken might already be out
          new Egg(6, new Yolk(Condition.BAD, Color.ORANGE)), // Yolk is bad
          new Egg(12, new Yolk(Condition.GOOD, Color.ORANGE)), // Yolk in wrong color
          new Egg(6, null) // No Child to validate
          );

  public static final List<ImmutableEgg> IMMUTABLE_EGG_CARTON =
      io.vavr.collection.List
          .of( // Using vavr list as `java.util.List` doesn't allow `null` in `List.of()`
              null, // No egg to validate
              new ImmutableEgg(1, new Yolk(Condition.GOOD, Color.GOLD)), // About to hatch
              new ImmutableEgg(8, new Yolk(Condition.BAD, Color.ORANGE)), // Yolk is bad
              new ImmutableEgg(25, new Yolk(Condition.GOOD, Color.ORANGE)), // Might never hatch
              new ImmutableEgg(5, new Yolk(Condition.GOOD, Color.YELLOW)), // Valid ✅
              new ImmutableEgg(
                  -1, new Yolk(Condition.BAD, Color.GOLD)), // Chicken might already be out
              new ImmutableEgg(16, new Yolk(Condition.GOOD, Color.GOLD)), // Too late to hatch
              new ImmutableEgg(14, new Yolk(Condition.GOOD, Color.GOLD)), // Valid ✅
              new ImmutableEgg(
                  0, new Yolk(Condition.BAD, Color.YELLOW)), // Chicken might already be out
              new ImmutableEgg(6, new Yolk(Condition.BAD, Color.ORANGE)), // Yolk is bad
              new ImmutableEgg(12, new Yolk(Condition.GOOD, Color.ORANGE)), // Yolk in wrong color
              new ImmutableEgg(6, null) // No Child to validate
              );

  public static final Map<Integer, ValidationFailure> EXPECTED_IMPERATIVE_VALIDATION_RESULTS =
      Map.of(
          0, ValidationFailures.NO_EGG_TO_VALIDATE_1,
          1, ValidationFailures.ABOUT_TO_HATCH_P_3,
          2, ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_NESTED_OPERATION_32),
          3, ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_OPERATION_2),
          5, ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_VALIDATION_3),
          6, ValidationFailures.TOO_LATE_TO_HATCH_2,
          8, ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_VALIDATION_3),
          9, ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_NESTED_OPERATION_32),
          10, ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3,
          11, ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_NESTED_OPERATION_31));

  public static final List<Either<ValidationFailure, ?>> EXPECTED_DECLARATIVE_VALIDATION_RESULTS =
      List.of(
          Either.left(ValidationFailures.NOTHING_TO_VALIDATE),
          Either.left(ValidationFailures.ABOUT_TO_HATCH_P_3),
          Either.left(
              ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_NESTED_OPERATION_32)),
          Either.left(ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_OPERATION_2)),
          Either.right(new ImmutableEgg(5, new Yolk(Condition.GOOD, Color.YELLOW))),
          Either.left(ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_VALIDATION_3)),
          Either.left(ValidationFailures.TOO_LATE_TO_HATCH_2),
          Either.right(new ImmutableEgg(14, new Yolk(Condition.GOOD, Color.GOLD))),
          Either.left(ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_VALIDATION_3)),
          Either.left(
              ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_NESTED_OPERATION_32)),
          Either.left(ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3),
          Either.left(ValidationFailures.NO_CHILD_TO_VALIDATE));

  public static final List<Either<ValidationFailure, ?>> EXPECTED_DECLARATIVE_VALIDATION_RESULTS_2 =
      List.of(
          Either.left(ValidationFailures.NOTHING_TO_VALIDATE),
          Either.left(ValidationFailures.ABOUT_TO_HATCH_P_3),
          Either.left(
              ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_NESTED_OPERATION_32)),
          Either.left(ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_OPERATION_2)),
          Either.right(new ImmutableEgg(5, new Yolk(Condition.GOOD, Color.YELLOW))),
          Either.left(ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_VALIDATION_3)),
          Either.left(ValidationFailures.TOO_LATE_TO_HATCH_2),
          Either.right(new ImmutableEgg(14, new Yolk(Condition.GOOD, Color.GOLD))),
          Either.left(ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_VALIDATION_3)),
          Either.left(
              ValidationFailure.withErrorMessage(ThrowableMsgs.THROWABLE_NESTED_OPERATION_32)),
          Either.left(ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3),
          Either.left(ValidationFailures.NO_CHILD_TO_VALIDATE));

  // TODO: 5/10/20 Incomplete, need to build it
  public static List<List<Either<ValidationFailure, ?>>>
      getExpectedImmutableEggAccumulatedValidationResults() {
    return List.of(
        List.of(
            Either.left(ValidationFailures.NO_EGG_TO_VALIDATE_1),
            Either.left(ValidationFailure.withErrorMessage("null")),
            Either.left(ValidationFailure.withErrorMessage("null")),
            Either.left(ValidationFailures.NO_PARENT_TO_VALIDATE_CHILD),
            Either.left(ValidationFailures.NO_PARENT_TO_VALIDATE_CHILD),
            Either.left(ValidationFailure.withErrorMessage("null")),
            Either.left(ValidationFailure.withErrorMessage("null")),
            Either.left(ValidationFailures.NO_PARENT_TO_VALIDATE_CHILD)));
  }
}
