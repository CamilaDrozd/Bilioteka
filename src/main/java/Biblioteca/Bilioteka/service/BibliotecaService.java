package Biblioteca.Bilioteka.service;

import Biblioteca.Bilioteka.DTO.LivroDTOatualizar;
import Biblioteca.Bilioteka.DTO.LivroDTOid;
import Biblioteca.Bilioteka.DTO.LivroVendaDTO;
import Biblioteca.Bilioteka.model.Livro;
import Biblioteca.Bilioteka.Livro.LivroRepository;
import Biblioteca.Bilioteka.DTO.LivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaService {

    @Autowired
    private LivroRepository livroRepository;


    public Livro cadastraLivro(Livro livro){
        return livroRepository.save(livro);
    }


//    public Livro cadastraLivro(LivroDTO livroDTO){
//        Livro livro = new Livro();
//        livro.setAutor(livroDTO.getAutor());
//        return livroRepository.save(livro);
//    }

    public List<LivroDTOid> listarLivrosDTO() {
        List<Livro> lista = livroRepository.findAll();
        //percorre lista
        //        para cada elemento da lista
        //          cria um livroDTO =>
        List<LivroDTOid> livrosListaDTO = new ArrayList<LivroDTOid>();
        //Lista inicializado
        if (!lista.isEmpty()){
            for (Livro l: lista) {
                LivroDTOid livroDto = new LivroDTOid();
                //copia os dados necessarios para o DTO
                livroDto.setId(l.getId());
                livroDto.setTitulo(l.getTitulo());
                livroDto.setAutor(l.getAutor());
                livroDto.setEditora(l.getEditora());
                livroDto.setGenero(l.getGenero());

                livrosListaDTO.add(livroDto);
            }
        }else {
            throw new RuntimeException("Lista de livros vazia");
        }
        return livrosListaDTO;
    }


    public LivroDTO buscarIdDTO(Long id) {
        Optional<Livro> optLivro = livroRepository.findById(id);
        LivroDTO livroDTO = new LivroDTO();
        if (!optLivro.isEmpty()) {
            Livro livro = optLivro.get();
            livroDTO.setTitulo(livro.getTitulo());
            livroDTO.setAutor(livro.getAutor());
            livroDTO.setEditora(livro.getEditora());
            livroDTO.setGenero(livro.getGenero());
        }else {
            throw new RuntimeException("Id não encontrado");
        }
        return livroDTO;
    }


    public LivroDTO buscarTitulo(String titulo) {
        Livro livro = livroRepository.findByTitulo(titulo);
        LivroDTO livroDTO = new LivroDTO();
        if (livro != null){
            livroDTO.setTitulo(livro.getTitulo());
            livroDTO.setAutor(livro.getAutor());
            livroDTO.setEditora(livro.getEditora());
            livroDTO.setGenero(livro.getGenero());
        }else {
            throw new RuntimeException("Titulo não encontrado ou mais de um titulo");
        }
        return livroDTO;
    }



    public List<LivroDTO> buscarTitulos(String titulo) {
        //Pegou os livros com titulos do repository/BD
        List<Livro> livrosTitulos = livroRepository.findTitulos(titulo);
        //Fez um New para retornar pro Controller
        List<LivroDTO> livroTitulosDTO = new ArrayList<>();
        if(livrosTitulos != null){
            //Loop para rodar todos os elementos do Livro
            for ( Livro l: livrosTitulos) {
                //Criou novo Dto para guardar as informacoes
                LivroDTO livro = new LivroDTO();
                //Copia os dados necessarios pro Dto
                livro.setTitulo(l.getTitulo());
                livro.setAutor(l.getAutor());
                livro.setEditora(l.getEditora());
                livro.setGenero(l.getGenero());

                //Coloca as informações no Dto para retornar pro Controller
                livroTitulosDTO.add(livro);
            }
        }else {
            throw new RuntimeException("Não há livros com esse título");
        }
        //Retorna a Lista de DTOs com as informações requeridas
        return livroTitulosDTO;
    }



    public List<LivroDTO> buscarAutor(String autor) {
        List<Livro> livrosAutor = livroRepository.findByAutor(autor);
        List<LivroDTO> listaAutorDTO = new ArrayList<>();
        if (livrosAutor != null){
            for(Livro l : livrosAutor){
                LivroDTO livro = new LivroDTO();
                livro.setTitulo(l.getTitulo());
                livro.setAutor(l.getAutor());
                livro.setEditora(l.getEditora());
                livro.setGenero(l.getGenero());

                listaAutorDTO.add(livro);
            }
        }else {
            throw new RuntimeException("Busca por autor não encontrada");
        }
        return listaAutorDTO;
    }

    public List<LivroDTO> buscarGenero(String genero) {
        List<Livro> livrosGenero = livroRepository.findByGenero(genero);
        List<LivroDTO> listaGeneroDTO = new ArrayList<>();
        if (livrosGenero != null){
            for(Livro l : livrosGenero){
                LivroDTO livro = new LivroDTO();
                livro.setTitulo(l.getTitulo());
                livro.setAutor(l.getAutor());
                livro.setEditora(l.getEditora());
                livro.setGenero(l.getGenero());

                listaGeneroDTO.add(livro);
            }
        }else {
            throw new RuntimeException("Busca por genero não encontrada");
        }
        return listaGeneroDTO;
    }


    public Livro atualizarLivro(LivroDTOatualizar livroDTOatualizar) {

       Livro l = livroRepository.findById(livroDTOatualizar.getId()).orElse(null);

       if (livroDTOatualizar.getTitulo() != null){
           l.setTitulo(livroDTOatualizar.getTitulo());
       }
        if (livroDTOatualizar.getAutor() != null){
            l.setAutor(livroDTOatualizar.getAutor());
        }
        if (livroDTOatualizar.getEditora() != null){
            l.setEditora(livroDTOatualizar.getEditora());
        }
        if (livroDTOatualizar.getGenero() != null){
            l.setGenero(livroDTOatualizar.getGenero());
        }
        if (livroDTOatualizar.getPreco() != null){
            l.setPreco(livroDTOatualizar.getPreco());
        }
       livroRepository.save(l);
        return l;
    }

    public void deletarLivro(Long id){
        if( !livroRepository.existsById(id)){
            throw new RuntimeException("Id não encontrado");
        }
        livroRepository.deleteById(id);
    }

}
