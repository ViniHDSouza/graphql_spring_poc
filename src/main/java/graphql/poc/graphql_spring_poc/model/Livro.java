package graphql.poc.graphql_spring_poc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a entidade Livro no banco de dados.
 * Mapeada via JPA para uma tabela que armazenará os livros.
 */
@Data
@NoArgsConstructor
@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único para cada livro.

    // Título do livro.
    private String titulo;
    
    // Autor do livro.
    private String autor;

    // Construtor auxiliar para facilitar a criação usando todos os campos
    public Livro(Long id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }
}
