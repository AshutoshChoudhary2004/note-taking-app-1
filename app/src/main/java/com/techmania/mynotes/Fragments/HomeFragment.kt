package com.techmania.mynotes

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.techmania.mynotes.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    lateinit var notesAdapter: NotesAdapter
    var tempNotes = arrayListOf<Notes>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        viewModel.getNotes().observe(viewLifecycleOwner) {
            tempNotes.clear()
            tempNotes.addAll(it)
            binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
            notesAdapter = NotesAdapter(requireContext(), it, viewModel)
            binding.rcvAllNotes.adapter = notesAdapter
        }

        binding.filterDsa.setOnClickListener {
            viewModel.getDsaNotes().observe(viewLifecycleOwner) {
                tempNotes.clear()
                tempNotes.addAll(it)
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                notesAdapter = NotesAdapter(requireContext(), it, viewModel)
                binding.rcvAllNotes.adapter = notesAdapter
            }
        }

        binding.filterCollege.setOnClickListener {
            viewModel.getCollegeNotes().observe(viewLifecycleOwner) {
                tempNotes.clear()
                tempNotes.addAll(it)
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                notesAdapter = NotesAdapter(requireContext(), it, viewModel)
                binding.rcvAllNotes.adapter = notesAdapter
            }
        }

        binding.filterDev.setOnClickListener {
            viewModel.getDevNotes().observe(viewLifecycleOwner) {
                tempNotes.clear()
                tempNotes.addAll(it)
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                notesAdapter = NotesAdapter(requireContext(), it, viewModel)
                binding.rcvAllNotes.adapter = notesAdapter
            }
        }

        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) {
                tempNotes.clear()
                tempNotes.addAll(it)
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                notesAdapter = NotesAdapter(requireContext(), it, viewModel)
                binding.rcvAllNotes.adapter = notesAdapter
            }
        }

        binding.btnAddNotes.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCreateNotesFragment()
            findNavController().navigate(action)
        }

        val searchView = binding.searchViewId
        searchView.queryHint = "Search Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                val newFilteredList = arrayListOf<Notes>()

                for (i in tempNotes) {
                    if (i.title.lowercase().contains(p0!!) || i.notes.lowercase().contains(p0)) {
                        newFilteredList.add(i)
                    }
                }
                notesAdapter.filtering(newFilteredList)
                return true
            }
        })

        return binding.root
    }
}
