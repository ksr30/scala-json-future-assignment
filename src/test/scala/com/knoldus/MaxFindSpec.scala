package com.knoldus
import org.scalatest._
class ArithmeticSpec extends FlatSpec with BeforeAndAfterAll {
  var arithmeticOperation: Arthmetic = new Arthmetic
  override def beforeAll(): Unit = {
    arithmeticOperation = new Arthmetic()
  }
  override def afterAll(): Unit = {
    if (arithmeticOperation != null)  {
      arithmeticOperation = null
    }
  }
  "Add method" should "add two numbers" in {
    val actualResult = arithmeticOperation.add(5, 5)
    val expectedResult = 10
    assert(expectedResult == actualResult)
  }
  "Subtract method" should "subtract two numbers" in {
    val actualResult = arithmeticOperation.subtract(5, 5)
    val expectedResult = 0
    assert(expectedResult == actualResult)
  }
  "divide method" should "divide two numbers" in {
    val actualResult = arithmeticOperation.divide(5, 5)
    val expectedResult = 0
    assert(expectedResult == actualResult)
  }
  "multiply method" should "multiply two numbers" in {
    val actualResult = arithmeticOperation.multiply(5, 5)
    val expectedResult = 25
    assert(expectedResult == actualResult)
  }
}




