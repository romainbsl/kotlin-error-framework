package io.github.romainbsl.klogger.core.error

class InnerError(val code: String, val message: String, var innerError: InnerError? = null) {
    fun setOuterError(error: InnerError) {
        if (!error.equals(this.innerError))
            error.innerError = this
    }

    fun invert() = invert(error = null)

    private fun invert(error: InnerError? = null): InnerError {
        var firstInnerError: InnerError? = null
        if (innerError != null)
            firstInnerError = (innerError as InnerError).invert(this)

        innerError = error
        if (firstInnerError != null) {
            return firstInnerError
        } else {
            return this
        }
    }
}