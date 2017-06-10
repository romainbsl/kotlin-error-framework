package io.github.romainbsl.kerror.core.error

import org.junit.Test

/**
 * Created by Romain on 09/06/2017.
 */
class ErrorBaseTest {
    @Test fun getCode() {
        assert(errorBase_1.clone().code == "ErrorBaseCode_1")
        assert(errorBase_2.clone().code == "ErrorBaseCode_2")
        assert(errorBase_3.clone().code == "ErrorBaseCode_3")
        assert(errorBase_4.clone().code == "ErrorBaseCode_4")
    }

    @Test fun getMessage() {
        assert(errorBase_1.clone().message == "ErrorBaseMsg_1")
        assert(errorBase_2.clone().message == "ErrorBaseMsg_2")
        assert(errorBase_3.clone().message == "ErrorBaseMsg_3")
        assert(errorBase_4.clone().message == "ErrorBaseMsg_4")
    }

    @Test fun getInnerError() {
        assert(
                errorBase_1.clone().innerError?.code == innerError_1.code &&
                        errorBase_1.clone().innerError?.message == innerError_1.message
        )
        assert(
                errorBase_1.clone().innerError?.innerError?.code == innerError_2.code &&
                        errorBase_1.clone().innerError?.innerError?.message == innerError_2.message
        )
        assert(
                errorBase_1.clone().innerError?.innerError?.innerError?.code == innerError_3.code &&
                        errorBase_1.clone().innerError?.innerError?.innerError?.message == innerError_3.message
        )

        assert(
                errorBase_2.clone().innerError?.code == innerError_3.code &&
                        errorBase_2.clone().innerError?.message == innerError_3.message
        )
        assert(
                errorBase_2.clone().innerError?.innerError?.code == innerError_2.code &&
                        errorBase_2.clone().innerError?.innerError?.message == innerError_2.message
        )
        assert(
                errorBase_2.clone().innerError?.innerError?.innerError?.code == innerError_1.code &&
                        errorBase_2.clone().innerError?.innerError?.innerError?.message == innerError_1.message
        )

        assert(errorBase_3.clone().innerError == null)
        assert(errorBase_4.clone().innerError == null)
    }

    @Test fun getDetails() {
    }

    @Test fun addDetail() {
    }

    @Test fun inner() {
    }

    @Test fun outer() {
    }

    @Test fun invertInnerError() {
    }


}