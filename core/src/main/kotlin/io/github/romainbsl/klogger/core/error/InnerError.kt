package io.github.romainbsl.klogger.core.error

data class InnerError(val code: String, val message: String, var innerError: InnerError? = null) {
    fun setOuterError(error: InnerError) {
        if (!error.equals(this.innerError))
            error.innerError = this
    }

    fun invert() = invert(inError = null)

    private fun invert(inError: InnerError? = null): InnerError {
        val thisError = innerError?.copy()
        innerError = inError
        return thisError?.invert(this) ?: this
    }
}