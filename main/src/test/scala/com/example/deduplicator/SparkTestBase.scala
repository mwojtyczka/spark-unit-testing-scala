package com.example.deduplicator

import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

abstract class SparkTestBase extends FlatSpec with BeforeAndAfterAll with Matchers {

  val spark: SparkSession = getSparkSession

  override def afterAll(): Unit = {
    spark.stop()
  }

  def getSparkSession: SparkSession = {
    // Location for internal Derby database metastore_db with a derby.log
    val warehouseLocation = "file:${system:user.dir}/spark-warehouse"

    SparkSession
      .builder
      .master("local[*]")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .getOrCreate()
  }

  def assertResults(expectedRaw: DataFrame, actualRaw: DataFrame, columns: Seq[String] = Seq("*")): Unit = {

    val expected = expectedRaw.select(columns.map(col): _*)
    val actual = actualRaw.select(columns.map(col): _*)

    println("Actual:")
    actual.show()

    println("Expected:")
    expected.show()

    val exceptLeft = actual.except(expected)
    val exceptRight = expected.except(actual)

    if (exceptLeft.count != 0) {
      println("Except left:")
      exceptLeft.show()
    }

    if (exceptRight.count != 0) {
      println("Except right:")
      exceptRight.show()
    }

    exceptLeft.count shouldEqual 0 // assert(exceptLeft.count == 0)
    exceptRight.count shouldEqual 0 // assert(exceptRight.count == 0)
  }
}
