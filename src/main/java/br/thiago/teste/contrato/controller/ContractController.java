package br.thiago.teste.contrato.controller;

import br.thiago.teste.contrato.entity.Contract;
import br.thiago.teste.contrato.entity.ContractDTO;
import br.thiago.teste.contrato.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/contract")
@RequiredArgsConstructor
@Api("API REST")
public class ContractController {

    public static final String ID = "/{idContract}";
    public static final String APPLICATION_JSON = "application/json";


    private final ContractService service;

    private final ModelMapper mapper;

    @GetMapping(value = ID,produces = APPLICATION_JSON)
    @ApiOperation(value = "List By Id")
    public ResponseEntity<ContractDTO> findById(@PathVariable String idContract) {
        return ResponseEntity.ok().body(mapper.map(service.findById(idContract), ContractDTO.class));
    }

    @GetMapping(produces = APPLICATION_JSON)
    @ApiOperation(value = "List All")
    public ResponseEntity<List<ContractDTO>> findAll() {
        return ResponseEntity.ok()
                .body(service.findAll().stream().map(x -> mapper.map(x, ContractDTO.class))
                        .collect(Collectors.toList()));
    }

    @PostMapping(produces = APPLICATION_JSON,consumes = APPLICATION_JSON)
    @ApiOperation(value = "Save Contract")
    public ResponseEntity<ContractDTO> create(@RequestBody ContractDTO user){
        ContractDTO dto= mapper.map(service.create(user),ContractDTO.class);
        return ResponseEntity.ok().body(dto);

    }

    @PutMapping(value = ID,  produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
    @ApiOperation(value = "Update Contract")
    public ResponseEntity<ContractDTO> update(@PathVariable String idContract, @RequestBody ContractDTO obj){
        obj.setIdContract(idContract);
        return ResponseEntity.ok().body(mapper.map(service.update(obj),ContractDTO.class));
    }

    @DeleteMapping(value = ID, produces = APPLICATION_JSON)
    @ApiOperation(value = "Delete Contract")
    public ResponseEntity<ContractDTO> delete(@PathVariable String idContract){
        service.delete(idContract);
        return ResponseEntity.noContent().build();

    }

}
