// !LANGUAGE: +GenerateNullChecksForGenericTypeReturningFunctions
// TARGET_BACKEND: JVM
// IGNORE_BACKEND: JVM_IR

fun <T> foo(): T = null as T

val x = foo<String>()

fun test() = x

// 1 IFNONNULL
// 1 INVOKESTATIC kotlin/jvm/internal/Intrinsics.throwNpe
