package io.github.romainbsl.kerror.core.error

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ErrorBaseTest {
    @Test fun getCode() {
        assertThat(errorBase_1)
                .isNotNull()
                .hasFieldOrPropertyWithValue("code", "ErrorBaseCode_1")
        assertThat(errorBase_2)
                .isNotNull()
                .hasFieldOrPropertyWithValue("code", "ErrorBaseCode_2")
        assertThat(errorBase_3)
                .isNotNull()
                .hasFieldOrPropertyWithValue("code", "ErrorBaseCode_3")
    }

    @Test fun getMessage() {
        assertThat(errorBase_1)
                .isNotNull()
                .hasFieldOrPropertyWithValue("message", "ErrorBaseMsg_1")
        assertThat(errorBase_2)
                .isNotNull()
                .hasFieldOrPropertyWithValue("message", "ErrorBaseMsg_2")
        assertThat(errorBase_3)
                .isNotNull()
                .hasFieldOrPropertyWithValue("message", "ErrorBaseMsg_3")
    }

    @Test fun errorHashCode() {
        assertThat(errorBase_1.hashCode())
                .isNotNull()
                .isEqualTo(1043150723)
        assertThat(errorBase_2.hashCode())
                .isNotNull()
                .isEqualTo(1043181475)
        assertThat(errorBase_3.hashCode())
                .isNotNull()
                .isEqualTo(1043212227)
    }

    @Test fun getInnerError() {
        assertThat(errorBase_1.innerError)
                .isNull()
        assertThat(errorBase_2.innerError)
                .isNull()
        assertThat(errorBase_3.innerError)
                .isNull()

        assertThat(errorBase_21.innerError)
                .isNotNull()
                .isNotSameAs(innerError_1)
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")
        assertThat(errorBase_21.innerError?.innerError)
                .isNotNull()
                .isNotSameAs(innerError_2)
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")
        assertThat(errorBase_21.innerError?.innerError?.innerError)
                .isNotNull()
                .isNotSameAs(innerError_3)
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")

        assertThat(errorBase_22.innerError)
                .isNotNull()
                .isNotSameAs(innerError_3)
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")
        assertThat(errorBase_22.innerError?.innerError)
                .isNotNull()
                .isNotSameAs(innerError_2)
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")
        assertThat(errorBase_22.innerError?.innerError?.innerError)
                .isNotNull()
                .isNotSameAs(innerError_1)
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")
    }

    @Test fun getDetails() {
        assertThat(errorBase_31).isNotNull()
        assertThat(errorBase_31.details).isEmpty()

        assertThat(errorBase_32).isNotNull()
        assertThat(errorBase_32.details).isEmpty()

        assertThat(errorBase_35.details)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(errorBase_31, errorBase_32)

        assertThat(errorBase_36.details)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(errorBase_33)

        assertThat(errorBase_36.details.get(0).details)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(errorBase_34)
    }

    @Test fun addDetail() {
        assertThat(errorBase_41.details).isEmpty()

        errorBase_41.details.add(errorBase_42)

        assertThat(errorBase_41.details)
                .isNotEmpty()

        assertThat(errorBase_41.details)
                .hasSize(1)
                .containsExactly(errorBase_42)

        errorBase_41.details.add(errorBase_43)

        assertThat(errorBase_41.details).isNotEmpty()

        assertThat(errorBase_41.details)
                .hasSize(2)
                .containsExactlyInAnyOrder(errorBase_42, errorBase_43)

        assertThat(errorBase_43.details).isEmpty()

        errorBase_43.details.add(errorBase_44)

        assertThat(errorBase_43.details).isNotEmpty()

        assertThat(errorBase_43.details)
                .hasSize(1)
                .containsExactly(errorBase_44)
    }

    @Test fun inner() {
        assertThat(errorBase_51.innerError).isNull()

        errorBase_51.inner(innerError_1.clone())

        assertThat(errorBase_51.innerError)
                .isNotNull()
                .isNotSameAs(innerError_1)
                .isEqualTo(innerError_1)

        errorBase_51.inner(innerError_2.clone())

        assertThat(errorBase_51.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")
        assertThat(errorBase_51.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")

        errorBase_51.inner(innerError_3.clone())

        assertThat(errorBase_51.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")
        assertThat(errorBase_51.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")
        assertThat(errorBase_51.innerError?.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")
    }

    @Test fun outer() {
        assertThat(errorBase_61.innerError).isNull()

        errorBase_61.outer(innerError_1.clone())
        assertThat(errorBase_61.innerError)
                .isNotNull()
                .isEqualTo(innerError_1)

        errorBase_61.outer(innerError_2.clone())
        assertThat(errorBase_61.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")
        assertThat(errorBase_61.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")
    }

    @Test fun invertInneredInnerError() {
        assertThat(errorBase_71.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")
        assertThat(errorBase_71.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")
        assertThat(errorBase_71.innerError?.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")

        errorBase_71.invertInnerError()

        assertThat(errorBase_71.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")
        assertThat(errorBase_71.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")
        assertThat(errorBase_71.innerError?.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")
    }

    @Test fun invertOuteredInnerError() {
        assertThat(errorBase_72.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")
        assertThat(errorBase_72.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")
        assertThat(errorBase_72.innerError?.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")

        errorBase_72.invertInnerError()

        assertThat(errorBase_72.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_3, "innerError")
        assertThat(errorBase_72.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_2, "innerError")
        assertThat(errorBase_72.innerError?.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(innerError_1, "innerError")
    }
}