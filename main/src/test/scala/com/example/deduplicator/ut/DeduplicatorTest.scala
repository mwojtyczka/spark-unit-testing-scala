package com.example.deduplicator.ut

import com.example.deduplicator.{Deduplicator, SparkTestBase}
import org.apache.spark.sql.DataFrame
import org.scalatest.BeforeAndAfterEach

class DeduplicatorTest extends SparkTestBase with BeforeAndAfterEach {

  private val dedupColumns = Seq("field1", "field2")
  private val columns = dedupColumns :+ "timestamp"

  it should "deduplicate" in {

    // When
    val input: Option[DataFrame] = getInput

    // Given
    val output: Option[DataFrame] = new Deduplicator(spark).dedup(input, dedupColumns)

    // Then
    assertResults(output.get, getExpectedOutput)
  }

  it should "do nothing given empty input" in {

    // When
    val input: Option[DataFrame] = Option.empty

    // Given
    val output: Option[DataFrame] = new Deduplicator(spark).dedup(input, dedupColumns)

    // Then
    assert(output.isEmpty)
  }

  private def getInput: Option[DataFrame] = {

    val data = Seq(
      ("1", "1", 1L),
      ("1", "1", 1L),
      ("1", "1", 2L),
      ("1", "1", 2L),
      ("2", "1", 1L),
      ("2", "1", 1L),
      ("2", "1", 1L),
      ("3", "1", 1L),
      ("1", "2", 1L),
      ("1", "2", 1L)
    )

    Option(spark.createDataFrame(data).toDF(columns:_*))
  }

  private def getExpectedOutput: DataFrame = {

    val data = Seq(
      ("1", "1", 1L),
      ("2", "1", 1L),
      ("3", "1", 1L),
      ("1", "2", 1L)
    )

    spark.createDataFrame(data).toDF(columns:_*)
  }
}
