package br.com.br1ttm.sistemavendasandroid.services;

import br.com.br1ttm.sistemavendasandroid.entities.Produto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProdutoService {

    @POST("/produtos")
    Call<Produto> cadastrarProduto(@Body Produto produto);
}
