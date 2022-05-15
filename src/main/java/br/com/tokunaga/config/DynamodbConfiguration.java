package br.com.tokunaga.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.retry.PredefinedRetryPolicies;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamodbConfiguration {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.dynamodb.endpoint}")
    private String endpoint;

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.access.key}")
    private String secretKey;

    @Value("${aws.client.configuration.connection-timeout:10000}")
    private int connectionTimeout;

    @Value("${aws.client.configuration.client-execution-timeout:0}")
    private int clientExecutionTimeout;

    @Value("${aws.client.configuration.request-timeout:0}")
    private int requestTimeout;

    @Value("${aws.client.configuration.socket-timeout:50000}")
    private int socketTimeout;

    @Value("${aws.client.configuration.max-retry:10}")
    private int maxRetry;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration())
                .withCredentials(credentialsProvider())
                .withClientConfiguration(clientConfiguration())
                .build();
    }

    private AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(endpoint, region);
    }

    private AWSStaticCredentialsProvider credentialsProvider() {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey)
        );
    }

    private ClientConfiguration clientConfiguration() {
        return new ClientConfiguration()
                .withConnectionTimeout(connectionTimeout)
                .withClientExecutionTimeout(clientExecutionTimeout)
                .withRequestTimeout(requestTimeout)
                .withSocketTimeout(socketTimeout)
                .withRetryPolicy(PredefinedRetryPolicies.getDynamoDBDefaultRetryPolicyWithCustomMaxRetries(maxRetry));
    }
}
