package br.com.digitalinnovation.heroisapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
//import org.springframework.util.springUtils;

@Configuration
@EnableDynamoDBRepositories
public class DynamoConfig {
    @Value("{$amazon.dynamodb.enpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("{$AWS_access_key_id}")
    private String amazonAWSAcesskey;

    @Value("{$AWS_secret_access_key}")
    private String amazonAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamonDB(){
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
        if(!StringUtils.isEmpty(amazonDynamoDBEndpoint)){
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }
        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials(){
        return new BasicAWSCredentials(amazonAWSAcesskey, amazonAWSSecretKey);
    }
}
