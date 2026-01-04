package com.example.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AilOSSproperties {
	private String bucketName;
	private String endpoint;
	private String region;
}
