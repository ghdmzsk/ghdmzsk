package f03.service

import e01._
import f03.slick.model.Tables._

// case class CountResult(i1: Int, i2: Int, result: Option[Int])

trait DataCollection {
  /*val S             = "s"
  val T             = "t"
  val U             = "u"
  val V             = "v"
  val W             = "w"
  val X             = "x"
  val Y             = "y"
  val Z             = "z"
  val baseName      = Vector(S, T, U, V, W, Y)
  val zeroName      = Vector(X, Z)
  val PointType     = "point"
  val ValueType     = "value"
  val ZeroType      = "zero"

  def numberGen(nType: String, count: Int, zero: => Number1): Number1 = nType match {
    case S => Fusion.number1sGen(count, zero)
    case T => Fusion.number1tGen(count, zero)
    case U => Fusion.number1uGen(count, zero)
    case V => Fusion.number1vGen(count, zero)
    case W => Fusion.number1wGen(count, zero)
    case Y => Fusion.number1yGen(count, zero)
  }
  def numberZero(nType: String): Number1 = nType match {
    case S => Fusion.number1s
    case T => Fusion.number1t
    case U => Fusion.number1u
    case V => Fusion.number1v
    case W => Fusion.number1w
    case X => Fusion.number1x
    case Y => Fusion.number1y
    case Z => Fusion.number1z
  }*/

  def allCountPlan: Vector[CountPlanRow]
  def genString(result: List[(Int, Int, Option[Int])]): String
  val UnlimitedType = "unlimited"

  /*case class SingleNumber(outerName: String, outerType: String, innerName: String, innerType: String, start: Int)

  def fromString(str: String): List[CountResult]

  def countNumber(countPlan: CountPlanRow, firstValue: Int, secoundValue: Int): CountResult
  def genSingleNumber(countPlan: SingleNumber, value: Int): Number1*/
}

class DataCollectionImpl extends DataCollection {

  /*val singleZeroRows: Vector[SingleNumber] =
    for (name <- zeroName)
      yield SingleNumber(outerName = name, outerType = UnlimitedType, innerName = name, innerType = UnlimitedType, start = 0)

  val unlimitedRows: Vector[SingleNumber] =
    for (name <- baseName)
      yield SingleNumber(outerName = name, outerType = UnlimitedType, innerName = name, innerType = UnlimitedType, start = 0)

  val otherRows: Vector[SingleNumber] = for {
    outerType <- Vector(PointType, ValueType)
    innerType <- Vector(UnlimitedType, PointType, ValueType)
    outerName <- baseName
    innerName <- baseName.filter(s => s != outerName)
  } yield {
    val start = if (outerType == ValueType && innerType == ValueType) 1 else 0
    SingleNumber(outerName = outerName, outerType = outerType, innerName = innerName, innerType = innerType, start = start)
  }

  val zeroRows: Vector[SingleNumber] = for {
    outerType <- Vector(PointType, ValueType)
    outerName <- baseName
    innerName <- zeroName
  } yield SingleNumber(outerName = outerName, outerType = outerType, innerName = innerName, innerType = ZeroType, start = 0)*/

  def countNumberDifferent: Vector[(Char, Char)] = {
    def numCol = for {
      i1 <- 'a' to 's'
      i2 <- 'a' to 's'
    } yield ((i1, i2), tagNum(i1, i2))

    def tagNum(v1: Char, v2: Char): String = {
      var str                     = ""
      var needStop                = false
      var len: Int                = 80
      var countNum: () => Number1 = NumberGen.genNumber(v1, v2, 10)
      while (len > 0 && !needStop) {
        try {
          val next = countNum()
          next match {
            case Number1S =>
              str += "s"
              needStop = true
            case Number1T =>
              str += "t"
              needStop = true
            case Number1U =>
              str += "u"
              needStop = true
            case Number1V =>
              str += "v"
              needStop = true
            case Number1W =>
              str += "w"
              needStop = true
            case Number1X =>
              str += "x"
              needStop = true
            case Number1Y(tail) =>
              countNum = tail
              str += "y"
              len -= 1
            case Number1Z(tail) =>
              countNum = tail
              str += "z"
              len -= 1
            case Number1A(tail) =>
              countNum = tail
              str += "a"
              len -= 1
            case Number1B(tail) =>
              countNum = tail
              str += "b"
              len -= 1
          }
        } catch {
          case _: Throwable =>
            str += "e"
            needStop = true
        }
      }
      str
    }

    numCol.groupBy(_._2).map(_._2.head._1).to(Vector)
  }

