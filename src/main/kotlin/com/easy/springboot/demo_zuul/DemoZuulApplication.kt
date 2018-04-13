package com.easy.springboot.demo_zuul

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@SpringBootApplication
@EnableZuulProxy
open class DemoZuulApplication

fun main(args: Array<String>) {
    runApplication<DemoZuulApplication>(*args)
}

//@EnableZuulProxy简单理解为@EnableZuulServer的增强版，当Zuul与Eureka、Ribbon等组件配合使用时，我们使用@EnableZuulProxy。
