package io.github.romainbsl.klogger.core.error

import java.util.*

open class ErrorBase (val code: String, val message: String, var innerError: InnerError? = null) {
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

