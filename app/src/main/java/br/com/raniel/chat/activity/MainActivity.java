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
import br.com.raniel.chat.model.Mensagem;
import br.com.raniel.chat.service.ChatService;

public class MainActivity extends AppCompatActivity {

    private EditText campoConteudoMensagem;
    private Button botaoEnviar;
    private int idDoUsuario = 1;
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

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ChatService(MainActivity.this).enviar(new Mensagem(campoConteudoMensagem.getText().toString(), idDoUsuario));
            }
        });

        chatService = new ChatService(this);
        chatService.ouvirMensagens();       //ouve as mensagens do servidor, a execução dessa thread fica travada no servidor até receber resposta
    }

    public void colocaNaLista(Mensagem mensagem) {
        mensagens.add(mensagem);
        MensagemAdapter adapter = new MensagemAdapter(this, mensagens, idDoUsuario);
        lvListaDeMensagens.setAdapter(adapter);

        chatService.ouvirMensagens();   //coloca a thread para ficar escutando o servidor novamente
    }
}
