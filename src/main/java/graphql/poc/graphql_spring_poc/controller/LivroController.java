package graphql.poc.graphql_spring_poc.controller;

import graphql.poc.graphql_spring_poc.dto.LivroInput;
import graphql.poc.graphql_spring_poc.model.Livro;
import graphql.poc.graphql_spring_poc.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LivroController {

    private final LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    // --- QUERIES ---
    @QueryMapping
    public Livro buscarPorId(@Argument Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Livro> buscarTodos() {
        return livroRepository.findAll();
    }

    @QueryMapping
    public List<Livro> buscarPorAutor(@Argument String autor) {
        return livroRepository.findByAutor(autor);
    }

    // --- MUTATIONS ---
    @MutationMapping
    public Livro adicionarLivro(@Argument String titulo, @Argument String autor) {
        Livro novoLivro = new Livro();
        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        return livroRepository.save(novoLivro);
    }

    @MutationMapping
    public Livro atualizarLivro(
            @Argument Long id,
            @Argument String titulo,
            @Argument String autor
    ) {
        return livroRepository.findById(id)
                .map(livro -> {
                    if (titulo != null) livro.setTitulo(titulo);
                    if (autor != null) livro.setAutor(autor);
                    return livroRepository.save(livro);
                })
                .orElse(null);
    }

    @MutationMapping
    public Boolean deletarLivro(@Argument Long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @MutationMapping
    public List<Livro> adicionarColecaoDeLivros(
            @Argument @Valid List<LivroInput> livros // Valida cada item da lista
    ) {
        List<Livro> novosLivros = livros.stream()
                .map(input -> new Livro(null, input.getTitulo(), input.getAutor()))
                .collect(Collectors.toList());

        return livroRepository.saveAll(novosLivros);
    }
}