package br.com.raniel.chat.model;

import com.google.gson.annotations.SerializedName;

public class Mensagem {
    @SerializedName("text") //setando o nome do atributo como ele vem do servidor no Json
    private String texto;
    private int id;

    public Mensagem(String texto, int idDoUsuario) {
        this.texto = texto;
        this.id = idDoUsuario;
    }


    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }
}
