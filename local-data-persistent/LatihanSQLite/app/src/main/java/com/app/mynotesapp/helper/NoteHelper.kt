package com.app.mynotesapp.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.app.mynotesapp.db.DatabaseContract.NotesColumns.Companion.TABLE_NAME
import com.app.mynotesapp.db.DatabaseContract.NotesColumns.Companion._ID
import com.app.mynotesapp.db.DatabaseHelper

/**
 * Kelas di bawah menggunakan sebuah pattern yang bernama Singleton Pattern. Dengan singleton sebuah objek hanya bisa memiliki sebuah instance.
 * Sehingga tidak terjadi duplikasi instance.
 * synchronized di sini dipakai untuk menghindari duplikasi instance di semua Thread, karena bisa saja kita membuat instance di Thread yang berbeda.
 */
class NoteHelper(context: Context) {

	private var databaseHelper: DatabaseHelper = DatabaseHelper(context)
	private lateinit var database: SQLiteDatabase


	//	metode untuk membuka dan menutup koneksi ke database-nya.
	@Throws(SQLException::class)
	fun open() {
		database = databaseHelper.writableDatabase
	}

	fun close() {
		databaseHelper.close()

		if (database.isOpen)
			database.close()
	}

	//	metode crud
	fun queryAll(): Cursor {
		return database.query(
			DATABASE_TABLE,
			null,
			null,
			null,
			null,
			null,
			"$_ID ASC"
		)
	}

	//	metode get by _id
	fun queryById(id: String): Cursor {
		return database.query(
			DATABASE_TABLE,
			null,
			"$_ID = ?",
			arrayOf(id),
			null,
			null,
			null,
			null
		)
	}

	// metode untuk menyimpan data
	fun insert(values: ContentValues?): Long {
		return database.insert(DATABASE_TABLE, null, values)
	}

	// metode untuk update data
	fun update(id: String, values: ContentValues?): Int {
		return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
	}

	// metode untuk menghapus data
	fun deleteById(id: String): Int {
		return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
	}

	companion object {
		private const val DATABASE_TABLE = TABLE_NAME

		//	Untuk menginisiasi instance db
		private var INSTANCE: NoteHelper? = null
		fun getInstance(context: Context): NoteHelper =
			INSTANCE ?: synchronized(this) {
				INSTANCE ?: NoteHelper(context)
			}
	}
}