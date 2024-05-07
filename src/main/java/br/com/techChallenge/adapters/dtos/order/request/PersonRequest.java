package br.com.techChallenge.adapters.dtos.order.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {

    private String email;

    private String cpf;
}
