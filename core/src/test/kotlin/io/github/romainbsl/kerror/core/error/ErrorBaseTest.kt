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
    @Test fun errorHashCode() {
        assert(errorBase_1.hashCode() == 1043150723)
        assert(errorBase_2.hashCode() == 1043181475)
        assert(errorBase_3.hashCode() == 1043212227)
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
        assert(errorBase_51.innerError == null)

        errorBase_51.inner(innerError_1.clone())
        assert(errorBase_51.innerError == innerError_1)

        errorBase_51.inner(innerError_2.clone())
        assert(errorBase_51.innerError == innerError_1)
        assert(errorBase_51.innerError?.innerError == innerError_2)

        errorBase_51.inner(innerError_3.clone())
        assert(errorBase_51.innerError == innerError_1)
        assert(errorBase_51.innerError?.innerError == innerError_2)
        assert(errorBase_51.innerError?.innerError?.innerError == innerError_3)
    }

    @Test fun outer() {
        assert(errorBase_61.innerError == null)

        errorBase_61.outer(innerError_1.clone())
        assert(errorBase_61.innerError == innerError_1)
        errorBase_61.outer(innerError_2.clone())

        assert(errorBase_61.innerError == innerError_2)
        assert(errorBase_61.innerError?.innerError == innerError_1)
    }

    @Test fun invertInneredInnerError() {
        assert(errorBase_71.innerError == innerError_3)
        assert(errorBase_71.innerError?.innerError == innerError_2)
        assert(errorBase_71.innerError?.innerError?.innerError == innerError_1)

        errorBase_71.invertInnerError()

        assert(errorBase_71.innerError == innerError_1)
        assert(errorBase_71.innerError?.innerError == innerError_2)
        assert(errorBase_71.innerError?.innerError?.innerError == innerError_3)
    }

    @Test fun invertOuteredInnerError() {
        assert(errorBase_72.innerError == innerError_1)
        assert(errorBase_72.innerError?.innerError == innerError_2)
        assert(errorBase_72.innerError?.innerError?.innerError == innerError_3)

        errorBase_72.invertInnerError()

        assert(errorBase_72.innerError == innerError_3)
        assert(errorBase_72.innerError?.innerError == innerError_2)
        assert(errorBase_72.innerError?.innerError?.innerError == innerError_1)
    }
}