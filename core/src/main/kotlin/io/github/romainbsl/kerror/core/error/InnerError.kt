package io.github.romainbsl.kerror.core.error

data class InnerError(val code: String, val message: String) {
    var innerError: InnerError? = null
        private set

    constructor(code: String, message: String, innerError: InnerError) : this(code, message) {
        this.innerError = innerError
    }

    infix fun inner(error: InnerError) {
        error.innerError = this
    }

    infix fun outer(error: InnerError) {
        this.innerError = error
    }

    fun invert() = invert(error = null)

    private fun invert(error: InnerError? = null): InnerError {
        val subInnerError = this.copy(code, message)
        subInnerError.innerError = error
        return innerError?.invert(subInnerError) ?: subInnerError
    }
}

/*
data class InnerError(val code: String, val message: String, var innerError: InnerError? = null) {
    fun setOuterError(error: InnerError) {
        if (!error.equals(this.innerError))
            error.innerError = this
    }

    fun invert() = invert(error = null)

    private fun invert(error: InnerError? = null): InnerError {
        val firstInnerError = innerError?.invert(this)
        innerError = error
        return firstInnerError ?: this
    }
}
*/
