package com.snp.userservice.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Getter
@ConfigurationProperties("com.snp.userservice")
@RefreshScope
public class ConfigProperties {
}
