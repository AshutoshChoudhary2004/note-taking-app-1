package com.techmania.mynotes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.techmania.mynotes.databinding.ItemNotesBinding

class NotesAdapter(val requireContext: Context, var notes: List<Notes>, val viewModel: NotesViewModel ) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(val binding : ItemNotesBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val data = notes[position]
            holder.binding.notesTitle.text = data.title
            holder.binding.notesDescription.text = data.notes
            holder.binding.notesDate.text = notes[position].date

            when (data.priority){
                "1" -> {
                    holder.binding.viewPrioirity.setBackgroundResource(R.drawable.green_dot)
                }
                "2" -> {
                    holder.binding.viewPrioirity.setBackgroundResource(R.drawable.yellow_dot)
                }
                "3" -> {
                    holder.binding.viewPrioirity.setBackgroundResource(R.drawable.red_dot)
                }
            }

        holder.binding.root.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.root.setOnLongClickListener {
            val bottomSheet  = BottomSheetDialog(requireContext, R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialogYes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialogNo)

            bottomSheet.show()

            textViewYes?.setOnClickListener {

                bottomSheet.dismiss()
                viewModel.deleteNotes(data)

            }

            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            true
        }

    }
    fun filtering(newFilteredList: ArrayList<Notes>) {
        notes = newFilteredList
        notifyDataSetChanged()
    }
}