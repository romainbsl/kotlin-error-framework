package io.github.romainbsl.kerror.core.error

import org.junit.Test

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
        assert(errorBase_1.clone().innerError == innerError_1)
        assert(errorBase_1.clone().innerError?.innerError == innerError_2)
        assert(errorBase_1.clone().innerError?.innerError?.innerError == innerError_3)

        assert(errorBase_2.clone().innerError == innerError_3)
        assert(errorBase_2.clone().innerError?.innerError == innerError_2)
        assert(errorBase_2.clone().innerError?.innerError?.innerError == innerError_1)

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