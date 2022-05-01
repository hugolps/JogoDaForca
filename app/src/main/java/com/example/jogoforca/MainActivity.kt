package com.example.jogoforca

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View
import android.util.Log
import android.widget.Toast


//import com.example.jogoforca.Banco
//import com.example.jogoforca.Jogo

class MainActivity : AppCompatActivity() {


    private lateinit var tvLayout: TextView
    private lateinit var banco: Banco
    private lateinit var jogo: Jogo
    private lateinit var tvDica: TextView
    private lateinit var etLetra: EditText
    private lateinit var btJogar: Button
    private lateinit var tvLetrasTentadas: TextView
    private lateinit var tvChances: TextView
    private lateinit var tvStatus: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.banco = Banco()
        this.jogo = Jogo(this.banco.palavra(), this.banco.dica())

        this.tvLayout = findViewById(R.id.tvLayout)
        this.tvDica = findViewById(R.id.tvDica)
        this.etLetra = findViewById(R.id.etLetra)
        this.btJogar = findViewById(R.id.btJogar)
        this.tvLetrasTentadas = findViewById(R.id.tvLetrasTentadas)
        this.tvChances = findViewById(R.id.tvChances)
        this.tvStatus = findViewById(R.id.tvStatus)

        this.tvLayout.text = this.jogo.printSecretWord()
        this.tvDica.text = this.jogo.askHint()

        this.btJogar.setOnClickListener(ButtonClick())

    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun fimDoJogo() {
        val status = this@MainActivity.jogo.status()
        if(status == "VITÓRIA") {
            val intent = Intent(this, EndGameActivity::class.java).apply {
                putExtra("Resultado", "VITÓRIA")
                putExtra("PalavraCerta", this@MainActivity.jogo.printSecretWord())
            }
            if(intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
                }
        } else {
            val intent = Intent(this, EndGameActivity::class.java).apply {
                putExtra("Resultado", "DERROTA")
                putExtra("PalavraCerta", this@MainActivity.jogo.printSecretWord())
            }
            if(intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
                }
            }
        }

    @SuppressLint("SetTextI18n")
    private fun updateView() {
        this@MainActivity.etLetra.setText("")
        this@MainActivity.tvLayout.text = "PALAVRA SECRETA:       " + this@MainActivity.jogo.printSecretWord()
        this@MainActivity.tvLetrasTentadas.text  = "Letras tentada: " + this@MainActivity.jogo.usedLetters().toString()
        this@MainActivity.tvChances.text = "Chances restantes: " + this@MainActivity.jogo.chances()
        this@MainActivity.tvStatus = findViewById(R.id.tvStatus)

    }


    inner class ButtonClick: View.OnClickListener{

        override fun onClick(p0: View?) {
//             Log.i("APP_FORCA", "teste 1"+ this@MainActivity.jogo.status())

             val letter = this@MainActivity.etLetra.text.toString().uppercase()
             if (letter.length == 1) {
                 if (!this@MainActivity.jogo.usedLetters().contains(letter)) {

                     this@MainActivity.jogo.askLetter(letter)
//                     Log.i("APP_FORCA", "teste 2"+ this@MainActivity.jogo.status())

                 } else {
                     Toast.makeText(this@MainActivity, "Letra já utlizada", Toast.LENGTH_SHORT).show()
                 }
             } else {
                    Toast.makeText(this@MainActivity, "Jogada Inválida", Toast.LENGTH_SHORT).show()
                 }

             updateView()

             if (this@MainActivity.jogo.endGame) {
                 fimDoJogo()
             }
         }

    }
}