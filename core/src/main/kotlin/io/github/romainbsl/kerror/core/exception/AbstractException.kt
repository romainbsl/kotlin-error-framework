package io.github.romainbsl.kerror.core.exception

import io.github.romainbsl.kerror.core.error.ErrorBase

abstract class AbstractException : Exception {
    val rootError: ErrorBase

    constructor(rootError: ErrorBase) : super() {
        this.rootError = rootError
    }
}
