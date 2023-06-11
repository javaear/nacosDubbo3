package org.jxx.dubbo.filter;


import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Slf4j
//Activate(group = {/*"provider",  */"consumer"})
public class ServiceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("start========service接口调用，接口：{}，参数：{}", invocation.getMethodName(), invocation.getArguments());
        Result result = invoker.invoke(invocation);
        log.info("end========service接口调用，结果：{}", result.getValue());
        return result;
    }
}
