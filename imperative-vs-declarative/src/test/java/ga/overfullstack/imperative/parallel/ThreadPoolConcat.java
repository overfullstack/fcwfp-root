package ga.overfullstack.imperative.parallel;

import static ga.overfullstack.common.Common.EXPECTED_RESULT;
import static ga.overfullstack.common.Common.TEAM;
import static ga.overfullstack.imperative.ImperativeConcat.concatLastNames;
import static ga.overfullstack.imperative.parallel.Util.AVAILABLE_CORES;
import static ga.overfullstack.imperative.parallel.Util.concatResults;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class ThreadPoolConcat {

  @Test
  void testLastNameFinderThreadPool() {
    final var actualResult = parallelWithThreadPool(TEAM);
    System.out.println(actualResult);
    assertEquals(EXPECTED_RESULT, actualResult);
  }

  private String parallelWithThreadPool(List<String> team) {
    var executor = Executors.newFixedThreadPool(AVAILABLE_CORES - 1);
    var futureList = new ArrayList<Future<String>>();
    var segmentLen = team.size() / AVAILABLE_CORES;
    if (segmentLen == 0) {
      segmentLen = team.size();
    }

    // Split the list to be dealt by different futures.
    var offset = 0;
    while (offset < team.size()) {
      final var from = offset;
      final var to = offset + segmentLen;
      futureList.add(
          executor.submit(
              new Callable<>() {
                @Override
                public String call() {
                  return concatLastNames(team.subList(from, to));
                }
              }));
      offset += segmentLen;
    }

    // Aggregate results
    var results = new ArrayList<String>();
    for (var future : futureList) {
      try {
        results.add(future.get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    // Deal with last left-out segment
    if (offset < team.size()) {
      results.add(concatLastNames(team.subList(team.size() - segmentLen, team.size())));
    }

    executor.shutdown();
    try {
      executor.awaitTermination(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return concatResults(results);
  }
}
