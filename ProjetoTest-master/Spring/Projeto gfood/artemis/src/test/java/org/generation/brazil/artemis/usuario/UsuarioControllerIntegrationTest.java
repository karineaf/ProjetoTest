package org.generation.brazil.artemis.usuario;
import org.apache.commons.lang3.RandomStringUtils;
import org.generation.brazil.artemis.ArtemisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.nio.charset.Charset;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class) // responsavel por fazer a conexão do spring com o JUnit
@SpringBootTest(classes = ArtemisApplication.class,  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // o webenvironment é usado para que nn tenha conflito de portas
public class UsuarioControllerIntegrationTest {

    @Autowired // usado para fazer a injeção da dependencia TestRestTemplate
    private TestRestTemplate testRestTemplate; // usado para "simular" o postman

    @LocalServerPort // ele que vai mudando a porta
    private int port;

    private String getRootUrl(String path){ // informa o endereço na web
        return "http://localhost:" + port + "/api/v1" + path;
    }

    private String generateNameRandom(){
       String generatedString = RandomStringUtils.randomAlphabetic(10);
        return  generatedString;
    }

    private String generateEmailRandom(){
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        return  generatedString +"@gmail.com";
    }

    private String generateLoginRandom(){
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        return  generatedString;
    }

    private String generatePasswordRandom(){
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        return  generatedString;
    }

    @Test
    public void save(){ // testa o C do Crud
        Usuario usuario = new Usuario();
        usuario.setNome(generateNameRandom());
        usuario.setEmail(generateEmailRandom());
        usuario.setLogin(generateLoginRandom());
        usuario.setSenha(generatePasswordRandom());
        ResponseEntity<Usuario> postResponse = testRestTemplate.postForEntity(getRootUrl("/usuarios"), usuario, Usuario.class); // chamada da api
        //responseentity guarda a resposta da api

        assertNotNull(postResponse); //da erro na aplicação se for nulo
        assertEquals(201, postResponse.getStatusCodeValue()); // compara e vê se o expected é == ao código do resultado do postresponse.
    }
}
