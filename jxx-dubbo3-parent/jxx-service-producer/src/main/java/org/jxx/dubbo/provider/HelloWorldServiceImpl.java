package org.jxx.dubbo.provider;

import org.apache.dubbo.config.annotation.DubboService;
import org.jxx.dubbo.Interface.IHelloWordService;

/**
 * description: 啊
 * date: 2023/6/11
 * author: javaear
 */
@DubboService(group = "jxx-frame")
public class HelloWorldServiceImpl implements IHelloWordService {
    @Override
    public String hello(String message) {
        return "Hi word! "+message;
    }
}
