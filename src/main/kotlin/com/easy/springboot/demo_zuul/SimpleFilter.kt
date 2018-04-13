package com.easy.springboot.demo_zuul

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class SimpleFilter : ZuulFilter() {
    private val log = LoggerFactory.getLogger(SimpleFilter::class.java)
    override fun run(): Any? {
        val ctx = RequestContext.getCurrentContext()
        val request = ctx.request

        log.info(String.format("%s request to %s", request.method, request.requestURL.toString()))
        log.info(String.format("LocalAddr: %s", request.localAddr))
        log.info(String.format("LocalName: %s", request.localName))
        log.info(String.format("LocalPort: %s", request.localPort))

        log.info(String.format("RemoteAddr: %s", request.remoteAddr))
        log.info(String.format("RemoteHost: %s", request.remoteHost))
        log.info(String.format("RemotePort: %s", request.remotePort))

        return null
    }

    override fun shouldFilter(): Boolean {
        // 判断是否需要过滤
        return true
    }

    override fun filterType(): String {
        // 过滤器类型
        return "pre"
    }

    override fun filterOrder(): Int {
        // 过滤器的优先级，越大越靠后执行
        return 1
    }

}


//to classify a filter by type. Standard types in Zuul are
//"pre" for pre-routing filtering,
//"route" for routing to an origin,
//"post" for post-routing filters,
//"error" for error handling.
//
//We also support a "static" type for static responses see  StaticResponseFilter.
//Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)

//下面有几种标准的过滤器类型：
//
//pre：这种过滤器在请求到达Origin Server之前调用。比如身份验证，在集群中选择请求的Origin Server，记log等。
//route：在这种过滤器中把用户请求发送给Origin Server。发送给Origin Server的用户请求在这类过滤器中build。并使用Apache HttpClient或者Netfilx Ribbon发送给Origin Server。
//post：这种过滤器在用户请求从Origin Server返回以后执行。比如在返回的response上面加response header，做各种统计等。并在该过滤器中把response返回给客户。
//error：在其他阶段发生错误时执行该过滤器。
//客户定制：支持自定义静态响应的"静态"类型, 请参见 StaticResponseFilter类。
//通过调用 FilterProcessor.runFilters (类型) 来创建或添加并运行任何 filterType。