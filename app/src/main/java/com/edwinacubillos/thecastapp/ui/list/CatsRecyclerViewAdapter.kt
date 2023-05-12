package com.edwinacubillos.thecastapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edwinacubillos.thecastapp.R
import com.edwinacubillos.thecastapp.databinding.CardViewItemCatBinding
import com.edwinacubillos.thecastapp.server.model.CatList
import com.edwinacubillos.thecastapp.server.model.Cat
import com.squareup.picasso.Picasso

class CatsRecyclerViewAdapter (
    private val catsList: CatList,
    private val onItemClicked: (Cat) -> Unit
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

    fun appendItems(newList: ArrayList<Cat>){
        catsList.clear()
        catsList.addAll(newList)
        notifyItemInserted(newList.size)
    }

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardViewItemCatBinding.bind(itemView)

        fun bindCat(cat: Cat) {
            with(binding){
                catNameTextView.text = cat.name
                countryTextView.text = cat.origin
                intelligenceTextView.text = cat.intelligence.toString()
                Picasso.get().load("https://cdn2.thecatapi.com/images/"+cat.reference_image_id+".jpg").into(pictureImageView)
            }
        }
    }
}
