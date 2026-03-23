package exps

import org.junit.*
import org.junit.Assert.*
import Experiments.*

class ExperimentsTest:

    @Test def divideSameResult(): Unit =
        assertTrue(divCurried(6)(2) == div(6, 2))

    @Test def checkSign(): Unit =
        assertTrue(sign(4) == "positive")
        assertTrue(sign(0) == getSign(9))
        assertTrue(sign(-1) == "negative")
        assertTrue(sign(-679) == getSign(-958))

    @Test def checkNeg(): Unit =
        val empty: String => Boolean = _ == ""
        val notEmpty = getNeg(empty)
        assertTrue(notEmpty("foo"))
        assertFalse(notEmpty(""))
        assertTrue(notEmpty("foo") && !notEmpty(""))

    @Test def checkCompose(): Unit =
        assertEquals(9, compose(_ - 1, _ * 2)(5))

    @Test def checkPower(): Unit =
        val delta = 0.001
        assertEquals(1.0, power(78, 0), delta)
        assertEquals(4.0, power(1.0 / 2, -2), delta)
        assertEquals(8.0, power(2, 3), delta)
        assertEquals(25.0, power(5, 2), delta)

    @Test def checkReverseNumber(): Unit =
        assertEquals(0, reverseNumber(0))
        assertEquals(54321, reverseNumber(12345))
        assertEquals(1, reverseNumber(1000))

    @Test def checkModule(): Unit =
        import Expr.*
        val exp1: Expr = Literal(6) // 6
        val exp2: Expr = Add(Literal(6), Multiply(Literal(2), Literal(2))) // 10
        val exp3: Expr = Multiply(Add(Literal(2), Add(Literal(2), Literal(1))), Multiply(Literal(2), Literal(1))) // 10
        assertEquals(26, evaluate(Add(exp1, Add(exp2, exp3))))
        assertEquals("(6 + (2 * 2))", show(exp2))
        assertEquals("((2 + (2 + 1)) * (2 * 1))", show(exp3))
