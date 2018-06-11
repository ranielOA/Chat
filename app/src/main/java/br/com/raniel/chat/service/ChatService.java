package br.com.raniel.chat.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import br.com.raniel.chat.activity.MainActivity;
import br.com.raniel.chat.model.Mensagem;

public class ChatService {
    private MainActivity activity;

    public ChatService(MainActivity activity) {
        this.activity = activity;
    }

    public void enviar(final Mensagem mensagem) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String texto = mensagem.getTexto();

                try {
                    URL url = new URL("http://192.168.0.14:8080/polling");

                    HttpURLConnection httpConection = (HttpURLConnection) url.openConnection();

                    httpConection.setRequestMethod("POST");
                    httpConection.setRequestProperty("Content-type", "application/json");

                    JSONStringer json = new JSONStringer()
                            .object()
                            .key("text")
                            .value(texto)
                            .key("id")
                            .value(mensagem.getIdDoUsuario())
                            .endObject();

                    OutputStream saida = httpConection.getOutputStream();
                    PrintStream ps = new PrintStream(saida);

                    ps.println(json.toString());

                    httpConection.connect();
                    httpConection.getInputStream();

                } catch (Exception e) {
                    throw  new RuntimeException(e);
                }
            }
        }).start();
    }

    public void ouvirMensagens(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://192.168.0.14:8080/polling");

                    HttpURLConnection httpConection = (HttpURLConnection) url.openConnection();

                    httpConection.setRequestMethod("GET");
                    httpConection.setRequestProperty("Accept", "application/json");

                    httpConection.connect();

                    Scanner scanner = new Scanner(httpConection.getInputStream());
                    StringBuilder stringBuilder = new StringBuilder();

                    while (scanner.hasNextLine()){
                        stringBuilder.append(scanner.nextLine());
                    }

                    String json = stringBuilder.toString();

                    JSONObject jsonObject = new JSONObject(json);
                    final Mensagem mensagem = new Mensagem(jsonObject.getString("text"), jsonObject.getInt("id"));

                    activity.runOnUiThread(new Runnable() {             //precisa rodar na Thread principal porque meche com um componente visual, no caso o listView
                        @Override
                        public void run() {
                            activity.colocaNaLista(mensagem);
                        }
                    });

                } catch (Exception e) {
                    throw  new RuntimeException(e);
                }
            }
        }).start();
    }
}
