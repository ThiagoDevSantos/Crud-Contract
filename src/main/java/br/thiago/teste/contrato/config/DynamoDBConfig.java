package br.thiago.teste.contrato.config;

import br.thiago.teste.contrato.repositories.ContractRepository;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(dynamoDBMapperConfigRef = "dynamoDBMapperConfig", basePackageClasses = ContractRepository.class)
    public class DynamoDBConfig {

        @Autowired
        private DynamoDBProperties dynamoDBProperties;

        @Bean
        public AmazonDynamoDB amazonDynamoDB() {
            AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(
                            new BasicAWSCredentials(dynamoDBProperties.getAccessKey(),
                                    dynamoDBProperties.getSecretKey())));
            if (dynamoDBProperties.isLocalhost()) {
                builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDBProperties.getEndpoint(),
                        dynamoDBProperties.getRegion()));
            } else {
                builder.withRegion(dynamoDBProperties.getRegion());
            }

            return builder.build();
        }

        @Bean
        public DynamoDBMapperConfig dynamoDBMapperConfig() {
            var builder = new DynamoDBMapperConfig.Builder();

            builder.withTypeConverterFactory(DynamoDBTypeConverterFactory.standard());
            builder.withTableNameResolver(DynamoDBMapperConfig.DefaultTableNameResolver.INSTANCE);
            builder.withTableNameOverride(tableNameOverrider());

            return builder.build();
        }

        @Bean
        public DynamoDBMapperConfig.TableNameOverride tableNameOverrider() {
            return DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(dynamoDBProperties.getPrefix());
        }
    }


