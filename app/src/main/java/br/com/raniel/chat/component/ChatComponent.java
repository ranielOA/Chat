package br.com.raniel.chat.component;

import br.com.raniel.chat.activity.MainActivity;
import br.com.raniel.chat.module.ChatModule;
import dagger.Component;

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);
}
