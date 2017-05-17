package io.github.romainbsl.klogger.core.error

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class InnerErrorTest {

    //region Main InnerError Mock
    val mainCode = "ERROR_CODE"
    val mainmessage = "ERROR_MESSAGE"
    @Mock lateinit var mockMainInnerError: InnerError
    //endregion1
    //region Level 1 sub inner error
    val level1code = "LEVEL1_ERROR_CODE"
    val level1message = "LEVEL1_ERROR_MESSAGE"
    @Mock lateinit var mockLevel1InnerError: InnerError
    //endregion
    //region Level 2 sub inner error
    val level2code = "LEVEL2_ERROR_CODE"
    val level2message = "LEVEL2_ERROR_MESSAGE"
    @Mock lateinit var mockLevel2InnerError: InnerError
    //endregion

    @Before fun setup() {
        /* // setup mockMainInnerError
        `when`(mockMainInnerError.code).thenReturn(mainCode)
        `when`(mockMainInnerError.message).thenReturn(mainmessage)
        // setup mockLevel1InnerError
        `when`(mockLevel1InnerError.code).thenReturn(level1code)
        `when`(mockLevel1InnerError.message).thenReturn(level1message)
        // setup mockLevel2InnerError
        `when`(mockLevel2InnerError.code).thenReturn(level2code)
        `when`(mockLevel2InnerError.message).thenReturn(level2message) */

        mockMainInnerError = InnerError(code = mainCode, message = mainmessage)
        mockLevel1InnerError = InnerError(code = level1code, message = level1message)
        mockLevel2InnerError = InnerError(code = level2code, message = level2message)
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
        mockMainInnerError.innerError = mockLevel1InnerError
        mockLevel1InnerError.innerError = mockLevel2InnerError
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
