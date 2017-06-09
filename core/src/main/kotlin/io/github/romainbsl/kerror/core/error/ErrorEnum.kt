package io.github.romainbsl.kerror.core.error

enum class ErrorEnum private constructor(val code: String, val message: String) {
    // adding errors here are breaking changes (meaning an update in version number is needed)
    // reason   : any handling of errors should handle all of these errors meaning any handling will have to be updated if errors are added here
    // solution : use innereerror and/or details to give a more specific io.github.romainbsl.errorframework.core.test.error
    //              (=> existing handlers don't have to be updated, new handlers can handle the more specific io.github.romainbsl.errorframework.core.test.error)

    PERSISTENCE_ERROR("PERSISTENCE_ERROR", "There was an error in the Data Access Layer of the service"),
    BUSINESS_ERROR("BUSINESS_ERROR", "There was an error in the Business Layer of the service"),
    INPUT_VALIDATION_ERROR("INPUT_VALIDATION_ERROR", "The input object given to the service contained errors")

}
