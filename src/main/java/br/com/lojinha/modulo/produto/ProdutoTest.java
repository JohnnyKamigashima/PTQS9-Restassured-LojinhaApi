package br.com.lojinha.modulo.produto;

import br.com.lojinha.modulo.usuario.Configura;
import br.com.lojinha.modulo.usuario.Usuario;
import br.com.lojinha.pojo.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

@DisplayName("Testes do módulo de Produto")
public class ProdutoTest {
    String caminhoJson = "/Users/johnnykamigashima/odrive/GDrive/Biblioteca/PTQS/ptqs9_LojinhaApi/src/main/resources/produto1.json";
    String configJson = "/Users/johnnykamigashima/odrive/GDrive/Biblioteca/PTQS/ptqs9_LojinhaApi/src/main/resources/config.json";
    ObjectMapper mapper = new ObjectMapper();
    public ProdutoTest() throws IOException {
    }

    public String readJson(String path) throws IOException {
        return new String(readAllBytes(Paths.get(path)));
    }
    Produto produto = mapper.readValue(readJson(caminhoJson), Produto.class);
    Configura config = mapper.readValue(readJson(configJson), Configura.class);
    Usuario usuario = new Usuario();
    @Test
    @Order(1)
    @DisplayName("Cadastrar um produto")
    public void cadastrarUmProduto() throws IOException {

    given()
        .contentType("application/json")
        .header("token", usuario.lerTokenUsuario())
        .body(produto)
            .log().all()
            .when().post(baseURI+config.getBaseProduto())
            .then()
            .statusCode(201)
            .body("data.produtoNome", equalTo(produto.getProdutoNome()));

    }
}
