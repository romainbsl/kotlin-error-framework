package io.github.romainbsl.kerror.core.exception

import io.github.romainbsl.kerror.core.error.ErrorBase

abstract class AbstractException(val error: ErrorBase) : Exception()
