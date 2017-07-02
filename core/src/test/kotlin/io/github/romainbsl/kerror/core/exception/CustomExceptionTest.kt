package io.github.romainbsl.kerror.core.exception

import io.github.romainbsl.kerror.core.error.ErrorBase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * Created by Romain on 02/07/2017.
 */
class CustomExceptionTest {
    @Test fun customPeristenceExceptionCtor() {
        val exception = CustomPersistenceException()
        assertThat(exception)
                .hasFieldOrProperty("rootError")
                .hasNoSuppressedExceptions()
                .hasNoCause()
        assertThat(exception.rootError)
                .isNotNull()
                .isEqualTo(ErrorBase("PERSISTENCE_EXCEPTION", "There has been an issue in the persistence layer!"))
    }
    @Test fun customServiceExceptionCtor() {
        val exception = CustomServiceException()
        assertThat(exception)
                .hasFieldOrProperty("rootError")
                .hasNoSuppressedExceptions()
                .hasNoCause()
        assertThat(exception.rootError)
                .isNotNull()
                .isEqualTo(ErrorBase("SERVICE_EXCEPTION", "There has been an issue in the service layer!"))
    }
    @Test fun customValidationExceptionCtor() {
        val exception = CustomValidationException()
        assertThat(exception)
                .hasFieldOrProperty("rootError")
                .hasNoSuppressedExceptions()
                .hasNoCause()
        assertThat(exception.rootError)
                .isNotNull()
                .isEqualTo(ErrorBase("VALIDATION_EXCEPTION", "The input data are invalid!"))
    }
}