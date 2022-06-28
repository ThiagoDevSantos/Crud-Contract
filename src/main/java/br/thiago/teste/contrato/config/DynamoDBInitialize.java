package br.thiago.teste.contrato.config;

import br.thiago.teste.contrato.entity.Contract;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class DynamoDBInitialize {
    private static final Logger log = LoggerFactory.getLogger(DynamoDBInitialize.class);

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    public void init() {
        ListTablesResult list = amazonDynamoDB.listTables();
        var tableExist = false;
        for (String tableName : list.getTableNames()) {
            log.debug("Listing DynamoDB Tables: {}", tableName);
            tableExist = true;
        }

        if (!tableExist) {
            log.debug("Creating DynamoDB Tables");
            var req = dynamoDBMapper.generateCreateTableRequest(Contract.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
            amazonDynamoDB.createTable(req);

            log.info("DynamoDB tables created successfully");
        }
    }
}