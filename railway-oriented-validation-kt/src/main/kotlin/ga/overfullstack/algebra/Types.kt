package algebra

import arrow.core.Either
import kotlinx.coroutines.CoroutineScope

typealias Validator<ValidatableT, FailureT> = (ValidatableT) -> Either<FailureT, Any?>

typealias FailFastStrategy<ValidatableT, FailureT> =
  suspend CoroutineScope.(ValidatableT?) -> Either<FailureT, ValidatableT?>

typealias AccumulationStrategy<ValidatableT, FailureT> =
  suspend CoroutineScope.(ValidatableT?) -> List<Either<FailureT, ValidatableT?>>
