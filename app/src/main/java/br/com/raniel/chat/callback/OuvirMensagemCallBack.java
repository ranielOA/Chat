package br.com.raniel.chat.callback;

import org.greenrobot.eventbus.EventBus;

import br.com.raniel.chat.activity.MainActivity;
import br.com.raniel.chat.event.MensagemEvent;
import br.com.raniel.chat.model.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagemCallBack implements Callback<Mensagem> {

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if (response.isSuccessful()){
            Mensagem mensagem = response.body();

            EventBus.getDefault().post(new MensagemEvent(mensagem));
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
    }
}
