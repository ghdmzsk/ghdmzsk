package e01_02

trait Number[+A] {
  def execute[T <: TypeContext](contexts: Context[T, A])(t: T#toDataType): T#Result
}

object Number {
  def empty[S]: Number[S] = {
    def n: Number[S] = NumberT(() => n)
    n
  }
}

case class NumberS[+A](tail: () => Number[A], head: A) extends Number[A] {
  override def execute[T <: TypeContext](context: Context[T, A])(t: T#toDataType): T#Result = context.bindS(t, tail, head)
}
case class NumberT[+A](tail: () => Number[A]) extends Number[A] {
  override def execute[T <: TypeContext](context: Context[T, A])(t: T#toDataType): T#Result = context.bindT(t, tail)
}

trait Context[T <: TypeContext, -A] {
  def bindS(t: T#toDataType, current: () => Number[A], head: A): T#Result
  def bindT(t: T#toDataType, current: () => Number[A]): T#Result
}

trait TypeContext {
  type toDataType
  type Result
}
