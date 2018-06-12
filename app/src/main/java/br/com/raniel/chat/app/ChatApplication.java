package br.com.raniel.chat.app;

import android.app.Application;

import br.com.raniel.chat.component.ChatComponent;
import br.com.raniel.chat.component.DaggerChatComponent;

public class ChatApplication extends Application {

    private ChatComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent.builder().build();
    }

    public ChatComponent getComponent(){

        return component;
    }
}
