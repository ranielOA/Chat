package br.com.raniel.chat.app;

import android.app.Application;

import br.com.raniel.chat.component.ChatComponent;
import br.com.raniel.chat.component.DaggerChatComponent;
import br.com.raniel.chat.module.ChatModule;

public class ChatApplication extends Application {

    private ChatComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent.builder()
                                        .chatModule(new ChatModule(this))
                                        .build();
    }

    public ChatComponent getComponent(){

        return component;
    }
}
