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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eng_game)

        this.tvResultado = findViewById(R.id.tvResultado)


        if(intent.hasExtra("Resultado")) {
            val resultado = intent.getStringExtra("Resultado")
            if(resultado.equals("DERROTA")) {
                this.tvResultado.text = "DERROTA"
            } else if(resultado.equals("VITÓRIA")){
                this.tvResultado.text = "VITÓRIA"
            }
        }
        Log.i("APP_FORCA", "teste X")
        this.btRecomecar = findViewById(R.id.btRecomecar)
        Log.i("APP_FORCA", "teste Y")
        this.btRecomecar.setOnClickListener{recomecar()}
        Log.i("APP_FORCA", "teste Z")
    }

    fun recomecar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}