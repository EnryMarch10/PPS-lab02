package exps

import scala.annotation.tailrec

object Experiments:

    //// PART 1 - Warm up

    // standard function with no currying
    def div(x: Double, y: Double): Double = x * y

    // function with currying
    // divCurried has actually type: Double => (Double => Double)
    def divCurried(x: Double)(y: Double): Double = x * y

    //// PART 2 - Functions

/// FUNCTIONS - match cases

    val sign: Int => String = _ match
        case n if n >= 0 => "positive"
        case _ => "negative"

    def getSign(num: Int): String =
        num match
            case n if n >= 0 => "positive" // meglio evitare di usare la variabile num, per problemi di shadowing...
            case _ => "negative"

/// FUNCTIONS - neg

//    val neg: (String => Boolean) => (String => Boolean) = predicate => x => !predicate(x)
    val neg: (String => Boolean) => String => Boolean = predicate => !predicate(_)

//    def neg(predicate: String => Boolean): String => Boolean =
//        x => !predicate(x)
    def getNeg(predicate: String => Boolean): String => Boolean =
        !predicate(_)

/// FUNCTIONS - currying

    val p1: Int => Int => Int => Boolean = x => y => z => x <= y && y == z
//            x match
//                case n1 if n1 <= y => y match
//                    case n2 if n2 == z => true
//                    case _ => false
//                case _ => false

    val p2: (Int, Int, Int) => Boolean = (x, y, z) => x <= y && y == z

    def p3(x: Int)(y: Int)(z: Int): Boolean = x <= y && y == z

    def p4(x: Int, y: Int, z: Int): Boolean = x <= y && y == z

    def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))

    //// PART 3 - Recursion

    def power(base: Double, exponent: Int): Double =
        @tailrec
        def posPower(base: Double, exponent: Int, acc: Double = 1.0): Double = exponent match
            case e if e > 1 => posPower(base, e - 1, acc * base)
            case 1 => acc * base
        @tailrec
        def negPower(base: Double, exponent: Int, acc: Double = 1.0): Double = exponent match
            case e if e < 0 => negPower(base, e + 1, acc * (1 / base))
            case 0 => acc
        exponent match
            case e if e > 1 => posPower(base, e)
            case e if e < 0 => negPower(base, e)
            case 1 => base
            case 0 => 1

    def reverseNumber(n: Int): Int =
        @tailrec
        def tailReverseNumber(x: Int, acc: Int = 0): Int = x match
            case num if num > -10 && num < 10  => acc * 10 + num
            case num => tailReverseNumber(num / 10, acc * 10 + (num - num / 10 * 10))
        tailReverseNumber(n)

    enum Expr:
        case Literal(value: Int)
        case Add(left: Expr, right: Expr)
        case Multiply(left: Expr, right: Expr)

    object Expr:

        def evaluate(expr: Expr): Int = expr match
            case Multiply(l, r) => evaluate(l) * evaluate(r)
            case Add(l, r) => evaluate(l) + evaluate(r)
            case Literal(n) => n

        def show(expr: Expr): String = expr match
            case Multiply(l, r) => "(" + show(l) + " * " + show(r) + ")"
            case Add(l, r) => "(" + show(l) + " + " + show(r) + ")"
            case Literal(n) => s"$n"

@main def tryNeg(): Any =
    import Experiments.*
    val empty: String => Boolean = _ == "" // predicate on strings
    val notEmpty = getNeg(empty) // which type of notEmpty?
    println(notEmpty("foo")) // true
    println(notEmpty("")) // false
    println(notEmpty("foo") && !notEmpty("")) // true.. a comprehensive test
