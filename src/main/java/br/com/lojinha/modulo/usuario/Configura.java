package br.com.lojinha.modulo.usuario;

public class Configura {
    public String getBaseURI() {
        return baseURI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public String getBaseUsuario() {
        return baseUsuario;
    }

    public void setBaseUsuario(String baseUsuario) {
        this.baseUsuario = baseUsuario;
    }

    public String getBaseProduto() {
        return baseProduto;
    }

    public void setBaseProduto(String baseProduto) {
        this.baseProduto = baseProduto;
    }

    public String getBaseLogin() {
        return baseLogin;
    }

    public void setBaseLogin(String baseLogin) {
        this.baseLogin = baseLogin;
    }

    private String baseURI;
    private String baseUsuario;
    private String baseProduto;
    private String baseLogin;
}
