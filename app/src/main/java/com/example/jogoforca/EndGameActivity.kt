package com.example.jogoforca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.ImageView


class EndGameActivity : AppCompatActivity() {

    private lateinit var tvResultado: TextView
    private lateinit var btRecomecar: Button
    private lateinit var tvPalavraCerta: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)

        this.tvResultado = findViewById(R.id.tvResultado)
        this.tvPalavraCerta = findViewById(R.id.tvPalavraCerta)


        if(intent.hasExtra("Resultado")) {
            val resultado = intent.getStringExtra("Resultado")
            this.tvPalavraCerta.text = intent.getStringExtra("PalavraCerta")
            if(resultado.equals("DERROTA")) {
                this.tvResultado.text = "DERROTA"
            } else if(resultado.equals("VITÓRIA")){
                this.tvResultado.text = "VITÓRIA"
            }
        }
        this.btRecomecar = findViewById(R.id.btRecomecar)
        this.btRecomecar.setOnClickListener{recomecar()}
    }

    fun recomecar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}