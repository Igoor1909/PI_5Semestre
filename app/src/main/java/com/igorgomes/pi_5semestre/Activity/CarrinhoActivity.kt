package com.igorgomes.pi_5semestre.Activity

import ItemCarrinhoAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.igorgomes.pi_5semestre.R
import com.igorgomes.pi_5semestre.Responses.ProdutoResponse


class CarrinhoActivity : AppCompatActivity() {

    private lateinit var recyclerViewCarrinho: RecyclerView
    private lateinit var itemCarrinhoAdapter: ItemCarrinhoAdapter
    private lateinit var btnHome: ImageView
    private lateinit var btnCarrinho: ImageView
    private val listaProdutosCarrinho = mutableListOf<ProdutoResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrinho)


        // Inicializando o RecyclerView e o Adapter
        recyclerViewCarrinho = findViewById(R.id.recyclerViewCarrinho)
        itemCarrinhoAdapter = ItemCarrinhoAdapter(listaProdutosCarrinho)

        recyclerViewCarrinho.apply {
            layoutManager = LinearLayoutManager(this@CarrinhoActivity)
            adapter = itemCarrinhoAdapter
        }

        try {
            // Receber os dados do Intent
            val produtoId = intent.getIntExtra("produto_id", -1)
            val produtoNome = intent.getStringExtra("produto_nome") ?: ""
            val produtoPreco = intent.getDoubleExtra("produto_preco", 0.0)
            val produtoQuantidade = intent.getIntExtra("produto_quantidade", 1)

            // Verificar se o produto é válido e adicionar ao carrinho
            if (produtoId != -1) {
                val produto = ProdutoResponse(
                    id = produtoId,
                    nome = produtoNome,
                    preco = produtoPreco,
                    quantidade = produtoQuantidade
                )
                // Usar o método adicionarItem do adapter
                itemCarrinhoAdapter.adicionarItem(produto)
            }
        } catch (e: Exception) {
            e.printStackTrace() // Exibe o erro no log
            Toast.makeText(this, "Erro ao adicionar o produto ao carrinho.", Toast.LENGTH_SHORT)
                .show()
        }

        btnHome = findViewById(R.id.btn_home)
        btnHome.setOnClickListener{
            val telaInicial = Intent(this, PrincipalActivity::class.java)

            startActivity(telaInicial)
        }

        btnCarrinho = findViewById(R.id.btn_home)
        btnCarrinho.setOnClickListener{
            val telaCarrinho = Intent(this, CarrinhoActivity::class.java)
            startActivity(telaCarrinho)
        }


    }

}


