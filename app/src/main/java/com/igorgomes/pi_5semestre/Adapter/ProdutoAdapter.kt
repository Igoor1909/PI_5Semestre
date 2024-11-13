package com.igorgomes.pi_5semestre.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igorgomes.pi_5semestre.databinding.ViewholderProdutoBinding

class ProdutoAdapter(
    private val context: Context,
    private val produtos: List<String>
) : RecyclerView.Adapter<ProdutoAdapter.ViewHolder>() {

    class ViewHolder(viewholderProdutoBinding: ViewholderProdutoBinding) : RecyclerView.ViewHolder(viewholderProdutoBinding.root) {
        fun bind(produto: String) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ViewHolder(
            ViewholderProdutoBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )

        )
    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val produto =  produtos[position]
        holder.bind(produto)
    }
}