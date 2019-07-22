package com.sprincloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 消费测试
     * @param name
     * @return
     */
    public String test(String name){
        return restTemplate.getForObject("http://service-client/client?name="+name,String.class);

    }

    /**
     * 熔断测试
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hystruxService(String name) {
        return restTemplate.getForObject("http://service-client/hi?name="+name,String.class);
    }

}
