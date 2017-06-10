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

        assert(
                innerCopy.code == innerError_1.code &&
                        innerCopy.message == innerError_1.message
        )
        assert(
                innerCopy.innerError?.code == innerError_2.code &&
                        innerCopy.innerError?.message == innerError_2.message
        )
    }

    @Test
    fun inner() {
        val innerCopy =
                innerError_1.clone()
                        .inner(innerError_2.clone())
                        .inner(innerError_3.clone())

        assert(
                innerCopy.code == innerError_3.code &&
                        innerCopy.message == innerError_3.message
        )
        assert(
                innerCopy.innerError?.code == innerError_2.code &&
                        innerCopy.innerError?.message == innerError_2.message
        )
        assert(
                innerCopy.innerError?.innerError?.code == innerError_1.code &&
                        innerCopy.innerError?.innerError?.message == innerError_1.message
        )
    }

    @Test fun outer() {
        val innerCopy =
                innerError_1.clone()
                        .outer(innerError_2.clone()
                                .outer(innerError_3.clone()))

        assert(
                innerCopy.code == innerError_1.code &&
                        innerCopy.message == innerError_1.message
        )
        assert(
                innerCopy.innerError?.code == innerError_2.code &&
                        innerCopy.innerError?.message == innerError_2.message
        )
        assert(
                innerCopy.innerError?.innerError?.code == innerError_3.code &&
                        innerCopy.innerError?.innerError?.message == innerError_3.message
        )
    }

    @Test fun invertOuter() {
        val innerCopy =
                innerError_1.clone()
                        .outer(innerError_2.clone()
                                .outer(innerError_3.clone()))

        assert(
                innerCopy.code == innerError_1.code &&
                        innerCopy.message == innerError_1.message
        )
        assert(
                innerCopy.innerError?.code == innerError_2.code &&
                        innerCopy.innerError?.message == innerError_2.message
        )
        assert(
                innerCopy.innerError?.innerError?.code == innerError_3.code &&
                        innerCopy.innerError?.innerError?.message == innerError_3.message
        )
        val invertInnerCopy = innerCopy.invert()

        assert(
                invertInnerCopy.code == innerError_3.code &&
                        invertInnerCopy.message == innerError_3.message
        )
        assert(
                invertInnerCopy.innerError?.code == innerError_2.code &&
                        invertInnerCopy.innerError?.message == innerError_2.message
        )
        assert(
                invertInnerCopy.innerError?.innerError?.code == innerError_1.code &&
                        invertInnerCopy.innerError?.innerError?.message == innerError_1.message
        )
    }

    @Test fun invertInner() {
        val innerCopy =
                innerError_1.clone()
                        .inner(innerError_2.clone())
                        .inner(innerError_3.clone())

        assert(
                innerCopy.code == innerError_3.code &&
                        innerCopy.message == innerError_3.message
        )
        assert(
                innerCopy.innerError?.code == innerError_2.code &&
                        innerCopy.innerError?.message == innerError_2.message
        )
        assert(
                innerCopy.innerError?.innerError?.code == innerError_1.code &&
                        innerCopy.innerError?.innerError?.message == innerError_1.message
        )

        val invertInnerCopy = innerCopy.invert()

        assert(
                invertInnerCopy.code == innerError_1.code &&
                        invertInnerCopy.message == innerError_1.message
        )
        assert(
                invertInnerCopy.innerError?.code == innerError_2.code &&
                        invertInnerCopy.innerError?.message == innerError_2.message
        )
        assert(
                invertInnerCopy.innerError?.innerError?.code == innerError_3.code &&
                        invertInnerCopy.innerError?.innerError?.message == innerError_3.message
        )
    }

}