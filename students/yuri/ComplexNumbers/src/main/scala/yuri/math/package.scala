package yuri

package object math {
  implicit def double2Complex(d: Double): ComplexNumber[Double] = ComplexNumber(d, 0.0)

  implicit def int2Complex(i: Int): ComplexNumber[Double] = ComplexNumber(i, 0.0)
}
