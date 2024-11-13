import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.igorgomes.pi_5semestre.R
import com.igorgomes.pi_5semestre.Responses.ProdutoResponse

class ItemCarrinhoAdapter (


    private var listaItens: MutableList<ProdutoResponse>
): RecyclerView.Adapter<ItemCarrinhoAdapter.ItemCarrinhoViewHolder>(){


    inner class ItemCarrinhoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeProduto: TextView = itemView.findViewById(R.id.tvNomeProduto)
        val valorUn: TextView = itemView.findViewById(R.id.tvValorUn)
        val valorTotal: TextView = itemView.findViewById(R.id.tvTotalProd)
        val quantidade: TextView = itemView.findViewById(R.id.tvQuantidade)
        val adicionar: TextView = itemView.findViewById(R.id.tvAdicionar)
        val remover: TextView = itemView.findViewById(R.id.tvRemover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCarrinhoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart, parent, false)
        return ItemCarrinhoViewHolder(view)
    }

    override fun getItemCount(): Int = listaItens.size

    fun adicionarItem(produto: ProdutoResponse) {
        val produtoExistente = listaItens.find { it.id == produto.id }

        if (produtoExistente != null) {
            produtoExistente.quantidade += produto.quantidade
        } else {
            listaItens.add(produto)
        }

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemCarrinhoViewHolder, position: Int) {
        val item = listaItens[position]

        holder.nomeProduto.text = item.nome
        holder.valorUn.text = "R$: %.2f".format(item.preco)
        holder.quantidade.text = item.quantidade.toString()
        holder.valorTotal.text = "R$: %.2f".format(item.preco * item.quantidade)


        holder.adicionar.setOnClickListener {
            item.quantidade++
            holder.quantidade.text = item.quantidade.toString()
            holder.valorTotal.text = "R$: %.2f".format(item.preco * item.quantidade)

        }

        holder.remover.setOnClickListener {
            if (item.quantidade > 1) {
                item.quantidade--
                holder.quantidade.text = item.quantidade.toString()
                holder.valorTotal.text = "R$: %.2f".format(item.preco * item.quantidade)
            }
        }

    }

}