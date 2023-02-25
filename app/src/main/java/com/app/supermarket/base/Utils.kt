package com.app.supermarket.base

fun String.isValidEgyNumber() = Regex(
    "^(00201|201|\\+201|01|1)([0125])([0-9]{8})\$"
).matches(this)
