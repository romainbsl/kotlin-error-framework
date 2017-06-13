package io.github.romainbsl.kerror.core.error

import java.util.*
import kotlin.reflect.KFunction2

open class ErrorBase(val code: String, val message: String) {
    var innerError: InnerError? = null
        private set
    var details = LinkedList<ErrorBase>()
        private set

    infix fun inner(error: InnerError) = invokeInnerOuter(error, InnerError::inner)
    infix fun outer(error: InnerError) = invokeInnerOuter(error, InnerError::outer)

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

    fun invertInnerError() {
        innerError = innerError?.invert()
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