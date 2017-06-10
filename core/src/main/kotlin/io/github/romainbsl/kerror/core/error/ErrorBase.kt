package io.github.romainbsl.kerror.core.error

import java.util.*
import kotlin.reflect.KFunction2

open class ErrorBase(val code: String, val message: String) : Cloneable {
    var innerError: InnerError? = null
        private set
    var details = LinkedList<ErrorBase>()
        private set

    infix fun inner(error: InnerError) = testInnerOuter(error, InnerError::inner)
    infix fun outer(error: InnerError) = testInnerOuter(error, InnerError::outer)

    private fun testInnerOuter(error: InnerError,
                               method: KFunction2<InnerError,
                                       @ParameterName(name = "error") InnerError, InnerError>) {
        val localInnerError = innerError

        innerError = when {
            localInnerError == null -> error
            method == InnerError::inner -> method.invoke(error, localInnerError)
            method == InnerError::outer -> method.invoke(localInnerError, error)
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