package com.techmania.mynotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.techmania.mynotes.databinding.FragmentCreateNotesBinding

import java.text.SimpleDateFormat
import java.util.*


class CreateNotesFragment : Fragment() {

    lateinit var binding : FragmentCreateNotesBinding
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        binding.pGreen.setImageResource(R.drawable.baseline_done_24)

        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val notes = binding.edtNotes.text.toString()
        if (title.isBlank() || notes.isBlank()) {
            Toast.makeText(
                requireContext(),
                "Please enter both title and notes",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val notesDate = formatter.format(Date())

            viewModel.addNotes(Notes(title, notes, notesDate, priority))

            findNavController().popBackStack()
        }
    }
}

