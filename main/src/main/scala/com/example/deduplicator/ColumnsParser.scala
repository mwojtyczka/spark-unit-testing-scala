package com.example.deduplicator

object ColumnsParser {

  /**
    * Parse given column string by splitting and filtering out empty strings.
    * @param colsString column string.
    * @return splitted columns
    */
  def parse(colsString: String): Seq[String] = {
    colsString.split(",").toSeq.map(_.trim).filter(_ != "")
  }
}
