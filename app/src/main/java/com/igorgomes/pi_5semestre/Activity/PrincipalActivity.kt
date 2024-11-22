package com.igorgomes.pi_5semestre.Activity


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.igorgomes.pi_5semestre.Adapter.AbsolutAdapter
import com.igorgomes.pi_5semestre.Model.ProdutoModel
import com.igorgomes.pi_5semestre.R
import com.igorgomes.pi_5semestre.databinding.ActivityPrincipalBinding


class PrincipalActivity : AppCompatActivity() {

    lateinit var principalbinding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        principalbinding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(principalbinding.root)

        // Configurando o LayoutManager com 2 colunas
        principalbinding.RecyclerViewProduto.layoutManager = GridLayoutManager(this, 2)

        // Unindo os produtos em uma única lista
        val listaProdutos = listOf(
            ProdutoModel(1,"Vodka Absolut", "R$ 119,90", R.drawable.absolute_pequeno),
            ProdutoModel(2,"Gin Tanqueray", "R$ 109,90", R.drawable.tanqueray)
        )

        // Usando um único adaptador
        principalbinding.RecyclerViewProduto.adapter = AbsolutAdapter(listaProdutos) { produtoModel ->
            Toast.makeText(this, "${produtoModel.nome} adicionado ao carrinho!", Toast.LENGTH_SHORT).show()
        }

        // codigos de mudanças de telas
        principalbinding.imgHomeHomeAct.setOnClickListener {
            var intent = Intent(it.context, PrincipalActivity::class.java)
            startActivity(intent)
        }
        principalbinding.imgCartHomeAct.setOnClickListener {
            var intent = Intent(it.context, CarrinhoActivity::class.java)
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
}