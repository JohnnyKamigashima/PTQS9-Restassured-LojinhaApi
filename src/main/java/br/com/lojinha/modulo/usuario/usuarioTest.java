package br.com.lojinha.modulo.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static java.nio.file.Files.readAllBytes;

public class usuarioTest {
    public usuarioTest() throws IOException {
    }

    public String lerJson(String path) throws IOException {
        return new String(readAllBytes(Paths.get(path)));
    }

    ObjectMapper mapper = new ObjectMapper();
    String caminhoJson = "/Users/johnnykamigashima/odrive/GDrive/Biblioteca/PTQS/ptqs9_LojinhaApi/src/main/resources/usuario1.json";
    Usuario usuario = mapper.readValue(lerJson(caminhoJson), Usuario.class);

    @Test
    @Order(1)
    @DisplayName("Criar um novo usuário")
    public void criarUsuario() {
        baseURI = "http://165.227.93.41/lojinha";
        basePath = "/v2/usuarios";

    given()
        .contentType(ContentType.JSON)
        .body(usuario)
            .log().all()
    .when().post(baseURI+basePath)
        .then()
        .statusCode(409)
            .log().all()
        .body("error", equalTo("O usuário " + usuario.getUsuarioLogin() + " já existe."));
    }




}
