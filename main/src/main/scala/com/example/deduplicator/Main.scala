package com.example.deduplicator

import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

object Main {
  private val logger = LoggerFactory.getLogger(classOf[Deduplicator])

  /**
    * Main entry point of the application.
    * @param args arguments
    */
  def main(args: Array[String]) {

    val inputPath = args(0).trim
    val dedupColumns = ColumnsParser.parse(args(1).trim)

    logger.info("App started")

    val spark: SparkSession = SparkSession.builder.getOrCreate

    try {
      spark.conf.set("spark.sql.sources.partitionColumnTypeInference.enabled", "false")
      spark.conf.set("spark.sql.shuffle.partitions", 10)

      new Deduplicator(spark).dedup(Option(spark.read.parquet(inputPath)), dedupColumns)
    } finally {
      spark.stop()
    }

    logger.info("App finished")
  }
}
