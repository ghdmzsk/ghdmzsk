package a01_03

import scala.util.Random

object Runner {

  def main(args: Array[String]): Unit = {
    {
      val l1      = List.fill(200)(Random.nextInt())
      val number1 = Base.number1FromList(l1)
      val number2 = number1.dropRight.dropRight.dropRight
      val l2      = Base.number1ToList(number2)
      val l3      = Compare.dropRight(Compare.dropRight(Compare.dropRight(l1)))
      assert(l1.dropRight(3) == l2)
      assert(l1.dropRight(3) == l3)
    }
  }

}
