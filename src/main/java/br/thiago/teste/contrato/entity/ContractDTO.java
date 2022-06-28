package br.thiago.teste.contrato.entity;

import br.thiago.teste.contrato.utils.Cpf;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class ContractDTO {

     @NotBlank
     String idContract;

     @NotBlank
     @CPF
     String cpf;

     @NotBlank
     String product;
}
