package br.com.learning.kotlinwithspringboot.mathoperations

import br.com.learning.kotlinwithspringboot.exceptions.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.sqrt

@RestController
class MathController {
    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(
        @PathVariable(value = "numberOne") numberOne: String,
        @PathVariable(value = "numberTwo") numberTwo: String
    ): Double {
        if (!this.isNumeric(numberOne) || !this.isNumeric(numberTwo)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }
        return this.convertToDouble(numberOne) + this.convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/subtraction/{numberOne}/{numberTwo}"])
    fun subtract(
        @PathVariable(value = "numberOne") numberOne: String,
        @PathVariable(value = "numberTwo") numberTwo: String
    ): Double {
        if (!this.isNumeric(numberOne) || !this.isNumeric(numberTwo)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }
        return this.convertToDouble(numberOne) - this.convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/multiply/{numberOne}/{numberTwo}"])
    fun multiply(
        @PathVariable(value = "numberOne") numberOne: String,
        @PathVariable(value = "numberTwo") numberTwo: String
    ): Double {
        if (!this.isNumeric(numberOne) || !this.isNumeric(numberTwo)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }
        return this.convertToDouble(numberOne) * this.convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/divide/{numberOne}/{numberTwo}"])
    fun divide(
        @PathVariable(value = "numberOne") numberOne: String,
        @PathVariable(value = "numberTwo") numberTwo: String
    ): Double {
        if (!this.isNumeric(numberOne) || !this.isNumeric(numberTwo)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }
        val divisor = this.convertToDouble(numberTwo)
        if (divisor == 0.0) throw UnsupportedMathOperationException("Divisor can't be zero")
        return this.convertToDouble(numberOne) / divisor
    }

    @RequestMapping(value = ["/average/{numberOne}/{numberTwo}"])
    fun average(
        @PathVariable(value = "numberOne") numberOne: String,
        @PathVariable(value = "numberTwo") numberTwo: String
    ): Double {
        if (!this.isNumeric(numberOne) || !this.isNumeric(numberTwo)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }
        return (this.convertToDouble(numberOne) + this.convertToDouble(numberTwo)) / 2
    }

    @RequestMapping(value = ["/square/{number}"])
    fun squareRoot(
        @PathVariable(value = "number") number: String
    ): Double {
        if (!this.isNumeric(number)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }
        return sqrt(this.convertToDouble(number))
    }

    private fun isNumeric(value: String?): Boolean {
        if (value.isNullOrBlank()) return false
        val number = value.replace(",".toRegex(), ".")
        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }

    private fun convertToDouble(value: String?): Double {
        if (value.isNullOrBlank()) return 0.0
        val number = value.replace(",".toRegex(), ".")
        return if (isNumeric(number)) number.toDouble() else 0.0
    }
}