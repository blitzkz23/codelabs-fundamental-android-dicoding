package com.app.mynotesapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.mynotesapp.db.DatabaseContract.*
import com.app.mynotesapp.db.DatabaseContract.NotesColumns.Companion.TABLE_NAME

// Kebutuhan DDL
internal class DatabaseHelper(context: Context) :
	SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

	override fun onCreate(db: SQLiteDatabase) {
		db.execSQL(SQL_CREATE_TABLE_NOTE)
	}

	//	onUpgrade untuk menghandle perubahan yang terjadi
	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
		db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
		onCreate(db)
	}

	companion object {

		private const val DATABASE_NAME = "dbnoteapp"

		private const val DATABASE_VERSION = 1

		private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
				" (${NotesColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
				" ${NotesColumns.TITLE} TEXT NOT NULL," +
				" ${NotesColumns.DESCRIPTION} TEXT NOT NULL," +
				" ${NotesColumns.DATE} TEXT NOT NULL)"
	}


}