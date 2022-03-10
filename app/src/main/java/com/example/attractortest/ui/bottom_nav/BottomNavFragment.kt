package com.example.attractortest.ui.bottom_nav

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.attractortest.R
import com.example.attractortest.databinding.FragmentBottomNavBinding
import com.example.attractortest.ui.images.ImagesFragment
import com.example.attractortest.ui.profile.ProfileFragment
import com.example.attractortest.utils.extensions.viewBinding


/**
 * Class for managing fragment navigation and saving fragment state.
 */

class BottomNavFragment : Fragment(R.layout.fragment_bottom_nav) {

    private val binding by viewBinding(FragmentBottomNavBinding::bind)
    private val savedBackStackTags = HashSet<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.getIntArray(KEY_SAVED_BACK_STACK)?.let {
            savedBackStackTags.addAll(it.asList())
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (getNavigationFragmentManager().backStackEntryCount > 1) {
                        getNavigationFragmentManager().popBackStack()
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
        )

        binding.bottomNav.setOnItemSelectedListener { item ->
            val lastSelectedItemId = binding.bottomNav.selectedItemId
            val newSelectedItemId = item.itemId

            if (lastSelectedItemId != newSelectedItemId) {
                getNavigationFragmentManager().saveBackStack(lastSelectedItemId.toString())
                savedBackStackTags.add(lastSelectedItemId)
            }

            if (!savedBackStackTags.contains(newSelectedItemId)) {
                val (fragment, tag) = getDestination(newSelectedItemId)

                getNavigationFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(getFragmentContainerId(), fragment, tag)
                    .addToBackStack(newSelectedItemId.toString())
                    .commit()
            } else {
                getNavigationFragmentManager().restoreBackStack(newSelectedItemId.toString())
                savedBackStackTags.remove(newSelectedItemId)
            }

            true
        }

        getNavigationFragmentManager().findFragmentById(getFragmentContainerId()) ?: run {
            binding.bottomNav.selectedItemId = getInitialDestinationItemId()
        }

        binding.bottomNav.setOnItemReselectedListener { item ->
            val reselectedItemId = item.itemId
            getNavigationFragmentManager().popBackStack(reselectedItemId.toString(), 0)
        }
    }

    private fun getNavigationFragmentManager() = childFragmentManager

    private fun getFragmentContainerId() = binding.bottomNavContainerFragments.id

    private fun getInitialDestinationItemId() = R.id.profile

    private fun getDestination(itemId: Int): Pair<Fragment, String> {
        return when (itemId) {
            R.id.profile -> {
                ProfileFragment.newInstance() to ProfileFragment.TAG
            }
            R.id.images -> {
                ImagesFragment.newInstance() to ImagesFragment.TAG
            }
            else -> throw IllegalStateException("Unable to create fragment for this item id!")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray(KEY_SAVED_BACK_STACK, savedBackStackTags.toIntArray())
    }

    companion object {
        val TAG: String = BottomNavFragment::class.java.simpleName

        fun newInstance() = BottomNavFragment()
        private const val KEY_SAVED_BACK_STACK = "KeySavedBackStack"
    }
}