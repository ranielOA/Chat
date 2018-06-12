package br.com.raniel.chat.callback;

import br.com.raniel.chat.activity.MainActivity;
import br.com.raniel.chat.model.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagemCallBack implements Callback<Mensagem> {
    private MainActivity activity;

    public OuvirMensagemCallBack(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if (response.isSuccessful()){
            Mensagem mensagem = response.body();

            activity.colocaNaLista(mensagem);
            activity.ouvirMensagem();
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
            activity.ouvirMensagem();
    }
}
