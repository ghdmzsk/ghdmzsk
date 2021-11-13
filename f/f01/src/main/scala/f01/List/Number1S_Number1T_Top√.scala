package f01.List
import f01._

/* 一叠资料摔下来，张口就道：整理好这些，汇总成 excel。订好两天后去 xx 的机票。后天出发前有个饭局，给我买一套深色的西装。 */
/* 你还未来得及反应，老板便不见人了，留下一脸懵逼的你。                                                            */
object Number1S_Number1T_Top {
  object Number1SExe {
    def Number1S_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1T_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def exe = {
      Number1S_exe
      Number1T_exe
      Number1U_exe
      Number1V_exe
    }
  }
  object Number1TExe {
    def Number1S_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1T_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def exe = {
      Number1S_exe
      Number1T_exe
      Number1U_exe
      Number1V_exe
    }
  }
  object Number1UExe {
    def Number1S_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1T_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def exe = {
      Number1S_exe
      Number1T_exe
      Number1U_exe
      Number1V_exe
    }
  }
  object Number1VExe {
    def Number1S_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1T_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      def number2Gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def exe = {
      Number1S_exe
      Number1T_exe
      Number1U_exe
      Number1V_exe
    }
  }
  def exe = {
    Number1SExe.exe
    Number1TExe.exe
    Number1UExe.exe
    Number1VExe.exe
  }
}
