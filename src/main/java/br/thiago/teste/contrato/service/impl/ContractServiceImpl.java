package br.thiago.teste.contrato.service.impl;

import br.thiago.teste.contrato.entity.Contract;
import br.thiago.teste.contrato.entity.ContractDTO;
import br.thiago.teste.contrato.repositories.ContractRepository;
import br.thiago.teste.contrato.service.ContractService;
import br.thiago.teste.contrato.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository repository;

    @Autowired
    private ModelMapper mapper;

    public Contract findById(String idContract) {
        Optional<Contract> obj = repository.findById(idContract);
        return obj.orElseThrow(() -> new ObjectNotFoundException("id não encontrado"));
    }

    public List<Contract> findAll() {
        return (List<Contract>) repository.findAll();
    }

    @Override
    public Contract create(ContractDTO obj) {
        return repository.save(mapper.map(obj,Contract.class));
    }

    @Override
    public Contract update(ContractDTO obj) {
        return repository.save(mapper.map(obj,Contract.class));
    }

    @Override
    public void delete(String idContract) {
        repository.deleteById(idContract);
    }
}
