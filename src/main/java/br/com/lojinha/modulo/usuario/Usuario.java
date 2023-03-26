package br.com.lojinha.modulo.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class Usuario {
    private String usuarioNome;

    private String usuarioLogin;
    private String usuarioSenha;

    public String lerTokenUsuario() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String configPath = "/Users/johnnykamigashima/odrive/GDrive/Biblioteca/PTQS/ptqs9_LojinhaApi/src/main/resources/config.json";
        String usuarioPath = "/Users/johnnykamigashima/odrive/GDrive/Biblioteca/PTQS/ptqs9_LojinhaApi/src/main/resources/usuario1.json";
        String jsonConfigBody = new String(Files.readAllBytes(Paths.get(configPath)));
        String jsonUsuarioBody = new String(Files.readAllBytes(Paths.get(usuarioPath)));

        Usuario usuario = mapper.readValue(jsonUsuarioBody, Usuario.class);
        Configura config = mapper.readValue(jsonConfigBody, Configura.class);

        baseURI = config.getBaseURI();
        basePath = config.getBaseLogin();

        String response =
                given()
                        .contentType(ContentType.JSON)
                        .body(usuario)
                        .log().all()
                        .when()
                        .post(baseURI+basePath)
                        .then()
                        .extract()
                        .path("data.token");
        return response;
    }
    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getUsuarioSenha() {
        return usuarioSenha;
    }

    public void setUsuarioSenha(String usuarioSenha) {
        this.usuarioSenha = usuarioSenha;
    }


    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }
}
