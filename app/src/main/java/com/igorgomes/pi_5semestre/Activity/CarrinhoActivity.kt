package com.igorgomes.pi_5semestre.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.igorgomes.pi_5semestre.Adapter.ItemCartAdapter
import com.igorgomes.pi_5semestre.Model.ItemCartModel
import com.igorgomes.pi_5semestre.R
import com.igorgomes.pi_5semestre.databinding.ActivityCarrinhoBinding

class CarrinhoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarrinhoBinding
    private lateinit var itemCartAdapter: ItemCartAdapter
    private val itemList: MutableList<ItemCartModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarrinhoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView com o callback para atualização do valor total
        itemCartAdapter = ItemCartAdapter(
            itemList,
            onTotalValueItensChanged = { totalValue ->
                updateTotalValue(totalValue) // Atualiza o TextView do valor total de itens
            },
            onTotalVendaItensChanged = { totalVenda ->
                updateTotalVenda(totalVenda) // Atualiza o TextView do total de venda com adicional
            }
        )
        binding.recyclerViewCarrinho.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCarrinho.adapter = itemCartAdapter

        // Recuperar os dados do carrinho passados pela Intent
        val carrinhoJson = intent.getStringExtra("carrinho")

        if (!carrinhoJson.isNullOrEmpty()) {
            val gson = Gson()
            val type = object : TypeToken<List<ItemCartModel>>() {}.type
            val items = gson.fromJson<List<ItemCartModel>>(carrinhoJson, type)
            itemList.addAll(items)
            itemCartAdapter.notifyDataSetChanged() // Atualiza o RecyclerView com os dados

            // Atualiza o valor total inicial
            updateTotalValue(calcularTotalInicial())
            updateTotalVenda(calcularTotalVenda())
        } else {
            Toast.makeText(this, "Carrinho vazio!", Toast.LENGTH_SHORT).show()
        }

        // Configurar botões de navegação
        binding.imgHomeCartAct.setOnClickListener {
            startActivity(Intent(this, PrincipalActivity::class.java))
        }
        binding.imgCartCartAct.setOnClickListener {
            startActivity(Intent(this, CarrinhoActivity::class.java))
        }
        binding.imgPerfilCartAct.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }
        binding.imgSobreCartAct.setOnClickListener {
            startActivity(Intent(this, SobreActivity::class.java))
        }
    }

    // Atualiza o valor total no TextView correspondente
    private fun updateTotalValue(totalValue: Double) {
        binding.tvValorTotalItens.text = "${" %.2f".format(totalValue)}"
    }
    private fun updateTotalVenda(totalValue: Double){
        binding.tvTotalVenda.text = "${" %.2f".format(totalValue)}"
    }

    // Calcula o valor total inicial dos itens no carrinho
    private fun calcularTotalInicial(): Double {
        return itemList.sumOf { it.preco * it.quantidade }
    }

    private fun calcularTotalVenda(): Double{
        return itemList.sumOf { (it.preco * it.quantidade) + 10 }
    }
}
