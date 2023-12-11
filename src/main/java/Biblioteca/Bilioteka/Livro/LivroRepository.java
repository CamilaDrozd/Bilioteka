package Biblioteca.Bilioteka.Livro;

import Biblioteca.Bilioteka.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LivroRepository extends JpaRepository<Livro,Long> {

 //   Livro findByLivro(Long id);

    Livro findByTitulo(String titulo);

    @Query(value = "SELECT l FROM Livro l  WHERE l.titulo = :titulo")
    //@Query(value = "SELECT * FROM Livro l  WHERE l.titulo = ?1", nativeQuery = true)
    List<Livro> findTitulos( String titulo);


    List<Livro> findByAutor(String autor);

    List<Livro> findByGenero(String genero);

}
