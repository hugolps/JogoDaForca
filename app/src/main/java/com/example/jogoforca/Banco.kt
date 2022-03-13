package com.example.jogoforca

class Banco {
    val palavras: LinkedHashMap<String, String>
    lateinit var sorteada: String

    init {
        this.palavras = LinkedHashMap()
        this.montaPalavras()
        this.sorteio()
    }

    private fun montaPalavras() {
        this.palavras.put("MELANCIA", "Fruta favorita da Magali")
        this.palavras.put("ABACATE", "Fruta favorita da Vovó Juju")
        this.palavras.put("BANANA", "Fruta que macaco gosta")
        this.palavras.put("LARANJA", "Fruta com nome de cor")

    }

    fun sorteio() {
        this.sorteada = this.palavras.keys.random()
    }

    fun palavra(): String {
        return this.sorteada
    }

    fun dica(): String {
        return this.palavras.get(this.sorteada).toString()
    }
}