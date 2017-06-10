package io.github.romainbsl.kerror.core.error

/* InnerErrors for unit tests */
val innerError_1 = InnerError("InnerCode_1", "InnerMsg_1")
val innerError_2 = InnerError("InnerCode_2", "InnerMsg_2")
val innerError_3 = InnerError("InnerCode_3", "InnerMsg_3")

/* ErrorBase for unit tests */
val errorBase_1 = ErrorBase("ErrorBaseCode_1", "ErrorBaseMsg_1").apply {
    outer(innerError_1.clone())
    outer(innerError_2.clone())
    outer(innerError_3.clone())
}
val errorBase_2 = ErrorBase("ErrorBaseCode_2", "ErrorBaseMsg_2").apply {
    inner(innerError_1.clone())
    inner(innerError_2.clone())
    inner(innerError_3.clone())
}
val errorBase_3 = ErrorBase("ErrorBaseCode_3", "ErrorBaseMsg_3")
val errorBase_4 = ErrorBase("ErrorBaseCode_4", "ErrorBaseMsg_4")