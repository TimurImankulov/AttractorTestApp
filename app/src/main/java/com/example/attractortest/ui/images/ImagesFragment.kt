package com.example.attractortest.ui.images

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.attractortest.R
import com.example.attractortest.databinding.FragmentImagesBinding
import com.example.attractortest.utils.PermissionUtils
import com.example.attractortest.utils.extensions.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImagesFragment : Fragment(R.layout.fragment_images) {

    private val binding by viewBinding(FragmentImagesBinding::bind)
    private val viewModel by viewModel<ImagesViewModel>()
    private val adapter = ImageListAdapter()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(
                requireContext(),
                getString(R.string.permission_launcher_message),
                Toast.LENGTH_SHORT
            ).show()
            viewModel.getGalleryImages()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        if (PermissionUtils.isCheckedReadPermission(requireContext())) {
            viewModel.getGalleryImages()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        viewModel.galleryImagesLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        val TAG: String = ImagesFragment::class.java.simpleName

        fun newInstance() = ImagesFragment()
    }
}