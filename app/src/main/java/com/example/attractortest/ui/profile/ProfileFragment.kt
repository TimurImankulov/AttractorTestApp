package com.example.attractortest.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.attractortest.R
import com.example.attractortest.databinding.FragmentProfileBinding
import com.example.attractortest.utils.extensions.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel by viewModel<ProfileViewModel>()
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val adapter = CompanyListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(it.photo)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivProfileImage)

            binding.tvName.text = it.firstName
            binding.tvSecondName.text = it.secondName
            binding.tvEducation.text = getString(it.getAcademicDegree())

            adapter.submitList(it.company)
        }
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    companion object {
        val TAG: String = ProfileFragment::class.java.simpleName

        fun newInstance() = ProfileFragment()
    }
}