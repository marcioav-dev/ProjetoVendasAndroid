package br.com.br1ttm.sistemavendasandroid.entities;

public class Produto {
    private Long id;
    private String nome;
    private String descricao;
    private Double valorUn;
    private int qtdeEstoque;
    private String imgUrl;

    public Produto(String nome, String descricao, Double valorUn, int qtdeEstoque, String imgUrl) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorUn = valorUn;
        this.qtdeEstoque = qtdeEstoque;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorUn() {
        return valorUn;
    }

    public void setValorUn(Double valorUn) {
        this.valorUn = valorUn;
    }

    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
