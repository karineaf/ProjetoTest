package org.generation.brazil.artemis.usuario;

import com.github.javafaker.Faker;
import org.generation.brazil.artemis.usuario.Usuario;

import java.util.Locale;

public class UsuarioMock {
  public  static  Usuario getUserMock(){

    Usuario usuario = new Usuario();

    Faker faker = new Faker(new Locale("pt-BR"));

    usuario.setNome(faker.name().firstName());
    usuario.setEmail(faker.internet().emailAddress());
    usuario.setLogin(faker.name().username());
    usuario.setSenha(faker.internet().password());

    return usuario;
  }

}
