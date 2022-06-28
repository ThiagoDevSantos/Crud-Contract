package br.thiago.teste.contrato.config;

import ch.qos.logback.core.util.DynamicClassLoadingException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Profile("local")
public class DynamoDBApplication implements ApplicationRunner {

    private final DynamoDBInitialize initialize;

    @Override
    public void run(ApplicationArguments args) {
        initialize.init();
    }

}
