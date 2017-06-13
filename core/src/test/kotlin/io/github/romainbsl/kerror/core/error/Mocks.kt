package io.github.romainbsl.kerror.core.error

/* InnerErrors for unit tests */
// InnerError.code / InnerError.message
val innerError_1 = InnerError("InnerCode_1", "InnerMsg_1")
val innerError_2 = InnerError("InnerCode_2", "InnerMsg_2")
val innerError_3 = InnerError("InnerCode_3", "InnerMsg_3")

/* ErrorBase for unit tests */
// ErrorBase.code / ErrorBase.message
val errorBase_1 = ErrorBase("ErrorBaseCode_1", "ErrorBaseMsg_1")
val errorBase_2 = ErrorBase("ErrorBaseCode_2", "ErrorBaseMsg_2")
val errorBase_3 = ErrorBase("ErrorBaseCode_3", "ErrorBaseMsg_3")

// InnerError.outer
val errorBase_21 = ErrorBase("ErrorBaseCode_21", "ErrorBaseMsg_21").apply {
    outer(
            innerError_1.clone().outer(innerError_2.clone().outer(innerError_3.clone()))
    )
}
// InnerError.inner
val errorBase_22 = ErrorBase("ErrorBaseCode_22", "ErrorBaseMsg_22").apply {
    inner(
            innerError_1.clone().inner(innerError_2.clone()).inner(innerError_3.clone())
    )
}

// get ErrorBase.details
val errorBase_31 = ErrorBase("ErrorBaseCode_31", "ErrorBaseMsg_31")
val errorBase_32 = ErrorBase("ErrorBaseCode_32", "ErrorBaseMsg_32")
val errorBase_33 = ErrorBase("ErrorBaseCode_33", "ErrorBaseMsg_33")
val errorBase_34 = ErrorBase("ErrorBaseCode_34", "ErrorBaseMsg_34")
val errorBase_35 = ErrorBase("ErrorBaseCode_35", "ErrorBaseMsg_35").apply {
    details.add(0, errorBase_31)
    details.add(1, errorBase_32)
}
val errorBase_36 = ErrorBase("ErrorBaseCode_36", "ErrorBaseMsg_36").apply {
    errorBase_33.details.add(errorBase_34)
    details.add(errorBase_33)
}

// add ErrorBase.details
val errorBase_41 = ErrorBase("ErrorBaseCode_41", "ErrorBaseMsg_41")
val errorBase_42 = ErrorBase("ErrorBaseCode_42", "ErrorBaseMsg_42")
val errorBase_43 = ErrorBase("ErrorBaseCode_43", "ErrorBaseMsg_43")
val errorBase_44 = ErrorBase("ErrorBaseCode_44", "ErrorBaseMsg_44")

// ErrorBase.inner
val errorBase_51 = ErrorBase("ErrorBaseCode_51", "ErrorBaseMsg_51")
// ErrorBase.outer
val errorBase_61 = ErrorBase("ErrorBaseCode_61", "ErrorBaseMsg_61")

// ErrorBase.invertInnerError
val errorBase_71 = ErrorBase("ErrorBaseCode_71", "ErrorBaseMsg_71").apply {
    inner(
            innerError_1.clone()
                    .inner(innerError_2.clone())
                    .inner(innerError_3.clone())
    )
}
val errorBase_72 = ErrorBase("ErrorBaseCode_72", "ErrorBaseMsg_72").apply {
    outer(
            innerError_1.clone()
                    .outer(innerError_2.clone()
                            .outer(innerError_3.clone()))
    )
}
