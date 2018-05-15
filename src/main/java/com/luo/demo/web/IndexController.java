package com.luo.demo.web;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author xiangnan
 */
@RestController
@RequestMapping
public class IndexController {

    private Random random = new Random();

    @Resource
    private OkHttpClient client;

    @GetMapping
    public String index() {
        return "hello world";
    }

    /**
     * service1
     */
    @RequestMapping("service1")
    public String start() throws InterruptedException, IOException {
        int sleep= random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);

        Request request = new Request.Builder().url("http://localhost:9090/service2").get().build();
        Response response = client.newCall(request).execute();

        return " [service1 sleep " + sleep +" ms]" + response.body().toString();
    }

    /**
     * service2
     */
    @RequestMapping("service2")
    public String foo() throws InterruptedException, IOException {
        int sleep= random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);

        Request request = new Request.Builder().url("http://localhost:9091/service3").get().build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        request = new Request.Builder().url("http://localhost:9092/service4").get().build();
        response = client.newCall(request).execute();
        result += response.body().string();

        return " [service2 sleep " + sleep +" ms]" + result;
    }

    /**
     * service3
     */
    @RequestMapping("service3")
    public String bar() throws InterruptedException, IOException {
        int sleep= random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return " [service3 sleep " + sleep +" ms]";
    }

    /**
     * service4
     */
    @RequestMapping("service4")
    public String tar() throws InterruptedException, IOException {
        int sleep= random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return " [service4 sleep " + sleep +" ms]";
    }

}
