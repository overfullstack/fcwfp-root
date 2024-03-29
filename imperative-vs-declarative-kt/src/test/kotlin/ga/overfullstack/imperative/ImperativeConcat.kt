package ga.overfullstack.imperative

import com.google.common.truth.Truth.assertThat
import ga.overfullstack.common.DELIMITER
import ga.overfullstack.common.EXPECTED_RESULT
import ga.overfullstack.common.TEAM
import ga.overfullstack.common.extractLastName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ImperativeConcat {

  @Test
  fun `1 - LastName Concat`() {
    val actual = concatLastName1(TEAM)
    assertThrows<AssertionError> { assertThat(actual).isEqualTo(EXPECTED_RESULT) }
  }

  @Test
  fun `2 - LastName Concat`() {
    val actual = concatLastName2(TEAM)
    assertThrows<AssertionError> { assertThat(actual).isEqualTo(EXPECTED_RESULT) }
  }

  @Test
  fun `3 - LastName Concat`() {
    val actual = concatLastNames(TEAM)
    assertThat(actual).isEqualTo(EXPECTED_RESULT)
  }

  companion object {
    private fun concatLastName1(team: List<String?>): String {
      var output = ""
      for (teamMemberName in team) {
        val lastName = extractLastName(teamMemberName)
        output += (lastName + DELIMITER)
      }
      return output
    }

    private fun concatLastName2(team: List<String?>?): String { // Also want to support null teams
      if (team == null) {
        return ""
      }
      var output = ""
      for (teamMemberName in team) {
        if (teamMemberName != null) {
          val teamMemberNameTrimmed = teamMemberName.trim { it <= ' ' }
          if (teamMemberNameTrimmed.isNotEmpty()) {
            val lastName = extractLastName(teamMemberName)
            output += (lastName + DELIMITER)
          }
        }
      }
      return output
    }

    fun concatLastNames(team: List<String?>?): String {
      if (team == null) {
        return ""
      }
      var output = ""
      var isFirstFlag = true
      for (teamMemberName in team) { // HTD-1: Looping through the list
        if (teamMemberName != null) { // WTD-11: Deal with nulls
          val teamMemberNameTrimmed =
            teamMemberName.trim() // WTD-12: Deal with only white space names
          if (teamMemberNameTrimmed.isNotEmpty()) { // WTD-13: Deal with empty names
            if (!isFirstFlag) { // Catch: Should not prepend delimiter for first entry.
              output += DELIMITER
            }
            val lastName = extractLastName(teamMemberNameTrimmed) // WTD-2: Extracting last name
            output += lastName // HTD-2: Aggregating the results with the delimiter.
            isFirstFlag = false
          }
        }
      }
      return output
    }
  }
}
