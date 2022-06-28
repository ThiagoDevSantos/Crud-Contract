package br.thiago.teste.contrato.repositories;

import br.thiago.teste.contrato.entity.Contract;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ContractRepository extends CrudRepository<Contract,String> {

}
