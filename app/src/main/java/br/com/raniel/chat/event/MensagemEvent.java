package br.com.raniel.chat.event;

import br.com.raniel.chat.model.Mensagem;

public class MensagemEvent {
    public Mensagem mensagem;

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;
    }
}
