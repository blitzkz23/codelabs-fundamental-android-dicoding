package com.app.mynoteappsroomdb.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Untuk melakukan CRUD
 */
@Dao
interface NoteDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insert(note: Note)

	@Update
	fun update(note: Note)

	@Delete
	fun delete(note: Note)

	@Query("SELECT * from note ORDER BY id ASC")
	fun getAllNotes(): LiveData<List<Note>>
}