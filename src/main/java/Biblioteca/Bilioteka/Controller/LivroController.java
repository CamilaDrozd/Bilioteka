package Biblioteca.Bilioteka.Controller;

import Biblioteca.Bilioteka.DTO.LivroDTO;
import Biblioteca.Bilioteka.DTO.LivroDTOatualizar;
import Biblioteca.Bilioteka.DTO.LivroDTOid;
import Biblioteca.Bilioteka.model.Livro;
import Biblioteca.Bilioteka.Livro.LivroRepository;
import Biblioteca.Bilioteka.service.BibliotecaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class LivroController {

    @Autowired
    private BibliotecaService bibliotecaService;
    @Autowired
    private LivroRepository livroRepository;


    @PostMapping("/livro")
    public Livro cadastrarLivro(@RequestBody Livro livro){
        return bibliotecaService.cadastraLivro(livro);
    }


    @GetMapping("/livros")
    public List<LivroDTOid> listarLivrosDTO(){
        return bibliotecaService.listarLivrosDTO();
       // return livroRepository.findAll();
    }

    @GetMapping("/livro/{id}")
    public LivroDTO buscarLivroID(@PathVariable(value = "id") Long id){
       return bibliotecaService.buscarIdDTO(id);
    }


    @GetMapping("/livroTitulo/{titulo}")
    public LivroDTO buscarTitulo(@PathVariable(value = "titulo") String titulo){
        LivroDTO listaTitulo = bibliotecaService.buscarTitulo(titulo);
        return listaTitulo;
    }

    @GetMapping("/livros/{titulo}")
    public List<LivroDTO> buscarTitulos(@PathVariable(value = "titulo") String titulo){
        List<LivroDTO> listaTitulos = bibliotecaService.buscarTitulos(titulo);
        return listaTitulos;
    }


    @GetMapping("/livroAutor/{autor}")
    public List<LivroDTO> buscarAutor (@PathVariable(value = "autor") String autor){
        List<LivroDTO> listaAutor = bibliotecaService.buscarAutor(autor);
        return listaAutor;
    }

    @GetMapping("/livroGenero/{genero}")
    public List<LivroDTO> buscarGenero(@PathVariable(value = "genero") String genero){
        List<LivroDTO> listaGenero = bibliotecaService.buscarGenero(genero);
        return listaGenero;
    }




    @PutMapping("/livro/{id}")
    @Transactional
    public Livro atualizarLivro(@PathVariable Long id, @RequestBody LivroDTOatualizar livroDTOatualizar){
        //LivroDTOatualizar livroAtualiza = bibliotecaService.atualizarLivro(livroDTOatualizar);
        return bibliotecaService.atualizarLivro(livroDTOatualizar);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public void deletarLivro(@PathVariable Long id){
        bibliotecaService.deletarLivro(id);
    }



//    @GetMapping("/livro/venda")
//    public List<LivroVendaDTO> buscarLivroVenda(){
//        return bibliotecaService.listarLivrosVenda();
//    }

}
