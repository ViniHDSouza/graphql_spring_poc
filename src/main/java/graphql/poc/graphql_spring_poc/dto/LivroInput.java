package graphql.poc.graphql_spring_poc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;


@Data
@AllArgsConstructor
public class LivroInput {
    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String autor;
}