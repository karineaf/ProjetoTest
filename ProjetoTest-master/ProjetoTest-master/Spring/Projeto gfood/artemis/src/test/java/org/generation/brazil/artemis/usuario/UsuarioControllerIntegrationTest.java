package org.generation.brazil.artemis.usuario;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.generation.brazil.artemis.ArtemisApplication;
import org.generation.brazil.artemis.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArtemisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerIntegrationTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @LocalServerPort
  private int port;

  private String getRootUrl(String path) {
    return "http://localhost:" + port + "/api/v1" + path;
  }

  /*private String generateFirstName() {
    String firstName = RandomStringUtils.randomAlphabetic(10);
    return firstName;
  }

  private String generateEmail() {
    String email = RandomStringUtils.randomAlphanumeric(12);
    return email + "@email.com";
  }

  private String generateLogin() {
    String login = RandomStringUtils.randomAlphabetic(10);
    return login;
  }

  private String generatePassword() {
    String password = RandomStringUtils.randomAlphanumeric(10);
    return password;*/

  @Test
  public void testaConsultaDeTodosOsUsuarios(){
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/usuarios/"), HttpMethod.GET, entity, String.class);
    assertNotNull(response.getBody());
    assertEquals(200, response.getStatusCodeValue());
  }

  @Test
  public void testGetUserById() {
    Usuario usuario = testRestTemplate.getForObject(getRootUrl("/usuarios/1"), Usuario.class);
    System.out.println(usuario.getNome());
    assertNotNull(usuario);
  }

  @Test
  public void testCreateUser(){
    // Chamada da API
    ResponseEntity<Usuario> postResponse = testRestTemplate.postForEntity(getRootUrl("/usuarios/"), UsuarioMock.getUserMock(), Usuario.class);

    assertNotNull(postResponse);
    assertEquals(201, postResponse.getStatusCodeValue());
  }

  @Test
  public void testUpdatePost() {
    int id = 1;
    Usuario updatedUsuario = testRestTemplate.getForObject(getRootUrl("/usuarios/1"), Usuario.class);
    testRestTemplate.put(getRootUrl("/usuarios/1"), UsuarioMock.getUserMock(), Usuario.class);
    assertNotNull(updatedUsuario);
  }

  @Test
  public void testDeletePost() {
    int id = 2;
    Usuario usuario = testRestTemplate.getForObject(getRootUrl("/usuarios/1"), Usuario.class);
    assertNotNull(usuario);
    testRestTemplate.delete(getRootUrl("/usuarios/1"));

    try {
      usuario = testRestTemplate.getForObject(getRootUrl("/usuarios/1"), Usuario.class);
    } catch (final HttpClientErrorException e) {
      assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
    }
  }

}
