package br.com.raniel.chat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import br.com.raniel.chat.R;
import br.com.raniel.chat.adapter.MensagemAdapter;
import br.com.raniel.chat.model.Mensagem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvConversa = findViewById(R.id.main_listview_conversa);

        List<Mensagem> mensagens = Arrays.asList(new Mensagem("ola, tudo bem?", 1), new Mensagem("sim, de boas", 2));

        MensagemAdapter mensagemAdapter = new MensagemAdapter(this, mensagens, 2);

        lvConversa.setAdapter(mensagemAdapter);
    }
}
