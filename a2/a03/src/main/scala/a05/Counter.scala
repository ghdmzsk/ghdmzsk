package a05

trait Number4 {
  def method4(number2: Number2): Number3
}
case class Number4S(tail2: () => Number1) extends Number4 {
  def method4(number2: Number2): Number3 = tail2().method1(number2)
}
case class Number4T(tail2: () => Number1) extends Number4 {
  def method4(number2: Number2): Number3 = Number3S(tail2().method1(number2))
}

trait Number1 {
  def method1(number2: Number2): Number3
}
case class Number1S(tail: () => Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(tail())
}
case class Number1T(tail: () => Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = Number3S(tail().method1(number2))
}
case class Number1U(tail: () => Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = Number3S(number2.method2(tail()))
}
case class Number1V(tail: () => Number4) extends Number1 {
  override def method1(number2: Number2): Number3 = tail().method4(number2)
}

trait Number2 {
  def method2(number1: Number1): Number3
}
case class Number2S(tail: Number2) extends Number2 {
  override def method2(number1: Number1): Number3 = number1.method1(tail)
}
case object Number2T extends Number2 {
  override def method2(number1: Number1): Number3 = Number3T
}

trait Number3
case class Number3S(tail: Number3) extends Number3
case object Number3T               extends Number3
