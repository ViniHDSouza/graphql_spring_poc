package graphql.poc.graphql_spring_poc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * DTO (Data Transfer Object) para entrada de dados na criação ou atualização de um Livro.
 * A anotação @NotBlank garante que o campo 'titulo' seja informado.
 */
@Data
@AllArgsConstructor
public class LivroInput {
    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    // O campo 'autor' é opcional, podendo ser informado conforme necessário.
    private String autor;
}