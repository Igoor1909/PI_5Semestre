package com.igorgomes.pi_5semestre.Activity


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.igorgomes.pi_5semestre.Adapter.ProdutoAdapter
import com.igorgomes.pi_5semestre.Model.ItemCartModel
import com.igorgomes.pi_5semestre.Model.ProdutoModel
import com.igorgomes.pi_5semestre.R
import com.igorgomes.pi_5semestre.databinding.ActivityPrincipalBinding


class PrincipalActivity : AppCompatActivity() {

    lateinit var principalbinding: ActivityPrincipalBinding
    private val carrinhoProdutos = mutableListOf<ItemCartModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        principalbinding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(principalbinding.root)

        //loadCarrinho()

        principalbinding.RecyclerViewProduto.layoutManager = GridLayoutManager(this, 2)

        val listaProdutos = listOf(
            ProdutoModel(1, "Vodka Absolut", 119.90, R.drawable.absolute_pequeno),
            ProdutoModel(2, "Gin Tanqueray", 109.90, R.drawable.tanqueray)
        )

        val adapter = ProdutoAdapter(listaProdutos) { produto ->
            val item = ItemCartModel(produto.idItem, produto.nome, produto.preco, 1)
            addItemToCarrinho(item)
        }

        principalbinding.RecyclerViewProduto.adapter = adapter


        // codigos de mudanças de telas
        principalbinding.imgHomeHomeAct.setOnClickListener {
            var intent = Intent(it.context, PrincipalActivity::class.java)
            startActivity(intent)
        }

        principalbinding.imgCartHomeAct.setOnClickListener {
            val intent = Intent(it.context, CarrinhoActivity::class.java)

            val carrinhoJson = Gson().toJson(carrinhoProdutos)

            intent.putExtra("carrinho", carrinhoJson)

            startActivity(intent)
        }

        principalbinding.imgPerfilHomeAct.setOnClickListener {
            var intent = Intent(it.context, PerfilActivity::class.java)
            startActivity(intent)
        }
        principalbinding.imgSobreHomeAct.setOnClickListener {
            var intent = Intent(it.context, SobreActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addItemToCarrinho(item: ItemCartModel) {
        val existingItem = carrinhoProdutos.find { it.idItem == item.idItem }
        if (existingItem != null) {
            existingItem.quantidade += 1
        } else {
            carrinhoProdutos.add(item)
        }

        Log.d("Carrinho", "Carrinho Atual: ${carrinhoProdutos}")

        //saveCarrinho()

        Toast.makeText(this, "${item.nome} adicionado ao carrinho!", Toast.LENGTH_SHORT).show()
    }

    /*private fun saveCarrinho() {
        val sharedPref = getSharedPreferences("CarrinhoPrefs", MODE_PRIVATE)
        val editor = sharedPref.edit()

        val gson = Gson()
        val carrinhoJson = gson.toJson(carrinhoProdutos)

        editor.putString("carrinho", carrinhoJson)
        editor.apply()
    }

    private fun loadCarrinho() {
        val sharedPref = getSharedPreferences("CarrinhoPrefs", MODE_PRIVATE)
        val carrinhoJson = sharedPref.getString("carrinho", null)

        if (carrinhoJson != null) {
            val gson = Gson()
            val type = object : TypeToken<List<ItemCartModel>>() {}.type
            carrinhoProdutos.clear()
            carrinhoProdutos.addAll(gson.fromJson(carrinhoJson, type))
        }
    }*/


}