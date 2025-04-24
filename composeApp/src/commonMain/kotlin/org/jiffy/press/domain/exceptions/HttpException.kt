package org.jiffy.press.domain.exceptions

class HttpException(val httpStatusCode: Int) : Throwable(httpStatusCode.toString())