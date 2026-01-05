package com.example.util;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Component
public class AliOSSUtils {
	// 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
	EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
	// 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
	//此处进行配置注入
	@Autowired
	private AilOSSproperties ailOSSproperties;
	
	public AliOSSUtils() throws ClientException {
	}
	//https://web-ticast.oss-cn-guangzhou.aliyuncs.com/test.docx
	
	/**
	 * 实现上传图片到OSS
	 */
	public String upload(MultipartFile file) throws IOException {
		//获取参数
		String region = ailOSSproperties.getRegion();
		String endpoint = ailOSSproperties.getEndpoint();
		String bucketName = ailOSSproperties.getBucketName();
		// 获取上传的文件的输入流
		InputStream inputStream = file.getInputStream();
		
		// 避免文件覆盖
		String originalFilename = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
		
		//上传文件到 OSS
		ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
		clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
		OSS ossClient = OSSClientBuilder.create()
				.endpoint(endpoint)
				.credentialsProvider(credentialsProvider)
				.clientConfiguration(clientBuilderConfiguration)
				.region(region)
				.build();
		ossClient.putObject(bucketName, fileName, inputStream);
		
		//文件访问路径
		String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
		// 关闭ossClient
		ossClient.shutdown();
		return url;// 把上传到oss的路径返回
	}
	
}
