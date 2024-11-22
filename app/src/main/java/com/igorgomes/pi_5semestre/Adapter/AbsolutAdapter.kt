package com.igorgomes.pi_5semestre.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igorgomes.pi_5semestre.Activity.DetalhesAbsolutActivity
import com.igorgomes.pi_5semestre.Activity.DetalhesTanquerayActivity
import com.igorgomes.pi_5semestre.Model.ProdutoModel
import com.igorgomes.pi_5semestre.ViewModel.AbsolutViewHolder
import com.igorgomes.pi_5semestre.databinding.ViewholderProdutoBinding

class AbsolutAdapter (

    private val listaAbsolut: List<ProdutoModel>,
    private val adicionarProduto: (ProdutoModel) -> Unit
    ) : RecyclerView.Adapter<AbsolutViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsolutViewHolder {
        val binding = ViewholderProdutoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AbsolutViewHolder(binding)
    }

    override fun getItemCount(): Int = listaAbsolut.size

    override fun onBindViewHolder(holder: AbsolutViewHolder, position: Int) {
        val produto = listaAbsolut[position]

        // Acessando elementos do layout diretamente pelo View Binding
        holder.biding.TvNome.text = produto.nome
        holder.biding.tvPreco.text = produto.preco
        holder.biding.imgProduto.setImageResource(produto.imagemResId)

        // Configurando o clique no botão "Adicionar"
        holder.biding.btnAddProduto.setOnClickListener {
            adicionarProduto(produto)
        }

        // Configurando o clique na imagem para redirecionar para a tela apropriada
        holder.biding.imgProduto.setOnClickListener {
            val intent = if (produto.nome == "Vodka Absolut") {
                Intent(it.context, DetalhesAbsolutActivity::class.java)
            } else {
                Intent(it.context, DetalhesTanquerayActivity::class.java)
            }
            it.context.startActivity(intent)
        }
    }


}