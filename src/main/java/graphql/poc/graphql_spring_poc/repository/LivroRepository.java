package graphql.poc.graphql_spring_poc.repository;

import graphql.poc.graphql_spring_poc.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutor(String autor);
}