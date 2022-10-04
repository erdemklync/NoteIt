package com.ekalyoncu.noteit.presentation.ui.add_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ekalyoncu.noteit.R
import com.ekalyoncu.noteit.databinding.FragmentAddEditBinding
import com.ekalyoncu.noteit.domain.model.Note
import com.ekalyoncu.noteit.util.DELETED_NOTE_KEY
import com.ekalyoncu.noteit.util.NOTE_DELETE_REQUEST_KEY
import com.ekalyoncu.noteit.util.afterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddEditFragment: Fragment(R.layout.fragment_add_edit){

    private val viewModel: AddEditViewModel by viewModels()

    private val arguments: AddEditFragmentArgs by navArgs()

    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    binding.noteDeleteButton.visibility = if (state.editMode) View.VISIBLE else View.GONE
                }
            }
        }

        with(binding){
            addEditToolbar.title = getString(viewModel.toolbarTitle)

            addEditToolbar.setNavigationIcon(R.drawable.ic_back)
            addEditToolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            addEditToolbar.setOnMenuItemClickListener { item ->
                if(item.itemId == R.id.save){
                    viewModel.insertNote()
                    findNavController().popBackStack()
                }
                true
            }

            viewModel.state.value.note.let { note ->
                titleEditText.setText(note.title)
                textEditText.setText(note.text)
            }

            val currentColor = noteColorRadioGroup[viewModel.state.value.note.color].id
            noteColorRadioGroup.check(currentColor)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note: Note? = arguments.note
        with(binding){
            titleEditText.afterTextChanged { title ->
                viewModel.setTitle(title)
            }

            textEditText.afterTextChanged { text ->
                viewModel.setText(text)
            }

            noteColorRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                val index = group.indexOfChild(activity?.findViewById(checkedId))
                viewModel.setColor(index)
            }

            noteDeleteButton.setOnClickListener {
                setFragmentResult(
                    NOTE_DELETE_REQUEST_KEY,
                    bundleOf(DELETED_NOTE_KEY to note),
                )
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}