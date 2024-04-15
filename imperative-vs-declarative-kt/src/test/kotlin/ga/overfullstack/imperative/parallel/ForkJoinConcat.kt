/* gakshintala created on 4/14/20 */
package ga.overfullstack.imperative.parallel

import com.google.common.truth.Truth.assertThat
import ga.overfullstack.common.EXPECTED_RESULT
import ga.overfullstack.common.TEAM
import ga.overfullstack.imperative.ImperativeConcat.Companion.concatLastNames
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask
import org.junit.jupiter.api.Test

internal class MyRecursiveTask(private val team: List<String?>?) : RecursiveTask<String>() {
  override fun compute(): String =
    team?.let {
      if (it.size > MIN_TEAM_SIZE) {
        val mid = it.size / 2
        val myRecursiveTask1 = MyRecursiveTask(it.subList(0, mid))
        val myRecursiveTask2 = MyRecursiveTask(it.subList(mid, it.size))
        myRecursiveTask1.fork()
        myRecursiveTask2.fork()
        concatResultsFromForks(myRecursiveTask1, myRecursiveTask2)
      } else {
        concatLastNames(it) // Using concatLastNames as MIN_TEAM_SIZE can be greater than 2
      }
    } ?: ""

  companion object {
    private const val serialVersionUID = -5978274303314688527L
    private const val MIN_TEAM_SIZE = 2 // In real-world, DO NOT have it below 10,000
  }
}

class ForkJoinConcat {

  @Test
  fun parallelWithForkJoinPool() {
    val forkJoinPool = ForkJoinPool(AVAILABLE_CORES)
    val actualResult = forkJoinPool.invoke(MyRecursiveTask(TEAM))
    println(actualResult)
    assertThat(actualResult).isEqualTo(EXPECTED_RESULT)
  }
}
