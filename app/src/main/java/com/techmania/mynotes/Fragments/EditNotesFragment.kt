package com.techmania.mynotes

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.techmania.mynotes.databinding.FragmentEditNotesBinding
import java.text.SimpleDateFormat
import java.util.*

class EditNotesFragment : Fragment() {

    val notes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    val viewModel: NotesViewModel by viewModels()
    lateinit var priority: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        binding.edtTitle.setText(notes.data.title)
        binding.edtNotes.setText(notes.data.notes)

        when (notes.data.priority) {
            "1" -> {
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pRed.setImageResource(R.drawable.baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
        }

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

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }
        binding.deleteIconId.setOnClickListener {
            showDeleteDialog()
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val notesDescription = binding.edtNotes.text.toString()
        if (title.isBlank() || notesDescription.isBlank()) {
            Toast.makeText(
                requireContext(),
                "Please enter both title and notes",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val notesDate = formatter.format(Date())

            val newNote = Notes(title, notesDescription, notesDate, priority)
            newNote.id = notes.data.id

            viewModel.updateNotes(newNote)
            findNavController().popBackStack()
        }
    }

    private fun showDeleteDialog() {
        val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
        bottomSheet.setContentView(R.layout.dialog_delete)

        val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialogYes)
        val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialogNo)

        bottomSheet.show()

        textViewYes?.setOnClickListener {

            findNavController().popBackStack()

            bottomSheet.dismiss()
            viewModel.deleteNotes(notes.data)

        }

        textViewNo?.setOnClickListener {
            bottomSheet.dismiss()
        }
    }
}
