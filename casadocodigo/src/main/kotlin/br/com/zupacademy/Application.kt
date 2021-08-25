package br.com.zupacademy

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("br.com.zupacademy")
        .defaultEnvironments("prod")
        .start()
}

