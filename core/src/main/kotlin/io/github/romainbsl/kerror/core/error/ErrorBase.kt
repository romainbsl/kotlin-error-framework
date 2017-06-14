package io.github.romainbsl.kerror.core.error

import java.util.*
import kotlin.reflect.KFunction2

open class ErrorBase(val code: String, val message: String) {
    //region Prop
    var innerError: InnerError? = null
        private set
    var details = LinkedList<ErrorBase>()
        private set
    //endregion

    //region InnerError methods
    /**
     * Invoke InnerError.inner to add the error parameter
     * as last element of the tree
     */
    infix fun inner(error: InnerError) = invokeInnerOuter(error, InnerError::inner)
    /**
     * Invoke InnerError.outer to encapsulate
     * existing InnerErrors in the error parameter
     */
    infix fun outer(error: InnerError) = invokeInnerOuter(error, InnerError::outer)

    /**
     * Invoke the proper InnerError function
     * to set the error parameter as 'inner' or 'outer'
     * e.g. class InnerError
     */
    private fun invokeInnerOuter(error: InnerError,
                                 method: KFunction2<InnerError, InnerError, InnerError>) {
        val localInnerError = innerError

        when {
            localInnerError == null -> innerError = error
            method == InnerError::inner -> method.invoke(error, localInnerError.last())
            method == InnerError::outer -> innerError = method.invoke(error, localInnerError)
            else -> throw IllegalArgumentException()
        }
    }

    /**
     * Facade on InnerError.invert
     */
    fun invertInnerError() {
        innerError = innerError?.invert()
    }
    //endregion

    //region Overrides
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ErrorBase) return false

        if (code != other.code) return false
        if (message != other.message) return false
        if (innerError != other.innerError) return false
        if (details != other.details) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + (innerError?.hashCode() ?: 0)
        result = 31 * result + details.hashCode()
        return result
    }
    //endregion


}