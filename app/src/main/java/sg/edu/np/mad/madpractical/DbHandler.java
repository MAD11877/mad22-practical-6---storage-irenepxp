package sg.edu.np.mad.madpractical;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class DbHandler extends SQLiteOpenHelper {

    private static final String TABLE_USERS = "USERS";
    private static final String COLUMN_USERNAME = "NAME";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_FOLLOWED = "FOLLOWED";


    public DbHandler(Context c)
    {
        super(c, "user.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String  CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                 TABLE_USERS +
                "(" + COLUMN_USERNAME + "TEXT,"+
                 COLUMN_DESCRIPTION + "TEXT," +
                 COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                  COLUMN_FOLLOWED + " INTEGER" + ")";

        db.execSQL(CREATE_PRODUCTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);

    }

    public void addUser(User user) {
        /*
         * INSERT INTO USER VALUES("name","description", "followed")
         */
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.name);
        values.put(COLUMN_DESCRIPTION, user.description);
        if(user.followed){
            values.put(COLUMN_FOLLOWED,1);
        }
        else{
            values.put(COLUMN_FOLLOWED,0);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS,null,values);
        //db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES(\""+u.name+"\",\""+u.description+"\",\""+u.followed+"\")");
        db.close();
    }

    public ArrayList<User> getUsers()
    {
        /*
         * SELECT * FROM USERS
         */
        ArrayList<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS,null);
        while(cursor.moveToNext()){
            User user = new User();
            user.name = cursor.getString(0);
            user.description = cursor.getString(1);
            if(cursor.getInt(3)==1){
                user.followed = true;
            }
            else{
                user.followed = false;
            }
            userList.add(user);
        }
        cursor.close();
        db.close();
        return userList;
    }

    public void updateUser(User user){
        /*
         * UPDATE user SET followed = 0/1 WHERE name = name
         */
        Integer followedBool;
        if (user.followed) {
            followedBool = 1;
        }
        else{
            followedBool = 0;
        }
        String query = "UPDATE "+TABLE_USERS+" SET "
                + COLUMN_FOLLOWED + " = "
                + followedBool + " WHERE "
                + COLUMN_USERNAME + " = \""
                + user.name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public boolean deleteUserbyName(String name) {

        boolean result = false;

        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_USERNAME + " = \""
                + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();
        if (cursor.moveToFirst()) {
            user.id = cursor.getInt(2);
            db.delete(TABLE_USERS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(user.id) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

}




