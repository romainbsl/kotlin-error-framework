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
        return super.clone() as ErrorBase
    }
}