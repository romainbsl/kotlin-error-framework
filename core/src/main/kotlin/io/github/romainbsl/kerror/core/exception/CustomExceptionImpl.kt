package io.github.romainbsl.kerror.core.exception

import io.github.romainbsl.kerror.core.error.ErrorBase

class CustomPersistenceException :
        AbstractException(ErrorBase("PERSISTENCE_EXCEPTION", "There has been an issue in the persistence layer!"))
class CustomServiceException :
        AbstractException(ErrorBase("SERVICE_EXCEPTION", "There has been an issue in the service layer!"))
class CustomValidationException :
        AbstractException(ErrorBase("VALIDATION_EXCEPTION", "The input data are invalid!"))