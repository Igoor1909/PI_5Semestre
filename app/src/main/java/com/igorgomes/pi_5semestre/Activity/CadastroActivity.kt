package com.igorgomes.pi_5semestre.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import com.google.gson.Gson
import com.igorgomes.pi_5semestre.R

class CadastroActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var nomeEditText: EditText
    private lateinit var cadastrarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        emailEditText = findViewById(R.id.emailCadastroText)
        passwordEditText = findViewById(R.id.passwordCadastroText)
        nomeEditText = findViewById(R.id.emailCadastroText2)
        cadastrarButton = findViewById(R.id.CadastrarButton)

        cadastrarButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val nome = nomeEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty() || nome.isEmpty()) {
            Toast.makeText(this, "Todos os campos devem ser preenchidos.", Toast.LENGTH_LONG).show()
            return
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Formato de email inválido.", Toast.LENGTH_LONG).show()
            return
        }

        cadastrarButton.isEnabled = false

        val retrofit = Retrofit.Builder()
            .baseUrl("https://638a8ba8-2e76-4761-836a-37ea66c293fb-00-di276691jjx.worf.replit.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(CadastroService::class.java)

        val cadastroRequest = CadastroRequest(email, nome, password, "register")

        Log.d("CadastroRequest", "JSON enviado: ${Gson().toJson(cadastroRequest)}")

        val call = apiService.cadastrar(cadastroRequest)

        call.enqueue(object : Callback<CadastroResponse> {
            override fun onResponse(call: Call<CadastroResponse>, response: Response<CadastroResponse>) {
                cadastrarButton.isEnabled = true

                if (response.isSuccessful) {
                    Toast.makeText(this@CadastroActivity, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@CadastroActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Erro no cadastro. Tente novamente."
                    Toast.makeText(this@CadastroActivity, errorMessage, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<CadastroResponse>, t: Throwable) {
                cadastrarButton.isEnabled = true
                Toast.makeText(this@CadastroActivity, "Erro: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    data class CadastroRequest(val email: String, val nome: String, val password: String, val action: String)

    data class CadastroResponse(val message: String)

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    interface CadastroService {
        @POST("register.php")
        fun cadastrar(@Body cadastroRequest: CadastroRequest): Call<CadastroResponse>
    }
}
