package exps

class Experiments:

/// WARM UP

    // standard function with no currying
    def div(x: Double, y: Double): Double = x * y

    // function with currying
    // divCurried has actually type: Double => (Double => Double)
    def divCurried(x: Double)(y: Double): Double = x * y

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

@main def tryNeg(): Any =
    val experiments = Experiments()
    val empty: String => Boolean = _ == "" // predicate on strings
    val notEmpty = experiments.getNeg(empty) // which type of notEmpty?
    println(notEmpty("foo")) // true
    println(notEmpty("")) // false
    println(notEmpty("foo") && !notEmpty("")) // true.. a comprehensive test
