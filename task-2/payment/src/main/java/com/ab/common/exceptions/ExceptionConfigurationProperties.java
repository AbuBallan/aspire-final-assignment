package com.ab.common.exceptions;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "com.ab")
@Component
@Data
public class ExceptionConfigurationProperties {
    private Map<String, ErrorInfo> exceptions;
}
