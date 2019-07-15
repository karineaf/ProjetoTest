package org.generation.brazil.artemis.usuario;
import org.generation.brazil.artemis.ArtemisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArtemisApplication.class,  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // o webenvironment é usado para que nn tenha conflito de portas
public class UsuarioControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(){
        return "http://localhost:" + port;
    }

    @Test
    public void save(){
        Usuario usuario = new Usuario();
        usuario.setNome("João Maria");
        usuario.setEmail("joaomaria@gmail.com");
        usuario.setLogin("joaomaria");
        usuario.setSenha("@JM2019");
        //ResponseEntity<Usuario> postResponse = testRestTemplate.postForEntity(getRootUrl()+ "/usuarios", Usuario.class);
    }

}
