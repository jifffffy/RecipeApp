package org.jiffy.press

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform