package com.ekalyoncu.noteit.presentation.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekalyoncu.noteit.R
import com.ekalyoncu.noteit.databinding.FragmentNotesBinding
import com.ekalyoncu.noteit.domain.model.Note
import com.ekalyoncu.noteit.presentation.adapter.NoteAdapter
import com.ekalyoncu.noteit.presentation.listener.NoteDeleteListener
import com.ekalyoncu.noteit.presentation.listener.NoteOnClickListener
import com.ekalyoncu.noteit.util.DELETED_NOTE_KEY
import com.ekalyoncu.noteit.util.NOTE_DELETE_REQUEST_KEY
import com.ekalyoncu.noteit.util.parcelable
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment: Fragment(R.layout.fragment_notes) {

    private val viewModel: NotesViewModel by viewModels()

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private val noteAdapter: NoteAdapter = NoteAdapter(
        object : NoteOnClickListener{
            override fun onLongClick(note: Note): Boolean {
                navigateToAddEditScreen(note)
                return true
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(NOTE_DELETE_REQUEST_KEY){ _, bundle ->
            val deletedNote = bundle.parcelable<Note>(DELETED_NOTE_KEY)
            deletedNote?.let { note ->
                showNoteDeletedSnackbar(note)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    noteAdapter.submitList(state.notes)
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ){
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            noteRecyclerView.apply {
                adapter = noteAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

            floatingActionButton.setOnClickListener {
                navigateToAddEditScreen()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToAddEditScreen(note: Note? = null){
        val action = NotesFragmentDirections.actionNotesFragmentToAddEditFragment(note)
        findNavController().navigate(action)
    }

    private fun showNoteDeletedSnackbar(note: Note){
        viewModel.deleteNote(note)

        val snackbar = Snackbar.make(binding.root, "${note.title} başlıklı not silindi", Snackbar.LENGTH_LONG)
        snackbar.setAction(
            "Geri al",
            object : NoteDeleteListener {
                override fun onClick(view: View?) {
                    undoDeletion(note)
                }
            }
        )
        snackbar.anchorView = requireView().findViewById(R.id.floating_action_button)
        snackbar.show()
    }

    fun undoDeletion(note: Note){
        viewModel.insertNote(note)
    }
}