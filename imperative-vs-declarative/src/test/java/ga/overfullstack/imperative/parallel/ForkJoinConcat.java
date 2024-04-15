package ga.overfullstack.imperative.parallel;

import static ga.overfullstack.common.Common.EXPECTED_RESULT;
import static ga.overfullstack.common.Common.TEAM;
import static ga.overfullstack.imperative.ImperativeConcat.concatLastNames;
import static ga.overfullstack.imperative.parallel.Util.AVAILABLE_CORES;
import static ga.overfullstack.imperative.parallel.Util.concatResultsFromForks;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import org.junit.jupiter.api.Test;

class ForkJoinConcat {

  @Test
  void parallelWithForkJoinPool() {
    var forkJoinPool = new ForkJoinPool(AVAILABLE_CORES);
    var actualResult = forkJoinPool.invoke(new MyRecursiveTask(TEAM));
    System.out.println(actualResult);
    assertEquals(EXPECTED_RESULT, actualResult);
  }

  static class MyRecursiveTask extends RecursiveTask<String> {
    private static final long serialVersionUID = -5978274303314688527L;

    private static final int MIN_TEAM_SIZE = 2; // In real-world, DO NOT have it below 10,000
    private final List<String> team;

    MyRecursiveTask(List<String> team) {
      this.team = team;
    }

    @Override
    protected String compute() {
      if (team.size() > MIN_TEAM_SIZE) {
        var mid = team.size() / 2;
        final var myRecursiveTask1 = new MyRecursiveTask(team.subList(0, mid));
        final var myRecursiveTask2 = new MyRecursiveTask(team.subList(mid, team.size()));
        myRecursiveTask1.fork();
        myRecursiveTask2.fork();

        return concatResultsFromForks(myRecursiveTask1, myRecursiveTask2);
      } else {
        return concatLastNames(team);
      }
    }
  }
}
