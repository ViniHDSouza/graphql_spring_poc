package graphql.poc.graphql_spring_poc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Usamos Long para IDs gerados automaticamente

    private String titulo;
    private String autor;

    public Livro(Long id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }
}
