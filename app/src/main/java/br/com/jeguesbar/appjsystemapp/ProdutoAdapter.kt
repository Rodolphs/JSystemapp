package br.com.jeguesbar.appjsystemapp

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_produto.view.*

class ProdutoAdapter(
    val produtos: List<Produtos>,
    val onClick: (Produtos) -> Unit
) : RecyclerView.Adapter<ProdutoAdapter.ProdutosViewHolder>() {

    class ProdutosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cardNome: TextView
        val cardProgress: ProgressBar
        val cardView: CardView

        init {
            cardNome = view.card_nome
            cardProgress = view.card_progress
            cardView = view.card_produto
        }

    }

    override fun getItemCount() = this.produtos.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProdutosViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_produto, parent, false)

        val holder = ProdutosViewHolder(view)

        return holder

    }

    override fun onBindViewHolder(
        holder: ProdutosViewHolder,
        position: Int
    ) {

        val contexto = holder.itemView.context
        val produto = this.produtos[position]

        holder.cardNome.text = produto.nome
        holder.cardProgress.visibility = View.VISIBLE

//        Picasso.with(contexto).load(produto.foto).fit()
//            .into(holder.cardImg,
//                object : com.squareup.picasso.Callback {
//                    override fun onSuccess() {
//                        holder.cardProgress.visibility = View.INVISIBLE
//                    }
//
//                    override fun onError() {
//                        holder.cardProgress.visibility = View.INVISIBLE
//                    }
//                })

        holder.itemView.setOnClickListener {onClick (produto)}

    }

}