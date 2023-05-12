package com.edwinacubillos.thecastapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edwinacubillos.thecastapp.R
import com.edwinacubillos.thecastapp.databinding.CardViewItemCatBinding
import com.edwinacubillos.domain.remote.data.LocalCat
import com.squareup.picasso.Picasso

class CatsRecyclerViewAdapter (
    private val catsList: ArrayList<LocalCat>,
    private val onItemClicked: (LocalCat) -> Unit
) : RecyclerView.Adapter<CatsRecyclerViewAdapter.CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_cat, parent, false)
        return CatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = catsList[position]
        holder.bindCat(cat)
        holder.itemView.setOnClickListener { onItemClicked(catsList[position]) }
    }

    override fun getItemCount(): Int = catsList.size

    fun appendItems(newList: ArrayList<LocalCat>){
        catsList.clear()
        catsList.addAll(newList)
        notifyItemInserted(newList.size)
    }

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardViewItemCatBinding.bind(itemView)

        fun bindCat(cat: LocalCat) {
            with(binding){
                catNameTextView.text = cat.name
                countryTextView.text = cat.origin
                intelligenceTextView.text = cat.intelligence.toString()
                Picasso.get().load(cat.imageUrl).into(pictureImageView)
            }
        }
    }
}
