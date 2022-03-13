package com.example.jogoforca

class Jogo(val secretWord: String, val hint: String) {

    // Variáveis globais
    val hiddenWord = arrayListOf<String>()
    val usedLetters = mutableListOf<String>()
    var endGame = false
    var chances = 6
    var hits = 0

    // Escondendo a palavra
    init {
        for (i in secretWord) {
            hiddenWord.add("*")
        }
    }

    fun askHint(): String{
        return this.hint
    }

    fun asksecretWord(): String{
        return this.secretWord
    }

    fun size(): String {
        return "A palavra tem ${secretWord.length} letras"
    }

    fun distinctChar(): String {
        var count = 0
        val uniqueLetters = mutableListOf<String>()
        for (letter in secretWord) {
            if (!uniqueLetters.contains(letter.toString())) {
                uniqueLetters.add(letter.toString())
                count++
            }
        }
        return "A palavra tem $count letras distintas"
    }

    // Printar a palavra escondida com e sem acertos
    fun printSecretWord(): String {
        var finalWorld = ""
        for ((i,letter) in hiddenWord.withIndex()) {
            finalWorld += hiddenWord[i]
        }
        return finalWorld
    }

    //Alterar uma letra descoberta
    fun changeLetter(TriedLetter: String, word: String) {
        for ((i, letter) in word.withIndex()) {
            if (TriedLetter == word[i].toString()) {
                hiddenWord[i] = TriedLetter
                hits++
            }
        }
    }

    //Pergutar a letra ao jogador e avaliar o acerto
    fun askLetter(word: String) {
        if (chances > 0 && hits < word.length) {
            println("Informe uma letra: ")
            val chosenLetter = readLine()!!

            if (word.contains(chosenLetter)) {
                changeLetter(chosenLetter, word)
                return
            }
            else if (!usedLetters.contains(chosenLetter) && !word.contains(chosenLetter) && chances > 0) {
                usedLetters.add(chosenLetter)
                println("Letras já tentadas $usedLetters")
                chances--
                return
            } else {
                println("A letra $chosenLetter já foi utilizada. Tente outra letra")
            }

        } else if (chances == 0) {
            println("Você PERDEU!")
            println("FIM DO JOGO")
            endGame = true
            return
        } else if (hits == word.length) {
            println("Você VENCEU!")
            println("FIM DO JOGO")
            endGame = true
            return
        }
    }

}