package ga.overfullstack.app.declarative;


import static io.vavr.CheckedFunction1.liftTry;

import ga.overfullstack.app.domain.ImmutableEgg;
import ga.overfullstack.app.domain.Rules;
import ga.overfullstack.app.domain.Yolk;
import io.vavr.control.Either;
import java.util.function.UnaryOperator;

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
public class RailwayValidation1 {

  public static final UnaryOperator<Either<ga.overfullstack.app.domain.validation.ValidationFailure, ImmutableEgg>> validate1Simple = validatedEgg -> validatedEgg
      .filterOrElse(Rules::simpleOperation1, ignore -> ga.overfullstack.app.domain.validation.ValidationFailures.NO_EGG_TO_VALIDATE_1);

  public static final UnaryOperator<Either<ga.overfullstack.app.domain.validation.ValidationFailure, ImmutableEgg>> validate2Throwable = validatedEgg -> validatedEgg
      .map(egg -> liftTry(Rules::throwableOperation2).apply(egg))
      .flatMap(tryResult -> tryResult.toEither()
          .mapLeft(cause -> ga.overfullstack.app.domain.validation.ValidationFailure.withErrorMessage(cause.getMessage()))) // Trick in the Monad
      .filter(Boolean::booleanValue)
      .getOrElse(() -> Either.left(ga.overfullstack.app.domain.validation.ValidationFailures.TOO_LATE_TO_HATCH_2))
      .flatMap(ignore -> validatedEgg);

  public static final UnaryOperator<Either<ga.overfullstack.app.domain.validation.ValidationFailure, ImmutableEgg>> validateParent3 = validatedEgg -> validatedEgg
      .map(egg -> liftTry(Rules::throwableOperation3).apply(egg))
      .flatMap(
          tryResult -> tryResult.toEither().mapLeft(cause -> ga.overfullstack.app.domain.validation.ValidationFailure.withErrorMessage(cause.getMessage())))
      .filter(Boolean::booleanValue)
      .getOrElse(() -> Either.left(ga.overfullstack.app.domain.validation.ValidationFailures.ABOUT_TO_HATCH_P_3))
      .flatMap(ignore -> validatedEgg);

  public static final UnaryOperator<Either<ga.overfullstack.app.domain.validation.ValidationFailure, Yolk>> validateChild31 = validatedYolk -> validatedYolk
      .map(yolk -> liftTry(Rules::throwableNestedOperation3).apply(yolk))
      .flatMap(
          tryResult -> tryResult.toEither().mapLeft(cause -> ga.overfullstack.app.domain.validation.ValidationFailure.withErrorMessage(cause.getMessage())))
      .filter(Boolean::booleanValue)
      .getOrElse(() -> Either.left(ga.overfullstack.app.domain.validation.ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3))
      .flatMap(ignore -> validatedYolk);

  public static final UnaryOperator<Either<ga.overfullstack.app.domain.validation.ValidationFailure, Yolk>> validateChild32 = validatedYolk -> validatedYolk
      .map(yolk -> liftTry(Rules::throwableNestedOperation3).apply(yolk))
      .flatMap(
          tryResult -> tryResult.toEither().mapLeft(cause -> ga.overfullstack.app.domain.validation.ValidationFailure.withErrorMessage(cause.getMessage())))
      .filter(Boolean::booleanValue)
      .getOrElse(() -> Either.left(ga.overfullstack.app.domain.validation.ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3))
      .flatMap(ignore -> validatedYolk);

  public static final UnaryOperator<Either<ga.overfullstack.app.domain.validation.ValidationFailure, ImmutableEgg>> validateParent41 = validatedEgg -> validatedEgg
      .map(egg -> liftTry(Rules::throwableOperation3).apply(egg))
      .flatMap(
          tryResult -> tryResult.toEither().mapLeft(cause -> ga.overfullstack.app.domain.validation.ValidationFailure.withErrorMessage(cause.getMessage())))
      .filter(Boolean::booleanValue)
      .getOrElse(() -> Either.left(ga.overfullstack.app.domain.validation.ValidationFailures.ABOUT_TO_HATCH_P_3))
      .flatMap(ignore -> validatedEgg);

  public static final UnaryOperator<Either<ga.overfullstack.app.domain.validation.ValidationFailure, ImmutableEgg>> validateParent42 = validatedEgg -> validatedEgg
      .map(egg -> liftTry(Rules::throwableOperation3).apply(egg))
      .flatMap(
          tryResult -> tryResult.toEither().mapLeft(cause -> ga.overfullstack.app.domain.validation.ValidationFailure.withErrorMessage(cause.getMessage())))
      .filter(Boolean::booleanValue)
      .getOrElse(() -> Either.left(ga.overfullstack.app.domain.validation.ValidationFailures.ABOUT_TO_HATCH_P_3))
      .flatMap(ignore -> validatedEgg);

  // Child with multiple Parent Validations
  public static final UnaryOperator<Either<ga.overfullstack.app.domain.validation.ValidationFailure, Yolk>> validateChild4 = validatedYolk -> validatedYolk
      .map(yolk -> liftTry(Rules::throwableNestedOperation3).apply(yolk))
      .flatMap(
          tryResult -> tryResult.toEither().mapLeft(cause -> ga.overfullstack.app.domain.validation.ValidationFailure.withErrorMessage(cause.getMessage())))
      .filter(Boolean::booleanValue)
      .getOrElse(() -> Either.left(ga.overfullstack.app.domain.validation.ValidationFailures.YOLK_IS_IN_WRONG_COLOR_C_3))
      .flatMap(ignore -> validatedYolk);

}
