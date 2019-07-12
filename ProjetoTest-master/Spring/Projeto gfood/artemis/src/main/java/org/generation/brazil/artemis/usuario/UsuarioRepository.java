package org.generation.brazil.artemis.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNome(String nome);

    List<Usuario> findByEmail(Email email);

    List<Usuario> findByLogin(String login);

    //List<Usuario> findByPrecoBetween(BigDecimal precinho, BigDecimal precao);


}
