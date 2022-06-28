package br.thiago.teste.contrato.service;

import br.thiago.teste.contrato.entity.Contract;
import br.thiago.teste.contrato.entity.ContractDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContractService {
    Contract findById (String idContract);
    List<Contract> findAll();
    Contract create(ContractDTO obj);
    Contract update(ContractDTO obj);
    void delete(String idContract);
}
