package com.example.paul.practica02;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBAdapter db = new DBAdapter(this);

        //---add a contact---
        db.open();
        long id = db.insertContact("Jennifer Ann",
                "jenniferann@jfdimarzio.com");
        id = db.insertContact("Juan Perez", "oscar@gmail.com");
        id = db.insertContact("Carlos Jimenez", "carlos@gmail.com");
        db.close();


        // ******************* GET ALL CONTACTS ****************
        db.open();
        Cursor c = db.getAllContacts();     //pointer
        if (c.moveToFirst())
        {
            do {
                DisplayContact(c);
            } while (c.moveToNext());
        }
        db.close();

        //---get a contact---
        db.open();
        Cursor c2 = db.getContact(2);
        if (c2.moveToFirst())
            DisplayContact(c2);
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        db.close();

        //**************  MODIFICAR ************
        db.open();
        if (db.updateContact(1, "Oscar Diggs", "oscar@oscardiggs.com"))
            Toast.makeText(this, "Update successful.",
                    Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
        db.close();

        // ***********   DELETE A CONTACT ***************
        db.open();
        if (db.deleteContact(1))
            Toast.makeText(this, "Delete successful.",
                    Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
        db.close();

    }

    public void DisplayContact(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Name: " + c.getString(1) + "\n" +
                        "Email: " + c.getString(2),
                Toast.LENGTH_LONG).show();
    }

}
