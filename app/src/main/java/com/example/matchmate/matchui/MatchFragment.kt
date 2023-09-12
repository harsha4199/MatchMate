package com.example.matchmate.matchui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmate.R
import com.example.matchmate.adapter.CardAdapter
import com.example.matchmate.network.isNetworkAvailable
import com.example.matchmate.repository.ResultDataRepoImpl
import com.example.matchmate.repository.ResultsRepository
import com.example.matchmate.roomdatabase.ResultsDatabase
import com.example.matchmate.viewmodel.InformationViewModel
import com.example.matchmate.viewmodel.ViewModelFactory

class MatchFragment : Fragment() {
    private lateinit var recyclerAdapter: CardAdapter
    private lateinit var loaderProgressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_match, container, false)
        loaderProgressBar = view.findViewById(R.id.loadingData)
        loaderProgressBar.visibility = View.VISIBLE
        initRecyclerView(view)
        initViewModel()
        return view
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerAdapter = CardAdapter()
        recyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        val resultsDao = ResultsDatabase.getDatabase(requireContext()).resultsDataDao()
        val resultsRepository: ResultsRepository = ResultDataRepoImpl(resultsDao)
        val viewModelFactory = ViewModelFactory(resultsRepository)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(InformationViewModel::class.java)

        if (isNetworkAvailable(requireContext())) {
            viewModel.makeApiCall()
        } else {
            viewModel.loadFromRepository()
        }

        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer { dataManager ->
            dataManager?.let {
                recyclerAdapter.setUpdatedData(it.cardModelTransformationList)
                loaderProgressBar.visibility = View.GONE
            } ?: run {
                Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
                loaderProgressBar.visibility = View.GONE
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = MatchFragment()
    }
}