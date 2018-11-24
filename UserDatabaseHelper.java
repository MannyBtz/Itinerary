package com.example.haitiangk.e_itinerary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userManager";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRST = "firstName";
    private static final String KEY_LAST = "lastName";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";

    public UserDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE" + TABLE_USERS + "("
                + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRST + "TEXT, "
                + KEY_LAST + "TEXT, " + KEY_USERNAME + "TEXT, " +KEY_PASSWORD + "TEXT, "
                + KEY_EMAIL + "TEXT, " + KEY_PHONE + "TEXT" + ")";
        
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    //Adding a new group member
    void addMember(Member newMember){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST, newMember.getFirstName());
        values.put(KEY_LAST, newMember.getLastName());
        values.put(KEY_USERNAME, newMember.getUsername());
        values.put(KEY_PASSWORD, newMember.getPassword());
        values.put(KEY_EMAIL, newMember.getEmail());
        values.put(KEY_PHONE, newMember.getPhoneNumber());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    //Getting a single group member
    public Member getMember(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID, KEY_FIRST, KEY_LAST,
                        KEY_USERNAME, KEY_PASSWORD, KEY_EMAIL, KEY_PHONE}, KEY_ID + " = ?",
            new String[]{String.valueOf(id)}, null,null,null,null);

        Member member;
        if(cursor != null) {
            cursor.moveToFirst();

            member = new Member(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6));

            cursor.close();
        }else
            member = new Member();

        return member;

    }

    //Getting a list of all members
    public List<Member> getAllMembers(){
        List<Member> memberList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Member member = new Member(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6));

                memberList.add(member);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return memberList;
    }

    //Updating a single member
    public int updateMember(Member member){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST, member.getFirstName());
        values.put(KEY_LAST, member.getLastName());
        values.put(KEY_USERNAME, member.getUsername());
        values.put(KEY_PASSWORD, member.getPassword());
        values.put(KEY_EMAIL, member.getEmail());
        values.put(KEY_PHONE, member.getPhoneNumber());

        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(member.getID())});
    }

    //Deleting a member
    public void deleteMember(Member member){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[]{String.valueOf(member.getID())});

        db.close();
    }

    //Getting a count of all members
    public int getMemberCount(){
        String countQuery = " SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
