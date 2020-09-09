package br.com.br1ttm.sistemavendasandroid;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.br1ttm.sistemavendasandroid.entities.Produto;
import br.com.br1ttm.sistemavendasandroid.entities.Usuario;
import br.com.br1ttm.sistemavendasandroid.services.ProdutoService;
import br.com.br1ttm.sistemavendasandroid.services.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciar;
    private Button btnTestarID;
    private EditText edtID;
    private EditText edtNome;
    private EditText edtDescricao;
    private EditText edtValorUn;
    private EditText edtQtdeEstoque;
    private EditText edtImgUrl;
    private TextView txtResultado;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIniciar);
        btnTestarID = findViewById(R.id.btnTestarID);
        txtResultado = findViewById(R.id.txtResultado);
        edtID = findViewById(R.id.edtID);
        edtNome = findViewById(R.id.edtNome);
        edtDescricao = findViewById(R.id.edtDescricao);
        edtValorUn = findViewById(R.id.edtValorUn);
        edtQtdeEstoque = findViewById(R.id.edtQtdeEstoque);
        edtImgUrl = findViewById(R.id.edtImgUrl);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://back-sistema-vendas.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void testarWebService(View view){
        UsuarioService usuarioService = retrofit.create(UsuarioService.class);
        Call<List<Usuario>> call = usuarioService.retornaUsuario();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    List<Usuario> usuarios = response.body();
                   int cont = 0;
                   StringBuilder result = new StringBuilder();
                    assert usuarios != null;
                    while (cont < usuarios.size()){
                       result.append("Nome: ")
                               .append(usuarios.get(cont).getNomeCompleto())
                               .append("\nEmail: ")
                               .append(usuarios.get(cont).getEmail())
                               .append("\nNome de Usuário: ")
                               .append(usuarios.get(cont).getNomeUsuario())
                               .append("\nTelefone: ")
                               .append(usuarios.get(cont).getTelefone())
                               .append("\n--------------------------------\n");
                       cont++;
                   }
                   txtResultado.setText(result.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("ERRO", t.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtResultado.setText("DEU RUIM");
                    }
                });
            }


        });
    }

    public void testarWebServiceComID(View view){
        UsuarioService usuarioService = retrofit.create(UsuarioService.class);
        Call<Usuario> call = usuarioService.retornaUsuarioId(edtID.getText().toString());
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario usuario = response.body();
                    txtResultado.setText(new StringBuilder()
                            .append("Nome: ")
                            .append(usuario.getNomeCompleto())
                            .append("\nEmail: ")
                            .append(usuario.getEmail())
                            .append("\nNome de Usuário: ")
                            .append(usuario.getNomeUsuario())
                            .append("\nTelefone: ")
                            .append(usuario.getTelefone())
                            .append("\n--------------------------------\n").toString());

                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("ERRO", t.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtResultado.setText("DEU RUIM");
                    }
                });
            }
        });
    }

    public void cadastrarProduto(View view){
        final Produto produto = new Produto(edtNome.getText().toString(),
                edtDescricao.getText().toString(),
                Double.valueOf(edtValorUn.getText().toString()),
                Integer.parseInt(edtQtdeEstoque.getText().toString()),
                edtImgUrl.getText().toString());

        ProdutoService produtoService = retrofit.create(ProdutoService.class);
        Call<Produto> call = produtoService.cadastrarProduto(produto);
        call.enqueue(new Callback<Produto>() {
            @Override
            public void onResponse(Call<Produto> call, Response<Produto> response) {
                if(response.isSuccessful()){
                    Produto prod = response.body();
                    txtResultado.setText("Código: " + response.code() + "\nid: "  + prod.getId());
                }else{
                    txtResultado.setText(response.toString());
                }
            }

            @Override
            public void onFailure(Call<Produto> call, Throwable t) {
                Log.e("ERRO", t.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtResultado.setText("DEU RUIM");
                    }
                });
            }
        });
    }

}