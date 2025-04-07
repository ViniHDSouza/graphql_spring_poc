package graphql.poc.graphql_spring_poc.repository;

import graphql.poc.graphql_spring_poc.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositório de acesso aos dados da entidade Livro.
 * Fornece operações CRUD automaticamente através do JpaRepository.
 */
public interface LivroRepository extends JpaRepository<Livro, Long> {
    // Método customizado para buscar livros com base no nome do autor.
    List<Livro> findByAutor(String autor);
}