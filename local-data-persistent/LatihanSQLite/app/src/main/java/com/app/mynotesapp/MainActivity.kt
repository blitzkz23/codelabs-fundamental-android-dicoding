package com.app.mynotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mynotesapp.adapter.NoteAdapter
import com.app.mynotesapp.adapter.NoteAdapter.*
import com.app.mynotesapp.databinding.ActivityMainBinding
import com.app.mynotesapp.directory.Note
import com.app.mynotesapp.helper.MappingHelper
import com.app.mynotesapp.helper.NoteHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding
	private lateinit var adapter: NoteAdapter

	val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
		ActivityResultContracts.StartActivityForResult()
	) { result ->
		if (result.data != null) {
			// Akan dipanggil jika request codenya ADD
			when (result.resultCode) {
				NoteAddUpdateActivity.RESULT_ADD -> {
					val note =
						result.data?.getParcelableExtra<Note>(NoteAddUpdateActivity.EXTRA_NOTE) as Note
					adapter.addItem(note)
					binding.rvNotes.smoothScrollToPosition(adapter.itemCount - 1)
					showSnackbarMessage("Satu item berhasil ditambahkan")
				}
				NoteAddUpdateActivity.RESULT_UPDATE -> {
					val note =
						result.data?.getParcelableExtra<Note>(NoteAddUpdateActivity.EXTRA_NOTE) as Note
					val position =
						result.data?.getIntExtra(NoteAddUpdateActivity.EXTRA_POSITION, 0) as Int
					adapter.updateItem(position, note)
					binding.rvNotes.smoothScrollToPosition(position)
					showSnackbarMessage("Satu item berhasil diubah")
				}
				NoteAddUpdateActivity.RESULT_DELETE -> {
					val position =
						result.data?.getIntExtra(NoteAddUpdateActivity.EXTRA_POSITION, 0) as Int
					adapter.removeItem(position)
					showSnackbarMessage("Satu item berhasil dihapus")
				}
			}
		}
	}


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		supportActionBar?.title = "Notes"

		binding.rvNotes.layoutManager = LinearLayoutManager(this)
		binding.rvNotes.setHasFixedSize(true)

		val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)

		adapter = NoteAdapter(object : OnItemClickCallback {
			override fun onItemClicked(selected: Note?, position: Int?) {
				intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, selected)
				intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position)
				resultLauncher.launch(intent)
			}
		})
		binding.rvNotes.adapter = adapter

		binding.fabAdd.setOnClickListener {
			resultLauncher.launch(intent)
		}

		if (savedInstanceState == null) {
			// proses ambil data
			loadNotesAsync()
		} else {
			val list = savedInstanceState.getParcelableArrayList<Note>(EXTRA_STATE)
			if (list != null) {
				adapter.listNotes = list
			}
		}
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putParcelableArrayList(EXTRA_STATE, adapter.listNotes)
	}

	private fun loadNotesAsync() {
		lifecycleScope.launch {
			binding.progressBar.visibility = View.VISIBLE
			val noteHelper = NoteHelper.getInstance(applicationContext)
			noteHelper.open()
			val deferredNotes = async(Dispatchers.IO) {
				val cursor = noteHelper.queryAll()
				MappingHelper.mapCursorToArrayList(cursor)
			}
			binding.progressBar.visibility = View.INVISIBLE
			val notes = deferredNotes.await()
			if (notes.size > 0) {
				adapter.listNotes = notes
			} else {
				adapter.listNotes = ArrayList()
				showSnackbarMessage("Tidak ada data saat ini")
			}
			noteHelper.close()
		}
	}

	private fun showSnackbarMessage(message: String) {
		Snackbar.make(binding.rvNotes, message, Snackbar.LENGTH_SHORT).show()
	}

	companion object {
		private const val EXTRA_STATE = "EXTRA_STATE"
	}
}