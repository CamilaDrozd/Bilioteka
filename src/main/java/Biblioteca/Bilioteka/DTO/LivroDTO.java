package Biblioteca.Bilioteka.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO {
    private String titulo;
    private String autor;
    private String editora;
    private String genero;


}
