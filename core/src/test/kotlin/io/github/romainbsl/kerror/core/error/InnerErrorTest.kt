package io.github.romainbsl.kerror.core.error

import org.junit.Test

class InnerErrorTest {

    @Test fun getCode() {
        assert(innerError_1.copy().code == "InnerCode_1")
        assert(innerError_2.copy().code == "InnerCode_2")
        assert(innerError_3.copy().code == "InnerCode_3")
    }

    @Test fun getMessage() {
        assert(innerError_1.copy().message == "InnerMsg_1")
        assert(innerError_2.copy().message == "InnerMsg_2")
        assert(innerError_3.copy().message == "InnerMsg_3")
    }

    @Test fun getInnerError() {
        val innerCopy =
                innerError_1.copy() outer innerError_2.copy()

        assert(innerCopy == innerError_1)
        assert(innerCopy.innerError == innerError_2)
    }

    @Test
    fun inner() {
        val innerCopy =
                innerError_1.copy()
                        .inner(innerError_2.copy())
                        .inner(innerError_3.copy())

        assert(innerCopy == innerError_3)
        assert(innerCopy.innerError == innerError_2)
        assert(innerCopy.innerError?.innerError == innerError_1)
    }

    @Test fun outer() {
        val innerCopy =
                innerError_1.copy()
                        .outer(innerError_2.copy()
                                .outer(innerError_3.copy()))

        assert(innerCopy == innerError_1)
        assert(innerCopy.innerError == innerError_2)
        assert(innerCopy.innerError?.innerError == innerError_3)
    }

    @Test fun invertOuter() {
        val innerCopy =
                innerError_1.copy()
                        .outer(innerError_2.copy()
                                .outer(innerError_3.copy()))

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
                innerError_1.copy()
                        .inner(innerError_2.copy())
                        .inner(innerError_3.copy())

        assert(innerCopy == innerError_3)
        assert(innerCopy.innerError == innerError_2)
        assert(innerCopy.innerError?.innerError == innerError_1)

        val invertInnerCopy = innerCopy.invert()

        assert(invertInnerCopy == innerError_1)
        assert(invertInnerCopy.innerError == innerError_2)
        assert(invertInnerCopy.innerError?.innerError == innerError_3)
    }

}