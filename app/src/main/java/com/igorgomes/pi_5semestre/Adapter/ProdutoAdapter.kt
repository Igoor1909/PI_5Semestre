package com.igorgomes.pi_5semestre.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igorgomes.pi_5semestre.Activity.DetalhesAbsolutActivity
import com.igorgomes.pi_5semestre.Activity.DetalhesTanquerayActivity
import com.igorgomes.pi_5semestre.Model.ItemCartModel
import com.igorgomes.pi_5semestre.Model.ProdutoModel
import com.igorgomes.pi_5semestre.ViewModel.ProdutoViewHolder
import com.igorgomes.pi_5semestre.databinding.ViewholderProdutoBinding

class ProdutoAdapter(
    private val listaAbsolut: List<ProdutoModel>,
    private val onProdutoAdicionado: (ItemCartModel) -> Unit
) : RecyclerView.Adapter<ProdutoViewHolder>() {

    private val carrinhoItems = mutableListOf<ItemCartModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val binding = ViewholderProdutoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProdutoViewHolder(binding)
    }

    override fun getItemCount(): Int = listaAbsolut.size

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = listaAbsolut[position]

        // Acessando elementos do layout diretamente pelo View Binding
        holder.biding.TvNome.text = produto.nome
        holder.biding.tvPreco.text = "R$ ${produto.preco}"
        holder.biding.imgProduto.setImageResource(produto.imagemResId)

        // Configurando o clique no botão "Adicionar"
        holder.biding.btnAddProduto.setOnClickListener {
            // Adicionando o produto ao carrinho (passando para a próxima tela)
            val item = ItemCartModel(produto.idProduto, produto.nome, produto.preco, 1)
            adicionarProdutoAoCarrinho(item)

            onProdutoAdicionado(item)
        }

        // Configurando o clique na imagem para redirecionar para a tela de detalhes
        holder.biding.imgProduto.setOnClickListener {
            val intent = if (produto.nome == "Vodka Absolut") {
                Intent(it.context, DetalhesAbsolutActivity::class.java)
            } else {
                Intent(it.context, DetalhesTanquerayActivity::class.java)
            }
            it.context.startActivity(intent)
        }
    }

    private fun adicionarProdutoAoCarrinho(item: ItemCartModel) {
        // Verifica se o item já existe no carrinho
        val existingItem = carrinhoItems.find { it.idItem == item.idItem }
        if (existingItem != null) {
            // Se o item já existe, incrementa a quantidade
            existingItem.quantidade += 1
        } else {
            // Se o item não existe, adiciona ao carrinho
            carrinhoItems.add(item)
        }
    }
}
