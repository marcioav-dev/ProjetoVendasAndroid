package br.com.br1ttm.sistemavendasandroid.services;

import java.util.List;

import br.com.br1ttm.sistemavendasandroid.entities.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuarioService {

    @GET("usuarios")
    Call<List<Usuario>> retornaUsuario();

    @GET("usuarios/{id}")
    Call<Usuario> retornaUsuarioId(@Path("id") String id);
}
