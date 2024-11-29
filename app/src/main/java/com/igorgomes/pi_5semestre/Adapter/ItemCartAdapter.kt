package com.igorgomes.pi_5semestre.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igorgomes.pi_5semestre.Model.ItemCartModel
import com.igorgomes.pi_5semestre.ViewModel.ItemViewHolder
import com.igorgomes.pi_5semestre.databinding.ViewholderCartBinding

class ItemCartAdapter(
    private val items: MutableList<ItemCartModel>,
    private val onTotalValueItensChanged: (Double) -> Unit,
    private val onTotalVendaItensChanged: (Double) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(
            binding,
            onRemoveClick = { position -> removeItem(position) },
            onAddQuantity = { position -> addItem(position) },
            onRemoveQuantity = { position -> decrementQuantity(position) }
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        // Atualiza o valor total do item na ViewHolder
        val valorTotal = calcularValorTotal(item)
        holder.binding.tvValorTotalUn.text = "R$ ${"%.2f".format(valorTotal)}"
    }

    // Função para calcular o valor total de um item
    private fun calcularValorTotal(item: ItemCartModel): Double {
        return item.preco * item.quantidade
    }

    // Função para calcular o valor total de todos os itens no carrinho
    private fun calcularTotalCarrinho(): Double {
        return items.sumOf { it.preco * it.quantidade }
    }

    private fun calcularTotalVenda(): Double {
        return calcularTotalCarrinho() + 10
    }

    // Função para remover um item do carrinho
    private fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
        onTotalValueItensChanged(calcularTotalCarrinho())
        onTotalVendaItensChanged(calcularTotalVenda())
    }

    // Função para incrementar a quantidade de um item
    private fun addItem(position: Int) {
        val item = items[position]
        item.quantidade += 1
        notifyItemChanged(position)
        onTotalValueItensChanged(calcularTotalCarrinho())
        onTotalVendaItensChanged(calcularTotalVenda())
    }

    // Função para decrementar a quantidade de um item
    private fun decrementQuantity(position: Int) {
        val item = items[position]
        if (item.quantidade > 1) {
            item.quantidade -= 1
            notifyItemChanged(position)
        } else {
            removeItem(position)
        }
        onTotalValueItensChanged(calcularTotalCarrinho())
        onTotalVendaItensChanged(calcularTotalVenda())
    }
}
