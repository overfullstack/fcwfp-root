package ga.overfullstack.app.declarative;/* gakshintala created on 8/20/19 */

import static ga.overfullstack.algebra.ConfigDsl.liftAllToParentValidationType;
import static ga.overfullstack.algebra.ConfigDsl.liftToParentValidationType;
import static ga.overfullstack.app.declarative.RailwayValidation2.validate1Simple;
import static ga.overfullstack.app.declarative.RailwayValidation2.validate2Throwable;
import static ga.overfullstack.app.declarative.RailwayValidation2.validateChild31;
import static ga.overfullstack.app.declarative.RailwayValidation2.validateChild32;
import static ga.overfullstack.app.declarative.RailwayValidation2.validateChild4;
import static ga.overfullstack.app.declarative.RailwayValidation2.validateParent3;
import static ga.overfullstack.app.declarative.RailwayValidation2.validateParent41;
import static ga.overfullstack.app.declarative.RailwayValidation2.validateParent42;

import ga.overfullstack.algebra.types.Validator;
import ga.overfullstack.app.common.Constants;
import ga.overfullstack.app.domain.ImmutableEgg;
import ga.overfullstack.app.domain.Yolk;
import ga.overfullstack.app.domain.validation.ValidationFailure;
import ga.overfullstack.app.domain.validation.ValidationFailures;
import io.vavr.collection.List;
import java.util.stream.Stream;

/**
 * Config to prepare various instances and constants. gakshintala created on 3/23/20.
 */
public final class Config {
  private Config() {
  }

  /**
   * The Validation Chains.<br> If these parent-child dependencies are complex, we can make use of some graph algorithm
   * to create a linear dependency graph of all validations.
   */
  private static final List<Validator<ImmutableEgg, ValidationFailure>> PARENT_VALIDATION_CHAIN =
      List.of(validate1Simple, validate2Throwable, validateParent3);

  private static final List<Validator<Yolk, ValidationFailure>> CHILD_VALIDATION_CHAIN
      = List.of(validateChild31, validateChild32);

  public static final List<Validator<ImmutableEgg, ValidationFailure>> EGG_VALIDATION_CHAIN =
      PARENT_VALIDATION_CHAIN
          .appendAll(
              liftAllToParentValidationType(CHILD_VALIDATION_CHAIN, ImmutableEgg::yolk, ValidationFailures.NO_PARENT_TO_VALIDATE_CHILD,
                  ValidationFailures.NO_CHILD_TO_VALIDATE))
          .appendAll(List.of(validateParent41, validateParent42,
              liftToParentValidationType(validateChild4,
                  ImmutableEgg::yolk, ValidationFailures.NO_PARENT_TO_VALIDATE_CHILD, ValidationFailures.NO_CHILD_TO_VALIDATE)));

  /**
   * The above chain can also be achieved this way using `andThen.
   */
    /*private static final Function1<Either<ValidationFailure, ImmutableEgg>, Either<ValidationFailure, ?>>
            PARENT_VALIDATION_COMPOSITION = validate1Simple
            .andThen(liftThrowable(validate2Throwable, ValidationFailure::withThrowable))
            .andThen(validateParent3);
    private static final Function1<Either<ValidationFailure, Yolk>, Either<ValidationFailure, ?>>
            CHILD_VALIDATION_COMPOSITION = liftThrowable(validateChild31, ValidationFailure::withThrowable).andThen(validateChild32);*/
  public static <E> Stream<E> getStreamBySize(List<E> list) {
    return list.size() >= Constants.MAX_SIZE_FOR_PARALLEL
        ? list.toJavaParallelStream()
        : list.toJavaStream();
  }

}

