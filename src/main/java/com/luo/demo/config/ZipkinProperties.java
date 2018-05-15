package com.luo.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiangnan
 */
@Configuration
@ConfigurationProperties(prefix = "com.zipkin")
@Data
public class ZipkinProperties {
    private String serviceName;
    private String url;
    private int connectTimeout;
    private int readTimeout;
    private int flushInterval;
    private boolean compressionEnabled;
}
