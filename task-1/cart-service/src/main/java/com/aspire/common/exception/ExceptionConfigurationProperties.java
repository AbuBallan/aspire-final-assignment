package com.aspire.common.exception;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "com.aspire")
@Component
@Data
public class ExceptionConfigurationProperties {
    private Map<String, ErrorInfo> exceptions;
}
