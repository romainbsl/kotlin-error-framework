package io.github.romainbsl.kerror.core.error

import org.junit.Test

class ErrorBaseTest {
    @Test fun getCode() {
        assert(errorBase_1.code == "ErrorBaseCode_1")
        assert(errorBase_2.code == "ErrorBaseCode_2")
        assert(errorBase_3.code == "ErrorBaseCode_3")
    }

    @Test fun getMessage() {
        assert(errorBase_1.message == "ErrorBaseMsg_1")
        assert(errorBase_2.message == "ErrorBaseMsg_2")
        assert(errorBase_3.message == "ErrorBaseMsg_3")
    }

    @Test fun getInnerError() {
        assert(errorBase_1.innerError == null)
        assert(errorBase_2.innerError == null)
        assert(errorBase_3.innerError == null)

        assert(errorBase_21.innerError == innerError_1)
        assert(errorBase_21.innerError?.innerError == innerError_2)
        assert(errorBase_21.innerError?.innerError?.innerError == innerError_3)

        assert(errorBase_22.innerError == innerError_3)
        assert(errorBase_22.innerError?.innerError == innerError_2)
        assert(errorBase_22.innerError?.innerError?.innerError == innerError_1)
    }

    @Test fun getDetails() {
        assert(errorBase_31.details.isEmpty())
        assert(errorBase_32.details.isEmpty())

        assert(errorBase_35.details.count() == 2)
        assert(errorBase_35.details.get(0) == errorBase_31)
        assert(errorBase_35.details.get(1) == errorBase_32)

        assert(errorBase_36.details.count() == 1)
        assert(errorBase_36.details.get(0) == errorBase_33)
        assert(errorBase_36.details.get(0).details.count() == 1)
        assert(errorBase_36.details.get(0).details.get(0) == errorBase_34)
    }

    @Test fun addDetail() {
        assert(errorBase_41.details.isEmpty())

        errorBase_41.details.add(errorBase_42)

        assert(!errorBase_41.details.isEmpty())
        assert(errorBase_41.details.count() == 1)
        assert(errorBase_41.details.get(0) == errorBase_42)

        errorBase_41.details.add(errorBase_43)

        assert(!errorBase_41.details.isEmpty())
        assert(errorBase_41.details.count() == 2)
        assert(errorBase_41.details.get(0) == errorBase_42)
        assert(errorBase_41.details.get(1) == errorBase_43)

        assert(errorBase_43.details.isEmpty())

        errorBase_43.details.add(errorBase_44)

        assert(!errorBase_43.details.isEmpty())
        assert(errorBase_43.details.count() == 1)
        assert(errorBase_43.details.get(0) == errorBase_44)
    }

    @Test fun inner() {

    }

    @Test fun outer() {

    }

    @Test fun invertInnerError() {

    }


}