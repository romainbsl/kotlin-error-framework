package io.github.romainbsl.klogger.core.error

import java.util.*

open class ErrorBase(val code: String, val message: String, var innerError: InnerError? = null) {
    var details: LinkedList<ErrorBase>? = null
        private set

    fun addDetail(detail: ErrorBase?) {
        if (detail == null) return
        if (details == null) details = LinkedList<ErrorBase>()
        (details as LinkedList<ErrorBase>).add(detail)
    }

    fun setOuterError(error: InnerError) {
        innerError?.setOuterError(error)
        innerError = error
    }

    fun invertInnerError() {
        innerError = innerError?.invert()
    }
}

open class ImmutableErrorBase(val code: String, val message: String) {
    var innerError: ImmutableInnerError? = null
        private set
    var details: LinkedList<ErrorBase>? = null
        private set

    fun addDetail(detail: ErrorBase) {
        if (details == null) details = LinkedList<ErrorBase>()
        (details as LinkedList<ErrorBase>).add(detail)
    }

    infix fun inner(error: ImmutableInnerError) {
        if (this.innerError != null )
            error inner this.innerError as ImmutableInnerError
    }

    infix fun outer(error: ImmutableInnerError) = this.innerError?.outer(error)

    fun invertInnerError() {
        innerError = innerError?.invert()
    }
}

