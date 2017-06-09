package io.github.romainbsl.kerror.core.Validation

import io.github.romainbsl.kerror.core.error.ErrorBase
import io.github.romainbsl.kerror.core.error.ErrorEnum
import io.github.romainbsl.kerror.core.exception.AbstractException

class ValidationException() : AbstractException(ValidationRootError()) {
    internal class ValidationRootError : ErrorBase(ErrorEnum.INPUT_VALIDATION_ERROR.code, ErrorEnum.INPUT_VALIDATION_ERROR.message)
}