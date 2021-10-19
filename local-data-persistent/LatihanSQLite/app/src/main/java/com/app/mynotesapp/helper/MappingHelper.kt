package com.app.mynotesapp.helper

import android.database.Cursor
import com.app.mynotesapp.db.DatabaseContract
import com.app.mynotesapp.directory.Note

object MappingHelper {

	/**
	 * Pada NoteHelper proses load data dilakukan dengan eksekusi queryAll() menghasilkan objek Cursor,
	 * namun pada adapter kita membutuhkan dalam bentuk ArrayList, maka dari itu kita harus mengonversi dari Cursor ke Arraylist, di sinilah fungsi kelas pembantu MappingHelper.
	 */
	fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<Note> {
		val notesList = ArrayList<Note>()

		notesCursor?.apply {
//			moveToNext digunakan untuk memindahkan cursor ke baris selanjutnya. Di sini kita ambil datanya satu per satu menggunakan getColumnIndexOrThrow dan dimasukkan ke dalam ArrayList.
			while (moveToNext()) {
				val id = getInt(getColumnIndexOrThrow(DatabaseContract.NotesColumns._ID))
				val title = getString(getColumnIndexOrThrow(DatabaseContract.NotesColumns.TITLE))
				val description = getString(getColumnIndexOrThrow(DatabaseContract.NotesColumns.DESCRIPTION))
				val date = getString(getColumnIndexOrThrow(DatabaseContract.NotesColumns.DATE))
				notesList.add(Note(id, title, description, date))
			}
		}
		return notesList
	}
}