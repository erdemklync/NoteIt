package com.ekalyoncu.noteit.presentation.ui.add_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ekalyoncu.noteit.R
import com.ekalyoncu.noteit.databinding.FragmentAddEditBinding

class AddEditFragment: Fragment(R.layout.fragment_add_edit){

    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}