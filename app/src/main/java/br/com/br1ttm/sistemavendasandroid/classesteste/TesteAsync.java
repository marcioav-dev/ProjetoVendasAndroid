package br.com.br1ttm.sistemavendasandroid.classesteste;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.br1ttm.sistemavendasandroid.entities.Usuario;

public class TesteAsync {

    class Tarefa extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String urlApi = strings[0];
            InputStream is = null;
            InputStreamReader isr;
            StringBuffer resposta = null;

            try {
                URL url = new URL(urlApi);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                is = conn.getInputStream();
                isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);
                String line = "";
                resposta = new StringBuffer();
                while ((line = reader.readLine()) != null){
                    resposta.append(line);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resposta.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Usuario usuario = new Usuario();
                JSONObject jsonObject = new JSONObject(s);
                usuario.setNomeCompleto(jsonObject.getString("nomeCompleto"));
                //txtResultado.setText(usuario.getNomeCompleto());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
