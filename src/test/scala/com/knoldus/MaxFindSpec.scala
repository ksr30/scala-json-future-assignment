package com.knoldus

import org.scalatest._
import scala.concurrent.Future

class MaxFindSpec extends AsyncFlatSpec with BeforeAndAfterAll {



var maxFind: MaxFind = new MaxFind
  override def beforeAll(): Unit = {
    maxFind = new MaxFind()
  }
  override def afterAll(): Unit = {
    if (maxFind != null)  {
      maxFind = null
    }
  }
    "Max Finder" should "eventually find user with maximum post" in {
    val futureFind: Future[String] = maxFind.maxFinder("Post")
    futureFind map { find => assert(find == "Clementina DuBuque") }
  }

  it should "eventually find user with maximum comments" in {
    val futureFindComment: Future[String] = maxFind.maxFinder("Comment")
    futureFindComment map { find => assert(find == "Clementina DuBuque") }
  }
}




