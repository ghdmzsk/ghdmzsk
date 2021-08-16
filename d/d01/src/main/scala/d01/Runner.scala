package d01

object Runner {
  lazy val number2Zero: Number2 = Number2T(() => number2Zero)

  def main(args: Array[String]): Unit = {
    {
      // 12 + 12
      {
        val number1 = Number2S(() => Number2S(() => number2Zero))
        lazy val number2: Number3 = Number5(() => Number5(() => Number5(() => number3)))
        lazy val number3: Number3 = Number4(() => number3)
        val number4 = number1.method1(number2)
        assert(number4 == Number1S(Number1S(Number1S(Number1S(Number1S(Number1T))))))
      }
      {
        lazy val number1: Number3 = Number5(() => Number5(() => Number5(() => number2)))
        lazy val number2: Number3 = Number4(() => number2)
        val number4 = number2Zero.method1(number1)
        assert(number4 == Number1S(Number1S(Number1S(Number1T))))
      }
      {
        val number1 = Number2S(() => Number2S(() => number2Zero))
        lazy val number3: Number3 = Number4(() => number3)
        val number4 = number1.method1(number3)
        assert(number4 == Number1S(Number1S(Number1T)))
      }
    }

    {
      // 12 - 12
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero))))
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => number3)))
        lazy val number3: Number3 = Number4(() => number3)
        val number4 = number1.method1(number2)
        assert(number4 == Number1S(Number1T))
      }
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => number2Zero)))
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => number3)))
        lazy val number3: Number3 = Number4(() => number3)
        val number4 = number1.method1(number2)
        assert(number4 == Number1T)
      }
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => number2Zero)))
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => Number6(() => number3))))
        lazy val number3: Number3 = Number4(() => number3)
        val number4 = number1.method1(number2)
        assert(number4 == Number1T)
      }
    }

    {
      // 12 × 12
      val number1 = Number2S(() => Number2S(() => Number2S(() => number2Zero)))
      lazy val number2: Number3 = Number5(() => Number5(() => Number5(() => Number5(() => number3))))
      lazy val number3: Number3 = Number6(() => number2)
      val number4 = number1.method1(number3)
      assert(
        number4 == Number1S(
          Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T)))))))))))
        )
      )
    }

    {
      // 12 ÷ 12（上舍法）
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero)))))))))
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => Number6(() => number3))))
        lazy val number3: Number3 = Number5(() => number2)
        val number4 = number1.method1(number2)
        assert(number4 == Number1S(Number1S(Number1T)))
      }
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero)))))))))
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => number3)))
        lazy val number3: Number3 = Number5(() => number2)
        val number4 = number1.method1(number2)
        assert(number4 == Number1S(Number1S(Number1S(Number1T))))
      }
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero))))))))))
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => number3)))
        lazy val number3: Number3 = Number5(() => number2)
        val number4 = number1.method1(number2)
        assert(number4 == Number1S(Number1S(Number1S(Number1T))))
      }
    }
  }
}
