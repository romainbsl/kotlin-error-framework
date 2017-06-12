package io.github.romainbsl.kerror.core.error

import org.junit.Test

class InnerErrorTest {

    @Test fun getCode() {
        assert(innerError_1.clone().code == "InnerCode_1")
        assert(innerError_2.clone().code == "InnerCode_2")
        assert(innerError_3.clone().code == "InnerCode_3")
    }

    @Test fun getMessage() {
        assert(innerError_1.clone().message == "InnerMsg_1")
        assert(innerError_2.clone().message == "InnerMsg_2")
        assert(innerError_3.clone().message == "InnerMsg_3")
    }

    @Test fun getInnerError() {
        val innerCopy =
                innerError_1.clone() outer innerError_2.clone()

        assert(innerCopy == innerError_1)
        assert(innerCopy.innerError == innerError_2)
    }

    @Test fun inner() {
        val innerCopy =
                innerError_1.clone()
                        .inner(innerError_2.clone())
                        .inner(innerError_3.clone())

        assert(innerCopy == innerError_3)
        assert(innerCopy.innerError == innerError_2)
        assert(innerCopy.innerError?.innerError == innerError_1)
    }

    @Test fun outer() {
        val innerCopy =
                innerError_1.clone()
                        .outer(innerError_2.clone()
                                .outer(innerError_3.clone()))

        assert(innerCopy == innerError_1)
        assert(innerCopy.innerError == innerError_2)
        assert(innerCopy.innerError?.innerError == innerError_3)
    }

    @Test fun invertOuter() {
        val innerCopy =
                innerError_1.clone()
                        .outer(innerError_2.clone()
                                .outer(innerError_3.clone()))

        assert(innerCopy == innerError_1)
        assert(innerCopy.innerError == innerError_2)
        assert(innerCopy.innerError?.innerError == innerError_3)

        val invertInnerCopy = innerCopy.invert()

        assert(invertInnerCopy == innerError_3)
        assert(invertInnerCopy.innerError == innerError_2)
        assert(invertInnerCopy.innerError?.innerError == innerError_1)
    }

    @Test fun invertInner() {
        val innerCopy =
                innerError_1.clone()
                        .inner(innerError_2.clone())
                        .inner(innerError_3.clone())

        assert(innerCopy == innerError_3)
        assert(innerCopy.innerError == innerError_2)
        assert(innerCopy.innerError?.innerError == innerError_1)

        val invertInnerCopy = innerCopy.invert()

        assert(invertInnerCopy == innerError_1)
        assert(invertInnerCopy.innerError == innerError_2)
        assert(invertInnerCopy.innerError?.innerError == innerError_3)
    }
}