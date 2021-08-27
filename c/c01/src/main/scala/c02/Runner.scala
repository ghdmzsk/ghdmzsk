package c02

object Runner {
  val item1  = Item("1")
  val item2  = Item("2")
  val item3  = Item("3")
  val item4  = Item("4")
  val item5  = Item("5")
  val item6  = Item("6")
  val item7  = Item("7")
  val item8  = Item("8")
  val item9  = Item("9")
  val item10 = Item("10")

  lazy val numberToZero: NumberTo = NumberToT(() => numberToZero)

  def main(args: Array[String]): Unit = {
    {
      val number1: NumberTo = NumberToS(() => NumberToS(() => NumberToS(() => NumberToS(() => numberToZero, item1), item2), item3), item4)
      val number2: Result   = ResultS(ResultS(ResultS(ResultS(ResultT, item1), item2), item3), item4)
      assert(number1.method1(NumberBeT) == number2)
    }
    {
      val number1: NumberBe = NumberBeS(() => NumberBeS(() => NumberBeS(() => NumberBeS(() => NumberBeT, item1), item2), item3), item4)
      val number2: Result   = ResultS(ResultS(ResultS(ResultS(ResultT, item1), item2), item3), item4)
      assert(numberToZero.method1(number1) == number2)
    }
    {
      val number1: NumberTo = NumberToS(() => NumberToS(() => NumberToS(() => numberToZero, item5), item6), item7)
      val number2: NumberBe = NumberBeS(() => NumberBeS(() => NumberBeS(() => NumberBeS(() => NumberBeT, item1), item2), item3), item4)
      val number3: Result =
        ResultS(ResultS(ResultS(ResultS(ResultS(ResultS(ResultS(ResultT, item1), item2), item3), item4), item5), item6), item7)
      assert(number1.method1(number2) == number3)
    }
  }
}
