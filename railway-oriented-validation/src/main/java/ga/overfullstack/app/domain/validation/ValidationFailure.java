package ga.overfullstack.app.domain.validation;

public interface ValidationFailure {

  static ValidationFailure withErrorMessage(String exceptionMessage) {
    return new FailureForThrowable(exceptionMessage);
  }

  static ValidationFailure withThrowable(Throwable throwable) {
    return new FailureForThrowable(throwable.getMessage());
  }

  record FailureForThrowable(String exceptionMessage) implements ValidationFailure {}
}
