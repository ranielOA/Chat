package br.com.raniel.chat.model;

public class Mensagem {
    private String texto;
    private int idDoUsuario;

    public Mensagem(String texto, int idDoUsuario) {
        this.texto = texto;
        this.idDoUsuario = idDoUsuario;
    }


    public String getTexto() {
        return texto;
    }

    public int getIdDoUsuario() {
        return idDoUsuario;
    }
}
