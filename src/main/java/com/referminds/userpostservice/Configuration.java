package com.referminds.userpostservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("user-post-service")
@Data
public class Configuration {
	private Integer expiresAfter;
	private Integer maxPostsByUser;

}
