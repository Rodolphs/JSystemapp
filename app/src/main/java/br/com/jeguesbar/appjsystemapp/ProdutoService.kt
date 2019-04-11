package br.com.jeguesbar.appjsystemapp

import android.content.Context

object ProdutoService {

    fun getProdutos(context: Context) : List<Produtos> {

        val produtos = mutableListOf<Produtos>()
        for (i in 1..10) {
            val p = Produtos()
            p.nome = "Produtos $i"
            p.grupo = "Grupo $i"
            p.categoria = "Categoria $i"
            p.valor_unitario = 10.50
            produtos.add(p)

        }
        return produtos

    }

}