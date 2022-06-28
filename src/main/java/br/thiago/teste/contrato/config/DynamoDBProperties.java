package br.thiago.teste.contrato.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "amazon")
@Getter
@Setter
public class DynamoDBProperties {
    @JsonIgnore
    @Value("${amazon.aws.accessKey}")
    private String accessKey;

    @JsonIgnore
    @Value("${amazon.aws.secretKey}")
    private String secretKey;

    @Value("${amazon.aws.region}")
    private String region;

    @Value("${amazon.aws.endpoint}")
    private String endpoint;

    @JsonIgnore
    @Value("${amazon.dynamodb.tablePrefix}")
    private String prefix;

    @Value("${amazon.dynamodb.localhost}")
    private boolean localhost;

}
