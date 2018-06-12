package br.com.raniel.chat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.raniel.chat.R;
import br.com.raniel.chat.model.Mensagem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MensagemAdapter extends BaseAdapter {
    private final Context context;
    private List<Mensagem> mensagens;
    private int idDoUsuario;

    @BindView(R.id.item_mensagem_conteudo_mensagem)
    TextView txtmensagem;
    @BindView(R.id.item_mensagem_avatar)
    ImageView avatar;

    public MensagemAdapter(Context context, List<Mensagem> mensagens, int idDoUsuario) {
        this.context = context;
        this.mensagens = mensagens;
        this.idDoUsuario = idDoUsuario;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Object getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewMensagem = LayoutInflater.from(context).inflate(R.layout.item_mensagem, parent, false);

        ButterKnife.bind(this, viewMensagem);

        Mensagem mensagem = mensagens.get(position);

        if (idDoUsuario != mensagem.getId()) {
            viewMensagem.setBackgroundColor(Color.CYAN);
        }

        txtmensagem.setText(mensagem.getTexto());
        Picasso.get().load("https://api.adorable.io/avatars/285/" + mensagem.getId() + ".png").into(avatar);

        return viewMensagem;
    }
}
