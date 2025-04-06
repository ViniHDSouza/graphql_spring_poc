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

    // Injeção de dependência do repositório para acesso aos dados dos livros
    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    // --- QUERIES ---
    // Endpoint GraphQL para buscar um livro pelo seu ID.
    @QueryMapping
    public Livro buscarPorId(@Argument Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    // Endpoint GraphQL para listar todos os livros cadastrados.
    @QueryMapping
    public List<Livro> buscarTodos() {
        return livroRepository.findAll();
    }

    // Endpoint GraphQL para buscar livros de acordo com o autor.
    @QueryMapping
    public List<Livro> buscarPorAutor(@Argument String autor) {
        return livroRepository.findByAutor(autor);
    }

    // --- MUTATIONS ---
    // Endpoint GraphQL para adicionar um novo livro utilizando título e autor.
    @MutationMapping
    public Livro adicionarLivro(@Argument String titulo, @Argument String autor) {
        Livro novoLivro = new Livro();
        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        return livroRepository.save(novoLivro);
    }

    // Endpoint para atualizar os dados de um livro existente.
    @MutationMapping
    public Livro atualizarLivro(
            @Argument Long id,
            @Argument String titulo,
            @Argument String autor
    ) {
        return livroRepository.findById(id)
                .map(livro -> {
                    // Atualiza os campos se os novos valores forem informados.
                    if (titulo != null) livro.setTitulo(titulo);
                    if (autor != null) livro.setAutor(autor);
                    return livroRepository.save(livro);
                })
                .orElse(null);
    }

    // Endpoint para deletar um livro pelo ID.
    @MutationMapping
    public Boolean deletarLivro(@Argument Long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Endpoint para adicionar uma coleção de livros.
    // A lista de LivroInput é validada (com @Valid) para garantir que os campos obrigatórios estejam preenchidos.
    @MutationMapping
    public List<Livro> adicionarColecaoDeLivros(
            @Argument @Valid List<LivroInput> livros
    ) {
        List<Livro> novosLivros = livros.stream()
                .map(input -> new Livro(null, input.getTitulo(), input.getAutor()))
                .collect(Collectors.toList());

        return livroRepository.saveAll(novosLivros);
    }
}