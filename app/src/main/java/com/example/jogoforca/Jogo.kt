package com.example.jogoforca

class Jogo(val secretWord: String, val hint: String) {

    // Variáveis globais
    val hiddenWord = arrayListOf<String>()
    val usedLetters = mutableListOf<String>()
    var endGame = false
    var chances = 6
    var hits = 0
    var status = ""

    // Escondendo a palavra
    init {
        for (i in secretWord) {
            hiddenWord.add("*")
        }
    }

    fun askHint(): String{
        return this.hint
    }

    fun usedLetters(): MutableList<String>{
        return usedLetters
    }

    fun chances(): String{
        return chances.toString()
    }

    fun status(): String{
        return status
    }

    fun asksecretWord(): String{
        val word = printSecretWord()
        return word
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
    fun askLetter(chosenLetter: String) {
        val word = secretWord

        if (hits == word.length) {
            status = "Vitória"
            endGame = true
            return
        }

        if (chances == 0) {
            status = "DERROTA"
            endGame = true
            return
        }

        if (chances > 0 && hits < word.length) {

            if (word.contains(chosenLetter)) {
                changeLetter(chosenLetter, word)
                return
            }
            else if (!usedLetters.contains(chosenLetter) && !word.contains(chosenLetter) && chances > 0) {
                usedLetters.add(chosenLetter)
                chances--
                return
            }
        }
    }

}