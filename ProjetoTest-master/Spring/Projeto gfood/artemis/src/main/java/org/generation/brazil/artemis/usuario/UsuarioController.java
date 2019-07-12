package org.generation.brazil.artemis.usuario;
import org.generation.brazil.artemis.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    @Autowired // para nn ter o nullpointer
    private UsuarioRepository repository;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/usuarios")
    public Usuario save(@RequestBody Usuario usuario){ //@RequestBody converte o q foi feito no jason para um objeto da classe cliente
        //INSERT INTO cliente ... Value ...
        return repository.save(usuario);
    }

    @GetMapping("/usuarios")
    public List<Usuario> findAll(){
        //SELECT * FROM cliente
        return repository.findAll();
    }

    @PutMapping("/usuarios/{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario)
    // "UPDATE cliente SET ... WHERE ..."
            throws ResourceNotFoundException {
        return repository.findById(id).map(u -> {
            u.setNome(usuario.getNome());
            u.setEmail(usuario.getEmail());
            u.setLogin(usuario.getLogin());
            u.setSenha(usuario.getSenha());
            return repository.save(u);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não existe cliente cadastrado com o id: " + id));
    }

    @DeleteMapping("/usuarios/{id}")
    public void delete(@PathVariable Long id){  //o @pathvaruable informa a variável q está no caminho e será "manipulada"
        //DELETE FROM cliente WHERE id="..."
        repository.deleteById(id);
    }



}
