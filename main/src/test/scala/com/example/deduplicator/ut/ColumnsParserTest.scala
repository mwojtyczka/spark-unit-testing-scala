package com.example.deduplicator.ut

import com.example.deduplicator.ColumnsParser
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class ColumnsParserTest extends FlatSpec with Matchers with BeforeAndAfterEach {

  it should "parse one element" in {
    ColumnsParser.parse("element1") should be(Seq[String]("element1"))
  }

  it should "parse multiple element" in {
    ColumnsParser.parse("element1,element2,element3") should be(Seq[String]("element1", "element2", "element3"))
  }

  it should "not parse given null" in {
    assertThrows[NullPointerException] {
      ColumnsParser.parse(null)
    }
  }
}
