package br.com.raniel.chat.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Mensagem implements Parcelable{
    @SerializedName("text") //setando o nome do atributo como ele vem do servidor no Json
    private String texto;
    private int id;

    public Mensagem(String texto, int idDoUsuario) {
        this.texto = texto;
        this.id = idDoUsuario;
    }

    protected Mensagem(Parcel in) {
        texto = in.readString();
        id = in.readInt();
    }

    public static final Creator<Mensagem> CREATOR = new Creator<Mensagem>() {
        @Override
        public Mensagem createFromParcel(Parcel in) {
            return new Mensagem(in);
        }

        @Override
        public Mensagem[] newArray(int size) {
            return new Mensagem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(texto);
        dest.writeInt(id);
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }
}
