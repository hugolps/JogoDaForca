package com.example.jogoforca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.jogoforca.Banco
import com.example.jogoforca.Jogo

class MainActivity : AppCompatActivity() {

    private lateinit var tvPalavra: TextView
    private  lateinit var banco: Banco
    private lateinit var jogo: Jogo
    private lateinit var tvDica: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.banco = Banco()
        this.jogo = Jogo(this.banco.palavra(), this.banco.dica())

        this.tvPalavra = findViewById(R.id.tvPalavra)
        this.tvPalavra.text = this.jogo.asksecretWord()

        this.tvDica = findViewById(R.id.tvDica)
        this.tvDica.text = this.jogo.askHint()

    }
}