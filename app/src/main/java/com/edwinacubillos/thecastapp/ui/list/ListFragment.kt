package com.edwinacubillos.thecastapp.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinacubillos.thecastapp.databinding.FragmentListBinding
import com.edwinacubillos.thecastapp.server.model.Cat
import com.edwinacubillos.thecastapp.server.model.CatList

class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {

        val listBinding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = listBinding.root
        val listViewModel = ViewModelProvider(this)[ListViewModel::class.java]

        val catList = CatList()
        val catsRecyclerViewAdapter = CatsRecyclerViewAdapter(catList, onItemClicked = { onCatItemClicked(it)})

        listBinding.catsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = catsRecyclerViewAdapter
            setHasFixedSize(false)
        }

        listViewModel.catsLoaded.observe(viewLifecycleOwner) { catList ->
            catsRecyclerViewAdapter.appendItems(catList)
        }

        listViewModel.getCats()

        return root
    }

    private fun onCatItemClicked(it: Cat) {

    }
}
