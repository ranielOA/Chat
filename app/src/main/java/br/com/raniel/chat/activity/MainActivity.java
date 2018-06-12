package br.com.raniel.chat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.raniel.chat.R;
import br.com.raniel.chat.adapter.MensagemAdapter;
import br.com.raniel.chat.callback.EnviarMensagemCallback;
import br.com.raniel.chat.callback.OuvirMensagemCallBack;
import br.com.raniel.chat.model.Mensagem;
import br.com.raniel.chat.service.ChatService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText campoConteudoMensagem;
    private Button botaoEnviar;
    private int idDoUsuario = 2;
    private ListView lvListaDeMensagens;
    private List<Mensagem> mensagens;
    private ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvListaDeMensagens = findViewById(R.id.main_listview_conversa);
        botaoEnviar = findViewById(R.id.main_enviar);
        campoConteudoMensagem = findViewById(R.id.main_mensagem);

        mensagens = new ArrayList<>();

        MensagemAdapter mensagemAdapter = new MensagemAdapter(this, mensagens, idDoUsuario);

        lvListaDeMensagens.setAdapter(mensagemAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.0.14:8080/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


        chatService = retrofit.create(ChatService.class);
        ouvirMensagem();

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mensagem msg = new Mensagem(campoConteudoMensagem.getText().toString(), idDoUsuario);
                chatService.enviar(msg).enqueue(new EnviarMensagemCallback());
            }
        });
    }

    public void colocaNaLista(Mensagem mensagem) {
        mensagens.add(mensagem);
        MensagemAdapter adapter = new MensagemAdapter(this, mensagens, idDoUsuario);
        lvListaDeMensagens.setAdapter(adapter);
    }

    public void ouvirMensagem() {
        Call<Mensagem> mensagemCall = chatService.ouvirMensagens();
        mensagemCall.enqueue(new OuvirMensagemCallBack(this));
    }
}