  override val allCountPlan: Vector[CountPlanRow] = {
    val col = ('a' to 's').to(Vector)
    for {
      f    <- col
      s    <- col
      t    <- col
      four <- col
    } yield CountPlanRow(
      id = -1,
      planInfo = f.toString + s.toString + t.toString + four.toString
    )
  }

  override def genString(result: List[(Int, Int, Option[Int])]): String =
    result.map(s => s"${s._1},${s._2},${s._3.map(_.toString).getOrElse(UnlimitedType)}").mkString("|")

  /*override def genSingleNumber(countPlan: SingleNumber, value: Int): Number1 = {
    countPlan.outerType match {
      case UnlimitedType => numberZero(countPlan.outerName)
      case PointType =>
        countPlan.innerType match {
          case UnlimitedType =>
            numberGen(countPlan.outerName, 1, numberZero(countPlan.innerName))
          case PointType =>
            lazy val outer: Number1 = numberGen(countPlan.outerName, 1, inner)
            lazy val inner: Number1 = numberGen(countPlan.innerName, 1, outer)
            outer
          case ValueType =>
            lazy val outer: Number1 = numberGen(countPlan.outerName, 1, inner)
            lazy val inner: Number1 = numberGen(countPlan.innerName, value, outer)
            outer
          case ZeroType =>
            numberGen(countPlan.outerName, 1, numberZero(countPlan.innerName))
        }
      case ValueType =>
        countPlan.innerType match {
          case UnlimitedType =>
            numberGen(countPlan.outerName, value, numberZero(countPlan.innerName))
          case PointType =>
            lazy val outer: Number1 = numberGen(countPlan.outerName, value, inner)
            lazy val inner: Number1 = numberGen(countPlan.innerName, 1, outer)
            outer
          case ValueType =>
            lazy val outer: Number1 = numberGen(countPlan.outerName, value, inner)
            lazy val inner: Number1 = numberGen(countPlan.innerName, value, outer)
            outer
          case ZeroType =>
            numberGen(countPlan.outerName, value, numberZero(countPlan.innerName))
        }
    }
  }



  override def fromString(str: String): List[CountResult] = {
    def splitToList(str: String)(c: Char) = str.split(c).to(List).map(_.trim).filterNot(_.isEmpty)
    def toSingle(str1: String): CountResult = {
      val List(i1, i2, result) = splitToList(str1)(',')
      val r1 = result match {
        case UnlimitedType => Option.empty
        case s             => s.toIntOption
      }
      CountResult(i1 = i1.toInt, i2 = i2.toInt, result = r1)
    }
    splitToList(str)('|').map(toSingle)
  }

  override def countNumber(countPlan: CountPlanRow, firstValue: Int, secoundValue: Int): CountResult = {
    val firstNumber = genSingleNumber(
      SingleNumber(
        outerName = countPlan.firstOuterName,
        outerType = countPlan.firstOuterType,
        innerName = countPlan.firstInnerName,
        innerType = countPlan.firstInnerType,
        start = countPlan.firstStart
      ),
      firstValue
    )
    val secondNumber = genSingleNumber(
      SingleNumber(
        outerName = countPlan.secondOuterName,
        outerType = countPlan.secondOuterType,
        innerName = countPlan.secondInnerName,
        innerType = countPlan.secondInnerType,
        start = countPlan.secondStart
      ),
      secoundValue
    )
    def exec = firstNumber.method1(secondNumber)
    CountResult(i1 = firstValue, i2 = secoundValue, result = Counter.countOpt(() => exec))
  }*/

}
