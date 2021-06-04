package com.google.samples.apps.hatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.samples.apps.hatch.adapters.CustomAdapter
import com.google.samples.apps.hatch.data.Profile
import com.google.samples.apps.hatch.data.ProfileHeader
import com.google.samples.apps.hatch.data.ProfileListItem
import com.google.samples.apps.hatch.databinding.FragmentProfileListBinding
import com.google.samples.apps.hatch.viewmodels.ProfileListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileListFragment : Fragment() {

    private val viewModel: ProfileListViewModel by viewModels()
    lateinit var adapter : CustomAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        adapter = CustomAdapter()
        binding.profileList.adapter = adapter
        subscribeUi(adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: CustomAdapter) {
        viewModel.profiles.observe(viewLifecycleOwner, {
            // Unsure of where header objects are coming from
            val profileHeader = ProfileHeader("Header")
            val listWithHeader = mutableListOf<ProfileListItem>(profileHeader)
            listWithHeader.addAll(it)
            adapter.submitList(listWithHeader)

        } )
    }
}
