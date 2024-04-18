package ga.overfullstack.app.declarative;

import static io.vavr.CheckedFunction1.liftTry;

import ga.overfullstack.app.domain.ImmutableEgg;
import ga.overfullstack.app.domain.Rules;
import ga.overfullstack.app.domain.Yolk;
import ga.overfullstack.app.domain.validation.ValidationFailure;
import ga.overfullstack.app.domain.validation.ValidationFailures;
import io.vavr.control.Either;

/**
 * <pre>
 * This class contains validations as functions.
 *
 * Requirements
 * Partial Failures
 *
 * Problems solved:
 * - Octopus Orchestrator - 😵 dead
 * - Mutation to Transformation
 * - Unit-Testability - 👍
 * - Complexity - Minimum
 * - Chaos to Order
 * </pre>
 */
public final class RailwayValidation {
  private RailwayValidation() {
  }

  public static Either<ValidationFailure, ?> validate1(Either<ValidationFailure, ImmutableEgg> validatedEgg) {
    return validatedEgg
        .filter(Rules::simpleOperation1)
        .getOrElse(() -> Either.left(ValidationFailures.NO_EGG_TO_VALIDATE_1));
  }

  public static Either<ValidationFailure, ?> validate2(Either<ValidationFailure, ImmutableEgg> validatedEgg) {
    return validatedEgg
        .map(egg -> liftTry(Rules::throwableOperation2).apply(egg))
        .flatMap(
            tryResult -> tryResult.toEither().mapLeft(cause -> ValidationFailure.withErrorMessage(cause.getMessage())))
        .filter(Boolean::booleanValue)
        .getOrElse(() -> Either.left(ValidationFailures.TOO_LATE_TO_HATCH_2));
  }

  private static Either<ValidationFailure, ?> validateParent3(Either<ValidationFailure, ImmutableEgg> validatedEgg) {
    return validatedEgg
        .map(egg -> liftTry(Rules::throwableOperation3).apply(egg))
        .flatMap(
            tryResult -> tryResult.toEither().mapLeft(cause -> ValidationFailure.withErrorMessage(cause.getMessage())))
        .filter(Boolean::booleanValue)
        .getOrElse(() -> Either.left(ValidationFailures.ABOUT_TO_HATCH_P_3));
  }

  public static Either<ValidationFailure, ?> validateChild31(Either<ValidationFailure, Yolk> validatedYolk) {
    return validatedYolk
        .map(yolk -> liftTry(Rules::throwableNestedOperation3).apply(yolk))
        .flatMap(
            tryResult -> tryResult.toEither().mapLeft(cause -> ValidationFailure.withErrorMessage(cause.getMessage())))
        .filter(Boolean::booleanValue)
        .getOrElse(() -> Either.left(ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3));
  }

  public static Either<ValidationFailure, ?> validateChild32(Either<ValidationFailure, Yolk> validatedYolk) {
    return validatedYolk
        .map(yolk -> liftTry(Rules::throwableNestedOperation3).apply(yolk))
        .flatMap(
            tryResult -> tryResult.toEither().mapLeft(cause -> ValidationFailure.withErrorMessage(cause.getMessage())))
        .filter(Boolean::booleanValue)
        .getOrElse(() -> Either.left(ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3));
  }

  private static Either<ValidationFailure, ?> validateParent41(Either<ValidationFailure, ImmutableEgg> validatedEgg) {
    return validatedEgg
        .map(egg -> liftTry(Rules::throwableOperation3).apply(egg))
        .flatMap(
            tryResult -> tryResult.toEither().mapLeft(cause -> ValidationFailure.withErrorMessage(cause.getMessage())))
        .filter(Boolean::booleanValue)
        .getOrElse(() -> Either.left(ValidationFailures.ABOUT_TO_HATCH_P_3));
  }

  private static Either<ValidationFailure, ?> validateParent42(Either<ValidationFailure, ImmutableEgg> validatedEgg) {
    return validatedEgg
        .map(egg -> liftTry(Rules::throwableOperation3).apply(egg))
        .flatMap(
            tryResult -> tryResult.toEither().mapLeft(cause -> ValidationFailure.withErrorMessage(cause.getMessage())))
        .filter(Boolean::booleanValue)
        .getOrElse(() -> Either.left(ValidationFailures.ABOUT_TO_HATCH_P_3));
  }

  public static Either<ValidationFailure, ?> validateChild4(Either<ValidationFailure, Yolk> validatedYolk) {
    return validatedYolk
        .map(yolk -> liftTry(Rules::throwableNestedOperation3).apply(yolk))
        .flatMap(
            tryResult -> tryResult.toEither().mapLeft(cause -> ValidationFailure.withErrorMessage(cause.getMessage())))
        .filter(Boolean::booleanValue)
        .getOrElse(() -> Either.left(ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3));
  }

}
