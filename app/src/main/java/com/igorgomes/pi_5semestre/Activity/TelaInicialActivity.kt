package com.igorgomes.pi_5semestre.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.igorgomes.pi_5semestre.R

class TelaInicialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        // Configura a inserção de padding automaticamente para acomodar as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(
                left = systemBars.left,
                top = systemBars.top,
                right = systemBars.right,
                bottom = systemBars.bottom
            )
            WindowInsetsCompat.CONSUMED
        }

        val entrarDireto: Button = findViewById(R.id.btn_entrar)
        entrarDireto.setOnClickListener{
            val intent = Intent(this@TelaInicialActivity, PrincipalActivity::class.java)
            startActivity(intent)
        }

        // Configura o botão de login
        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val intent = Intent(this@TelaInicialActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        // Configura o botão de cadastro
        val cadastrarButton: Button = findViewById(R.id.CadastrarButton)
        cadastrarButton.setOnClickListener {
            val intent = Intent(this@TelaInicialActivity, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
