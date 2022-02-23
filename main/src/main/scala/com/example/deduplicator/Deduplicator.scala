package com.example.deduplicator

import org.apache.spark.sql.{DataFrame, SparkSession}

class Deduplicator(spark: SparkSession) {

  def dedup(data: Option[DataFrame], cols: Seq[String]): Option[DataFrame] = {

    data match {
      case Some(i) => Option(i.dropDuplicates(cols))
      case other => other
    }
  }
}
