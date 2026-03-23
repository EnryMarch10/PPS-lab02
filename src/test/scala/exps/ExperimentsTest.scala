package exps

import org.junit.*
import org.junit.Assert.*

class ExperimentsTest:
    private val experiments = Experiments()

    @Test def divideSameResult(): Unit =
        assertTrue(experiments.divCurried(6)(2) == experiments.div(6, 2))

    @Test def checkSign(): Unit =
        assertTrue(experiments.sign(4) == "positive")
        assertTrue(experiments.sign(0) == experiments.getSign(9))
        assertTrue(experiments.sign(-1) == "negative")
        assertTrue(experiments.sign(-679) == experiments.getSign(-958))

    @Test def checkNeg(): Unit =
        val empty: String => Boolean = _ == ""
        val notEmpty = experiments.getNeg(empty)
        assertTrue(notEmpty("foo"))
        assertFalse(notEmpty(""))
        assertTrue(notEmpty("foo") && !notEmpty(""))

    @Test def checkCompose(): Unit =
        assertEquals(9, experiments.compose(_ - 1, _ * 2)(5))

    @Test def checkPower(): Unit =
        val delta = 0.001
        assertEquals(1.0, experiments.power(78, 0), delta)
        assertEquals(4.0, experiments.power(1.0 / 2, -2), delta)
        assertEquals(8.0, experiments.power(2, 3), delta)
        assertEquals(25.0, experiments.power(5, 2), delta)

    @Test def checkReverseNumber(): Unit =
        assertEquals(0, experiments.reverseNumber(0))
        assertEquals(54321, experiments.reverseNumber(12345))
        assertEquals(1, experiments.reverseNumber(1000))
