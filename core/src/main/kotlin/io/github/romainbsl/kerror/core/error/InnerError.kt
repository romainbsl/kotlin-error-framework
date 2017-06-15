package io.github.romainbsl.kerror.core.error

open class InnerError(val code: String, val message: String) : Cloneable {
    //region Prop
    var innerError: InnerError? = null
        private set
    //endregion

    //region Ctor
    constructor(code: String, message: String, innerError: InnerError) : this(code, message) {
        this.innerError = innerError
    }
    //endregion

    /**
     * Set 'this' InnerError into 'error' InnerError
     * inner1 inner inner2
     * becomes
     * inner2 -> inner1
     */
    infix fun inner(error: InnerError): InnerError {
        error.innerError = this
        return error
    }

    /**
     * Set 'this' InnerError on a higher level than 'error' InnerError
     * inner1 inner inner2
     * becomes
     * inner1 -> inner2
     */
    infix fun outer(error: InnerError): InnerError {
        this.innerError = error
        return this
    }

    /**
     * Invert all the InnerError nodes
     * inner1 -> inner2 -> inner3
     * becomes
     * inner3 -> inner2 -> inner1
     */
    fun invert(error: InnerError? = null): InnerError {
        val subInnerError = this.clone()
        subInnerError.innerError = error
        return innerError?.invert(subInnerError) ?: subInnerError
    }

    /**
     * Get the last element of the InnerError tree
     * Recursively
     */
    fun last(): InnerError {
        var innerLocal = this

        while (innerLocal.innerError != null) {
            innerLocal = this.innerError!!
        }

        return innerLocal
    }

    //region Overrides
    override public fun clone(): InnerError {
        return super.clone() as InnerError
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is InnerError) return false

        if (code != other.code) return false
        if (message != other.message) return false
        if (innerError != other.innerError) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + (innerError?.hashCode() ?: 0)
        return result
    }


    //endregion
}