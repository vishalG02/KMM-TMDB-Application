package com.vis.myapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform