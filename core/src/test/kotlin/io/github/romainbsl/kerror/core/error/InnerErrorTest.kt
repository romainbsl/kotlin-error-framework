package io.github.romainbsl.kerror.core.error

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class InnerErrorTest {

    @Test fun innerErrorCtor() {
        val innerError = InnerError("innerCtorCode_1", "innerCtorMsg_1",
                InnerError("innerCtorCode_2", "innerCtorMsg_2"))

        assertThat(innerError).isNotNull()

        assertThat(innerError.code)
                .isNotEmpty()
                .isEqualTo("innerCtorCode_1")

        assertThat(innerError.message)
                .isNotEmpty()
                .isEqualTo("innerCtorMsg_1")

        assertThat(innerError.innerError)
                .isNotNull()
                .hasFieldOrProperty("code")
                .hasFieldOrProperty("message")

        assertThat(innerError.innerError?.code)
                .isNotEmpty()
                .isEqualTo("innerCtorCode_2")

        assertThat(innerError.innerError?.message)
                .isNotEmpty()
                .isEqualTo("innerCtorMsg_2")
    }

    @Test fun getCode() {
        assertThat(innerError_1.clone())
                .isNotNull()
                .isNotSameAs(innerError_1)
                .hasFieldOrPropertyWithValue("code", "InnerCode_1")

        assertThat(innerError_2.clone())
                .isNotNull()
                .isNotSameAs(innerError_2)
                .hasFieldOrPropertyWithValue("code", "InnerCode_2")

        assertThat(innerError_3.clone())
                .isNotNull()
                .isNotSameAs(innerError_3)
                .hasFieldOrPropertyWithValue("code", "InnerCode_3")
    }

    @Test fun getMessage() {
        assertThat(innerError_1.clone())
                .isNotNull()
                .isNotSameAs(innerError_1)
                .hasFieldOrPropertyWithValue("message", "InnerMsg_1")

        assertThat(innerError_2.clone())
                .isNotNull()
                .isNotSameAs(innerError_2)
                .hasFieldOrPropertyWithValue("message", "InnerMsg_2")

        assertThat(innerError_3.clone())
                .isNotNull()
                .isNotSameAs(innerError_3)
                .hasFieldOrPropertyWithValue("message", "InnerMsg_3")
    }

    @Test fun innerHashCode() {
        assertThat(innerError_1.clone().hashCode())
                .isNotNull()
                .isInstanceOf(Integer::class.java)
                .isEqualTo(-1174383560)

        assertThat(innerError_2.clone().hashCode())
                .isNotNull()
                .isInstanceOf(Integer::class.java)
                .isEqualTo(-1174382568)

        assertThat(innerError_3.clone().hashCode())
                .isNotNull()
                .isInstanceOf(Integer::class.java)
                .isEqualTo(-1174381576)
    }

    @Test fun getInnerError() {
        val innerCopy =
                innerError_1.clone() outer innerError_2.clone()

        assertThat(innerCopy)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")

        assertThat(innerCopy.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")

        assertThat(innerCopy.innerError?.innerError)
                .isNull()
    }

    @Test fun inner() {
        val innerCopy =
                innerError_1.clone()
                        .inner(innerError_2.clone())
                        .inner(innerError_3.clone())

        assertThat(innerCopy)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")

        assertThat(innerCopy.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")

        assertThat(innerCopy.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")

        assertThat(innerCopy.innerError?.innerError?.innerError)
                .isNull()
    }

    @Test fun outer() {
        val innerCopy =
                innerError_1.clone()
                        .outer(innerError_2.clone()
                                .outer(innerError_3.clone()))

        assertThat(innerCopy)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")

        assertThat(innerCopy.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")

        assertThat(innerCopy.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")

        assertThat(innerCopy.innerError?.innerError?.innerError)
                .isNull()
    }

    @Test fun invertOuter() {
        val innerCopy =
                innerError_1.clone()
                        .outer(innerError_2.clone()
                                .outer(innerError_3.clone()))

        assertThat(innerCopy)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")

        assertThat(innerCopy.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")

        assertThat(innerCopy.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")

        assertThat(innerCopy.innerError?.innerError?.innerError)
                .isNull()

        val invertInnerCopy = innerCopy.invert()

        assertThat(invertInnerCopy)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")

        assertThat(invertInnerCopy.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")

        assertThat(invertInnerCopy.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")

        assertThat(invertInnerCopy.innerError?.innerError?.innerError)
                .isNull()
    }

    @Test fun invert() {
        val innerCopy =
                innerError_1.clone()
                        .inner(innerError_2.clone())
                        .inner(innerError_3.clone())

        assertThat(innerCopy)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")

        assertThat(innerCopy.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")

        assertThat(innerCopy.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")

        assertThat(innerCopy.innerError?.innerError?.innerError)
                .isNull()

        val invertInnerCopy = innerCopy.invert()

        assertThat(invertInnerCopy)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")

        assertThat(invertInnerCopy.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")

        assertThat(invertInnerCopy.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")

        assertThat(innerCopy.innerError?.innerError?.innerError)
                .isNull()

    }

    @Test fun parameterizedInvert() {
        val innerCopy =
                innerError_1.clone()
                        .inner(innerError_2.clone())

        assertThat(innerCopy)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")

        assertThat(innerCopy.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")

        val invertInnerCopy = innerCopy.invert(innerError_3.clone())

        assertThat(invertInnerCopy)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")

        assertThat(invertInnerCopy.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")

        assertThat(invertInnerCopy.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")

        assertThat(innerCopy.innerError?.innerError?.innerError)
                .isNull()
    }
}