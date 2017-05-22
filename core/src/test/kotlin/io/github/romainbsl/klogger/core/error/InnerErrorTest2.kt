package io.github.romainbsl.klogger.core.error

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock

class InnerErrorTest2 {

    //region Main InnerError Mock
    val mainCode = "ERROR_CODE"
    val mainmessage = "ERROR_MESSAGE"
    val mockMainInnerError = mock(ImmutableInnerError::class.java)
    //endregion1
    //region Level 1 sub inner error
    val level1code = "LEVEL1_ERROR_CODE"
    val level1message = "LEVEL1_ERROR_MESSAGE"
    val mockLevel1InnerError = mock(ImmutableInnerError::class.java)
    //endregion
    //region Level 2 sub inner error
    val level2code = "LEVEL2_ERROR_CODE"
    val level2message = "LEVEL2_ERROR_MESSAGE"
    val mockLevel2InnerError = mock(ImmutableInnerError::class.java)
    //endregion

    @Before fun setup() {
        doReturn(mainCode).`when`(mockMainInnerError).code
        doReturn(mainmessage).`when`(mockMainInnerError).message
        doReturn(mockLevel1InnerError).`when`(mockMainInnerError).innerError

        doReturn(level1code).`when`(mockLevel1InnerError).code
        doReturn(level1message).`when`(mockLevel1InnerError).message
        doReturn(mockLevel2InnerError).`when`(mockLevel1InnerError).innerError

        doReturn(level2code).`when`(mockLevel2InnerError).code
        doReturn(level2message).`when`(mockLevel2InnerError).message
        doReturn(null).`when`(mockLevel2InnerError).innerError
    }

    @Test fun setOuterError() {

    }
    @Test fun setInnerError() {

    }

    @Test fun invert2() {
        val invertedInnerError = mockMainInnerError.invert()

        assertThat(mockMainInnerError.innerError).isNull()

        assertThat(mockLevel1InnerError.innerError)
                .isNotNull()
                .isEqualToComparingFieldByField(mockMainInnerError)

        assertThat(mockLevel2InnerError.innerError)
                .isNotNull()
                .isEqualToComparingFieldByField(mockLevel1InnerError)

        assertThat(invertedInnerError)
                .isNotNull()
                .isEqualToComparingFieldByField(mockLevel2InnerError)

    }


    @Test fun invert() {
        val invertedInnerError =
            ImmutableInnerError("code_1", "message_1",
                    ImmutableInnerError("code_2", "message_2",
                            ImmutableInnerError("code_3", "message_3",
                                    ImmutableInnerError("code_4", "message_4",
                                            ImmutableInnerError("code_5", "message_5"))))).invert()


        assertThat(invertedInnerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(ImmutableInnerError("code_5", "message_5"), "innerError")
        assertThat(invertedInnerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(ImmutableInnerError("code_4", "message_4"), "innerError")
        assertThat(invertedInnerError?.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(ImmutableInnerError("code_3", "message_3"), "innerError")
        assertThat(invertedInnerError?.innerError?.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(ImmutableInnerError("code_2", "message_2"), "innerError")
        assertThat(invertedInnerError?.innerError?.innerError?.innerError?.innerError)
                .isNotNull()
                .isEqualToIgnoringGivenFields(ImmutableInnerError("code_1", "message_1"), "innerError")
    }

    @Test fun valueObject() {
        assertThat(mockMainInnerError.code).isEqualTo(mainCode)
        assertThat(mockMainInnerError.message).isEqualTo(mainmessage)

        assertThat(mockLevel1InnerError.code).isEqualTo(level1code)
        assertThat(mockLevel1InnerError.message).isEqualTo(level1message)

        assertThat(mockLevel2InnerError.code).isEqualTo(level2code)
        assertThat(mockLevel2InnerError.message).isEqualTo(level2message)

        assertThat(mockMainInnerError.innerError)
                .isNotNull().isEqualTo(mockLevel1InnerError)
        assertThat(mockLevel1InnerError.innerError)
                .isNotNull().isEqualTo(mockLevel2InnerError)
        assertThat(mockLevel2InnerError.innerError).isNull()
    }
}
