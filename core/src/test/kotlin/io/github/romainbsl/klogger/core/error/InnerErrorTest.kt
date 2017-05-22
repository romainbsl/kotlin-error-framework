package io.github.romainbsl.klogger.core.error

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy

class InnerErrorTest {

    //region Main InnerError Mock
    val mainCode = "ERROR_CODE"
    val mainmessage = "ERROR_MESSAGE"
    var mockMainInnerError = spy(InnerError::class.java)
    //endregion1
    //region Level 1 sub inner error
    val level1code = "LEVEL1_ERROR_CODE"
    val level1message = "LEVEL1_ERROR_MESSAGE"
    var mockLevel1InnerError = spy(InnerError::class.java)
    //endregion
    //region Level 2 sub inner error
    val level2code = "LEVEL2_ERROR_CODE"
    val level2message = "LEVEL2_ERROR_MESSAGE"
    var mockLevel2InnerError = spy(InnerError::class.java)
    //endregion

    @Before fun setup() {
         // setup mockMainInnerError
        `when`(mockMainInnerError.code).thenReturn(mainCode)
        `when`(mockMainInnerError.message).thenReturn(mainmessage)
        // setup mockLevel1InnerError
        `when`(mockLevel1InnerError.code).thenReturn(level1code)
        `when`(mockLevel1InnerError.message).thenReturn(level1message)
        // setup mockLevel2InnerError
        `when`(mockLevel2InnerError.code).thenReturn(level2code)
        `when`(mockLevel2InnerError.message).thenReturn(level2message)
    }

    @Test fun setOuterError() {
        mockLevel2InnerError.setOuterError(mockLevel1InnerError)
        mockLevel1InnerError.setOuterError(mockMainInnerError)

        assertThat(mockMainInnerError.innerError)
                .isNotNull()
                .isEqualToComparingFieldByField(mockLevel1InnerError)

        assertThat(mockLevel1InnerError.innerError)
                .isNotNull()
                .isEqualToComparingFieldByField(mockLevel2InnerError)
    }

    @Test fun invert() {
        `when`(mockMainInnerError.innerError).thenReturn(mockLevel1InnerError)
        `when`(mockLevel1InnerError.innerError).thenReturn(mockLevel2InnerError)
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

    @Test fun valueObject() {
        assertThat(mockMainInnerError.code).isEqualTo(mainCode)
        assertThat(mockMainInnerError.message).isEqualTo(mainmessage)

        assertThat(mockLevel1InnerError.code).isEqualTo(level1code)
        assertThat(mockLevel1InnerError.message).isEqualTo(level1message)

        assertThat(mockLevel2InnerError.code).isEqualTo(level2code)
        assertThat(mockLevel2InnerError.message).isEqualTo(level2message)

    }
}
