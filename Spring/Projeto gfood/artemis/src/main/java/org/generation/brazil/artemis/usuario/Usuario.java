package org.generation.brazil.artemis.usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table (name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = { //unique fala q é único
                "email"
        }),
        @UniqueConstraint(columnNames = {
                "login"
        })
})
public class Usuario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) // p/ que faça o auto incremento
private Long id;

@NotBlank
@NotNull
private String nome;

@NotBlank
@Email
@NotNull
private String email;

@NotBlank
@NotNull
private String login;

@NotBlank
@NotNull
private String senha;
}
