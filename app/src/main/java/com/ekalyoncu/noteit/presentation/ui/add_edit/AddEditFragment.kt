package com.ekalyoncu.noteit.presentation.ui.add_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ekalyoncu.noteit.R
import com.ekalyoncu.noteit.databinding.FragmentAddEditBinding
import com.ekalyoncu.noteit.util.afterTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditFragment: Fragment(R.layout.fragment_add_edit){

    private val viewModel: AddEditViewModel by viewModels()

    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditBinding.inflate(inflater, container, false)

        with(binding){

            viewModel.state.value.note.let { note ->
                titleEditText.setText(note.title)
                textEditText.setText(note.text)
            }

            addEditToolbar.setOnMenuItemClickListener { item ->
                if(item.itemId == R.id.save){
                    viewModel.insertEntry()
                    findNavController().popBackStack()
                }
                true
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            titleEditText.afterTextChanged { title ->
                viewModel.setTitle(title)
            }
            textEditText.afterTextChanged { text ->
                viewModel.setText(text)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}