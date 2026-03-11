# Lab 02 - Functional Programming

Third laboratory of [Programming and Development Paradigms - a.y. 2025-2026](https://www.unibo.it/en/teaching/course-unit-catalogue/course-unit/2025/526526) ([Computer Science and Engineering](https://corsi.unibo.it/2cycle/ComputerScienceEngineering)).

## Author

[@EnryMarch10](https://github.com/EnryMarch10)

## Details

First laboratory about functional programming using **Scala**.

### Exercises

#### Part 1 - Warm-Up

1. Setup & Hello Scala:
   - Implement a simple `"Hello, Scala"` inside an object extending `App`.
2. REPL Exploration (10 minutes):
   - Use `scala` or `sbt` console to explore Scala/FP examples from the `u02` package.
   - Try variations, explore autonomously, and ask in case of doubts.
   - Copy and past code as in any other terminal.
   - Run `sbt console` on the root of the project (or use the REPL directly).
   - Explore the class definitions in `u02` by experimenting with code examples.
   - For example, with Currying:
     - Copy `mult` and `multCurried` from the lecture code.
     - Try them with different parameters: `mult(3,4)`, `multCurried(3)(4)`.
     - Create variations like changing the operation or parameter types.
     - Try partial application: `val multiplyBy3 = multCurried(3)`.
   - Document your experiments in a file:
     - Try to implement similar functions like `divide` and `divideCurried`.
     - Test them with different inputs and edge cases.
     - Record your observations and code examples in your file.
     - This will be shared with us as a part of the lab.

#### Part 2 - Functions

1. First‑Class & Higher‑Order Functions:
   - Get familiar with first-class and higher order functions as well as with the different styles for expressing
   functions.
   - Using match-cases, implement the following function from `Int` to `String`:
     $$
     \text{positive}(x) =
     \begin{cases}
     \text{"positive"} & \text{if } x \ge 0 \\
     \text{"negative"} & \text{if } x < 0
     \end{cases}
     $$
   in both of the following styles: (i) val assigned to function literal (lambda) and (ii) method syntax.
   - Implement a `neg` function that accepts a **predicate** on strings (i.e., a function from strings to Booleans) and
     returns another predicate on strings, namely, one that does the exact opposite; write the type first, and then
     define the function both as a val lambda and with method syntax.
     ```
     val empty: String => Boolean = _ == "" // predicate on strings
     val notEmpty = neg(empty) // which type of notEmpty?
     notEmpty("foo") // true
     notEmpty("") // false
     notEmpty("foo") && !notEmpty("") // true.. a comprehensive test
     ```
2. Currying:
   - Implement a predicate that checks whether its arguments $x, y , z$ respect the relation $x ≤ y = z$, in 4 variants
   (curried/non-curried $\times$ val/def):
     - `val p1: <CurriedFunType> = ...`
     - `val p2: <NonCurriedFunType> = ...`
     - `def p3(...)(...)(...): ... = ...`
     - `def p4(...): ... = ...`
     - Notice: function types and function literals are syntactically similar.
3. Function Composition $(f \cdot g )(x) = f(g(x))$:
   - Signature: `compose(f: Int => Int, g: Int => Int): Int => Int`.
   - Example: `compose(_ - 1, _ * 2)(5) == 9`.

#### Part 3 - Recursion

1. Power Function:
   - Signature: `power(base: Double, exponent: Int): Double`.
   - Example: `(power(2, 3), power(5, 2)) // (8.0, 25.0)`.
   - Hint: $base^{exponent} = base \times base^{exponent−1}$ and $base^0 = 1$.
   - Note: The function should just work for positive exponents.
   - Try to implement the same function using tail recursion.
2. Reverse Number Using Recursion:
   - Signature: `reverseNumber(n: Int): Int`.
   - Example: `reverseNumber(12345) // 54321`.
   - Hint: Use tail recursion with an accumulator to iteratively build the reversed number. For instance, you could
   define a helper function that takes the remaining part of the number and the current reversed number as
   parameters.
   - Hint: o extract individual digits from a number, remember:
     - $n / 10$ gives the number without the last digit (integer division).
     - $n % 10$ gives just the last digit.
     - Example: for $123$, $123 / 10 = 12$ and $123 % 10 = 3$.

#### Part 4 - Sum Types, Product Types, Modules

- Define a sum type Expr to represent arithmetic expressions:
  - Include the following variants:
    - `Literal`: a product type representing a numeric constant.
    - `Add`: a product type representing the addition of two sub-expressions.
    - `Multiply`: a product type representing the multiplication of two sub-expressions.
  - Define a module that provides operations on expressions:
    - `evaluate(expr: Expr): Int` - Recursively compute the numerical result of the expression.
    - `show(expr: Expr): String` - Recursively generate a string representation of the expression.
    - Hint: For string concatenation in the show function, you can use string interpolation or the + operator, e.g.,
    `"(" + leftExprString + " + " + rightExprString + ")"` for formatting expressions.
  - Consider using a TDD (Test-Driven Development) process to design and test your functions:
    - Write tests using JUnit as shown in `src/main/test/task5`.

#### Part 5 - Functional Combinators

- Look at tasks5.Optionals:
  - This follows the concept of Java Optional but with an ADT approach and just for one type, therefore describing
  OptionalInt with two  cases:
  - `Just(value: Int)`: the value is present
  - `Empty()` : the value is not present
  - Look at the implementation and the tests
  - Implement mapInt: a function that transforms the value (if present):
    - `mapInt(Just(5))(_ + 1) // Just(6)`
    - `mapInt(Empty())(_ + 1) // Empty`
  - Filter: a function that keeps the value (if present, otherwise the output is Empty) only if it satisfies the given
  predicate.  
  `filter(Just(5))(_ > 2) // Just(5)`  
  `filter(Just(5))(_ > 8) // Empty`  
  `filter(Empty())(_ > 2) // Empty`  
  The signature can be guessed by the examples.

## License

[MIT](https://choosealicense.com/licenses/mit/)
