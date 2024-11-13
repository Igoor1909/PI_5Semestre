package com.igorgomes.pi_5semestre.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.igorgomes.pi_5semestre.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            blockLogin()
        }
    }

    private fun blockLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Usuário e senha não podem ser vazios.", Toast.LENGTH_LONG).show()
            return
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Formato de email inválido.", Toast.LENGTH_LONG).show()
            return
        }

        loginButton.isEnabled = false

        val retrofit = Retrofit.Builder()
            .baseUrl("https://638a8ba8-2e76-4761-836a-37ea66c293fb-00-di276691jjx.worf.replit.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val loginRequest = mapOf(
            "action" to "login",
            "email" to email,
            "password" to password
        )

        Log.d("LoginActivity", "Fazendo requisição de login: $loginRequest")

        val call = apiService.login(loginRequest)

        Toast.makeText(this, "Carregando...", Toast.LENGTH_SHORT).show()

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginButton.isEnabled = true
                Log.d("LoginActivity", "Código de resposta: ${response.code()}")

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        Toast.makeText(this@LoginActivity, "Bem-vindo, ${loginResponse.usuarioNome}!", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@LoginActivity, PrincipalActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Erro desconhecido. Tente novamente.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Email ou senha incorretos."
                    Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_LONG).show()
                    Log.e("LoginError", "Erro no login: $errorMessage")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginButton.isEnabled = true
                val errorMsg = if (t is IOException) {
                    "Verifique sua conexão com a internet."
                } else {
                    "Erro: ${t.message}"
                }
                Toast.makeText(this@LoginActivity, errorMsg, Toast.LENGTH_LONG).show()
                Log.e("LoginFailure", "Falha no login", t)
            }
        })
    }


    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    data class LoginResponse(val usuarioNome: String, val usuarioEmail: String)

    interface ApiService {
        @POST("login.php")
        fun login(@Body loginRequest: Map<String, String>): Call<LoginResponse>
    }
}