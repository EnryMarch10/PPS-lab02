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
        assertEquals(experiments.compose(_ - 1, _ * 2)(5), 9)
