package com.example.apponda;

public class Doacao {
    private int imagemProduto;
    private String nomeProduto;
    private String localDoacao;
    private String validadeProduto;

    public Doacao(int imagemProduto, String nomeProduto, String localDoacao, String validadeProduto) {
        this.imagemProduto = imagemProduto;
        this.nomeProduto = nomeProduto;
        this.localDoacao = localDoacao;
        this.validadeProduto = validadeProduto;
    }

    public int getImagemProduto() {
        return imagemProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getLocalDoacao() {
        return localDoacao;
    }

    public String getValidadeProduto() {
        return validadeProduto;
    }
}
