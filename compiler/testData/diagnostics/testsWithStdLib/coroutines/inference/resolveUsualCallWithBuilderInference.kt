// !DIAGNOSTICS: -UNUSED_PARAMETER
// !WITH_NEW_INFERENCE
// FILE: annotation.kt
// NI_EXPECTED_FILE

package kotlin

annotation class BuilderInference

// FILE: test.kt

class Builder<T> {
    fun add(t: T) {}
}

fun <S> build(@BuilderInference g: Builder<S>.() -> Unit): List<S> = TODO()
fun <S> wrongBuild(g: Builder<S>.() -> Unit): List<S> = TODO()

fun <S> Builder<S>.extensionAdd(s: S) {}

@BuilderInference
fun <S> Builder<S>.safeExtensionAdd(s: S) {}

val member = build {
    <!NI;NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>add<!>(42)
}

val memberWithoutAnn = <!NI;IMPLICIT_NOTHING_AS_TYPE_PARAMETER, NI;NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER, OI;TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>wrongBuild<!> {
    add(<!NI;CONSTANT_EXPECTED_TYPE_MISMATCH!>42<!>)
}

val extension = <!OI;TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>build<!> {
    <!NI;NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>extensionAdd<!>("foo")
}

val safeExtension = build {
    <!NI;NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>safeExtensionAdd<!>("foo")
}
