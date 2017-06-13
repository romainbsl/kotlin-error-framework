package io.github.romainbsl.kerror.core.error

open class InnerError(val code: String, val message: String) : Cloneable {
    var innerError: InnerError? = null
        private set

    constructor(code: String, message: String, innerError: InnerError) : this(code, message) {
        this.innerError = innerError
    }

    infix fun inner(error: InnerError): InnerError {
        error.innerError = this
        return error
    }

    infix fun outer(error: InnerError): InnerError {
        this.innerError = error
        return this
    }

    fun invert() = invert(error = null)

    private fun invert(error: InnerError? = null): InnerError {
        val subInnerError = this.clone()
        subInnerError.innerError = error
        return innerError?.invert(subInnerError) ?: subInnerError
    }

    override public fun clone(): InnerError {
        return super.clone() as InnerError
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is InnerError) return false

        if (code != other.code) return false
        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + (innerError?.hashCode() ?: 0)
        return result
    }

    fun last(): InnerError {
        var innerLocal = this

        while (innerLocal.innerError != null) {
            innerLocal = this.innerError!!
        }

        return innerLocal
    }
}