package d01

object Runner {
  def main(args: Array[String]): Unit = {
    {
      // 12 + 12
      val number1 = Number2S(Number2S(Number2T))
      val number2 = Number5(Number5(Number5(Number4.zero)))
      val number3 = number1.method1(number2)
      assert(number3 == Number1S(Number1S(Number1S(Number1S(Number1S(Number1T))))))
    }
    {
      // 12 - 12
      val number1 = Number2S(Number2S(Number2S(Number2S(Number2T))))
      val number2 = Number6(Number6(Number6(Number4.zero)))
      val number3 = number1.method1(number2)
      assert(number3 == Number1S(Number1T))
    }
    {
      // 12 - 12
      val number1 = Number2S(Number2S(Number2S(Number2T)))
      val number2 = Number6(Number6(Number6(Number4.zero)))
      val number3 = number1.method1(number2)
      assert(number3 == Number1T)
    }
    {
      // 12 - 12
      val number1 = Number2S(Number2S(Number2S(Number2T)))
      val number2 = Number6(Number6(Number6(Number6(Number4.zero))))
      val number3 = number1.method1(number2)
      assert(number3 == Number1T)
    }
    {
      // 12 × 12
      val number1               = Number2S(Number2S(Number2S(Number2T)))
      lazy val number2: Number3 = Number5(Number5(Number5(Number5(number3))))
      lazy val number3: Number3 = Number6(number2)
      val number4               = number1.method1(number2)
      assert(
        number4 == Number1S(
          Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T)))))))))))
        )
      )
    }
    {
      // 12 ÷ 12
      val number1               = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T)))))))))
      lazy val number2: Number3 = Number6(Number6(Number6(Number6(number3))))
      lazy val number3: Number3 = Number5(number2)
      val number4               = number1.method1(number3)
      assert(number4 == Number1S(Number1S(Number1S(Number1T))))
    }
    {
      // 12 ÷ 12
      val number1               = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T)))))))))
      lazy val number2: Number3 = Number6(Number6(Number6(number3)))
      lazy val number3: Number3 = Number5(number2)
      val number4               = number1.method1(number3)
      assert(number4 == Number1S(Number1S(Number1S(Number1T))))
    }
    {
      // 12 ÷ 12
      val number1 = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T))))))))))
      lazy val number2: Number3 = Number6(Number6(Number6(number3)))
      lazy val number3: Number3 = Number5(number2)
      val number4               = number1.method1(number3)
      assert(number4 == Number1S(Number1S(Number1S(Number1S(Number1T)))))
    }
  }
}