package Biblioteca.Bilioteka.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTOatualizar {

    private Long id;
    private String titulo;
    private String autor;
    private String editora;
    private String genero;
    private String preco;
}
