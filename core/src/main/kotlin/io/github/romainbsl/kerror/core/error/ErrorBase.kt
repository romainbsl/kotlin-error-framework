package io.github.romainbsl.kerror.core.error

import java.util.*
import kotlin.reflect.KFunction2

open class ErrorBase(val code: String, val message: String) : Cloneable {
    var innerError: InnerError? = null
        private set
    var details = LinkedList<ErrorBase>()
        private set

    infix fun inner(error: InnerError) = invokeInnerOuter(error, InnerError::inner)
    infix fun outer(error: InnerError) = invokeInnerOuter(error, InnerError::outer)

    private fun invokeInnerOuter(error: InnerError,
                                 method: KFunction2<InnerError,
                                         @ParameterName(name = "error") InnerError, InnerError>) {
        val localInnerError = innerError

        innerError = when {
            localInnerError == null -> error
            method == InnerError::inner -> method.invoke(localInnerError, error)
            method == InnerError::outer -> method.invoke(error, localInnerError)
            else -> throw IllegalArgumentException()
        }
    }

    fun invertInnerError() {
        innerError = innerError?.invert()
    }

    override public fun clone(): ErrorBase {
        val newError = super.clone() as ErrorBase
        // deep clone on details
        // list's items aren't clone by default
        newError.details.map { it.clone() }.toCollection(details)
        return newError
    }

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


}