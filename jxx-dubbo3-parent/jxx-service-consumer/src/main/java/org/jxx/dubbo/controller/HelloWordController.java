package org.jxx.dubbo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.jxx.dubbo.Interface.IHelloWordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hi")
public class HelloWordController {
    @DubboReference
    private IHelloWordService helloWordService;
    @GetMapping("/word")
    public String join( @RequestParam("w") String w){

        String result =  helloWordService.hello(w);
        log.info("=============result:======"+result);
        return result;
    }
}
