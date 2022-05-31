package com.example.figma

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.util.Log

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(p0: SQLiteDatabase?) {


        val query = "CREATE TABLE $TABLE_NAME ( $NAME_COl TEXT PRIMARY KEY," +
                "$NUMBER_COL INTEGER," +
                "$EMAIL_COL TEXT," +
                "$PASSWORD_COL TEXT )"

        p0?.execSQL(query)


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {


    }

    fun addName(name: String, email: String, number: String, password: String) {

        val values = ContentValues()
        val db = this.writableDatabase


        values.put(NAME_COl, name)
        values.put(NUMBER_COL, number)
        values.put(EMAIL_COL, email)
        values.put(PASSWORD_COL, password)

        db.insert(TABLE_NAME, null, values)

    }

    fun getName(): Cursor {

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)


    }

    fun delete(name: String) {

        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$NAME_COl=?", arrayOf(name))

    }

//    fun update(name: String, age: String, currentName: String) {
//
//        val db = this.readableDatabase
//
//        if (name.isNotEmpty() and age.isEmpty()) {
//
//            db.execSQL(
//                "UPDATE $TABLE_NAME SET $NAME_COl = '$name' WHERE $NAME_COl = '$currentName'"
//            )
//
//
//        } else if (name.isEmpty() and age.isNotEmpty()) {
//            db.execSQL(
//                "UPDATE $TABLE_NAME SET $AGE_COL = '$age' WHERE $NAME_COl = '$currentName'"
//            )
//
//        } else {
//
//            db.execSQL(
//                "UPDATE $TABLE_NAME SET $NAME_COl = '$name'," +
//                        "$AGE_COL = '$age' WHERE $NAME_COl = '$currentName'"
//            )
//
//        }
//
//
//    }

    fun updateName(name: String, currentName: String) {

        val db = this.readableDatabase

        db.execSQL("UPDATE $TABLE_NAME SET $NAME_COl = '$name', WHERE $NAME_COl = '$currentName'")
    }


    companion object {

        private const val DATABASE_NAME = "FIGMA"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "CREDENTIALS"
        const val ID_COL = "id"
        const val NAME_COl = "Name"
        const val EMAIL_COL = "Email_Address"
        const val NUMBER_COL = "Phone_Number"
        const val PASSWORD_COL = "Password"

        var id = 0

    }
}