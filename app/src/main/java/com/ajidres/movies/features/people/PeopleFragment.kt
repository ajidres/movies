package com.ajidres.movies.features.people

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ajidres.movies.base.BaseFragment
import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.databinding.FragmentPeopleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>() {

    private val viewModel: PeopleViewModel by viewModels()

    private val peopleAdapter by lazy { PeopleAdapter() }

    override fun initBinding(): FragmentPeopleBinding = FragmentPeopleBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        progressBar = binding.progressBar.root

        setupPeopleRecycler()

        peopleDataObserverApi()
        peopleDataObserverDb()
        viewModel.getPeopleDb()
    }

    private fun setupPeopleRecycler() {
        binding.rvPeople.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = peopleAdapter
        }
    }

    private fun peopleDataObserverApi() {
        viewModel.peopleDataApi.observe(this) { people ->
            when (people) {
                is ResultEndpoints.Loading -> {
                    showProgress(people.show)
                }

                is ResultEndpoints.Success -> {
                    viewModel.savePeople(people.data)
                    peopleAdapter.update(people.data.results)
                }

                is ResultEndpoints.Failure -> {

                    Toast.makeText(requireActivity(), people.errorMessage!!.description, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun peopleDataObserverDb() {
        viewModel.peopleDataDb.observe(this) { result ->
            viewModel.fetchPeople()
            peopleAdapter.update(result.results)
        }
    }

    companion object {
        fun newInstance(): PeopleFragment {
            return PeopleFragment()
        }
    }
}