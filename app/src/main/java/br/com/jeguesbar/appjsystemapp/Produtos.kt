package br.com.jeguesbar.appjsystemapp

class Produtos {

    var id:Long = 0
    var nome = ""
    var grupo = ""
    var categoria = ""
    var valor_unitario:Double = 0.0

    override fun toString(): String {
        return "Produtos: $nome"
    }
}