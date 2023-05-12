package com.edwinacubillos.thecastapp.ui.list

import BaseBottomSheetDialogFragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.domain.remote.data.LocalCat
import com.edwinacubillos.thecastapp.R
import com.edwinacubillos.thecastapp.databinding.FragmentListBinding
import com.edwinacubillos.thecastapp.utils.gone
import com.edwinacubillos.thecastapp.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {

        val listBinding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = listBinding.root

        val catList = ArrayList<LocalCat>()
        val catsRecyclerViewAdapter =
            CatsRecyclerViewAdapter(catList, onItemClicked = { onCatItemClicked(it) })

        listBinding.catsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = catsRecyclerViewAdapter
            setHasFixedSize(false)
        }

        listViewModel.catsLoaded.observe(viewLifecycleOwner) { result ->
            result?.let { resourceRemote ->
                when (result) {
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.let { wrappedResponse ->
                            wrappedResponse.data?.let { listProduct ->
                                catsRecyclerViewAdapter.appendItems(listProduct as ArrayList<LocalCat>)
                            }
                        }
                    }
                    is ResourceRemote.Error -> {
                        resourceRemote.message?.let { showMessageError(it) }
                    }
                    else -> {
                        //don't use
                    }
                }
            }
        }

        listViewModel.isLoading.observe(viewLifecycleOwner) { result ->
            result?.let { isLoading ->
                if (isLoading) listBinding.loadingLayout.loaderModalContainer.show()
                else listBinding.loadingLayout.loaderModalContainer.gone()
            }
        }

        listViewModel.getCats()

        return root
    }

    private fun showMessageError(message: String) {
        BaseBottomSheetDialogFragment.show(
            fragmentManager = childFragmentManager,
            customTitle = getString(R.string.warning),
            customDescription = message,
            customConfirmButtonTitle = getString(R.string.continue_label),
            customCancelButtonTitle = null
        )
    }

    private fun onCatItemClicked(cat: LocalCat) {
        Log.d("cat", cat.name)
    }
}
