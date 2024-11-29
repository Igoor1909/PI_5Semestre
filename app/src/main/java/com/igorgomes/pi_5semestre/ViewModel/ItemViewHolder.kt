package com.igorgomes.pi_5semestre.ViewModel

import androidx.recyclerview.widget.RecyclerView
import com.igorgomes.pi_5semestre.Model.ItemCartModel
import com.igorgomes.pi_5semestre.databinding.ViewholderCartBinding

class ItemViewHolder(
    val binding: ViewholderCartBinding,
    private val onRemoveClick: (Int) -> Unit,
    private val onAddQuantity: (Int) -> Unit,
    private val onRemoveQuantity: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemCartModel) {
        binding.tvNomeProduto.text = item.nome
        binding.tvValorTotalUn.text = "R$ ${String.format(" %.2f", item.preco)}"
        binding.tvQuantidade.text = item.quantidade.toString()

        val valorTotal = item.preco * item.quantidade
        binding.tvValorTotalUn.text = "R$ ${String.format(" %.2f", valorTotal)}"

        binding.tvAdicionar.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onAddQuantity(adapterPosition)
            }
        }

        binding.tvRemover.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onRemoveQuantity(adapterPosition)
            }
        }

        binding.imgDeletar.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onRemoveClick(adapterPosition)
            }
        }
    }
}
