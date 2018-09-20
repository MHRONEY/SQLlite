package com.example.a.sqllite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mid, mname, mpassword, mgender;
    TextView mshow;
    Button msave;
    databaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mid = findViewById(R.id.id);
        mname = findViewById(R.id.username);
        mpassword = findViewById(R.id.password);
        mgender = findViewById(R.id.gender);
        msave = findViewById(R.id.save);
        mshow = findViewById(R.id.show);
        databaseHelper = new databaseHelper(this);

        msave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long rowid = databaseHelper.insertdata(mid.getText().toString(), mname.getText().toString(), mpassword.getText().toString(), mgender.getText().toString());

                if (rowid == -1) {
                    Toast.makeText(getApplicationContext(), "Unsuccessfull data insert", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Successfull data insert", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void viewdata(View view) {


        Cursor result = databaseHelper.display();

        if (result.getCount() == 0) {
            Toast.makeText(this, "No data dound", Toast.LENGTH_LONG).show();
            return;

        }
        result.moveToFirst();
        StringBuffer buffer = new StringBuffer();

        do {
            buffer.append("ID : " + result.getString(0) + "\n");
            buffer.append("User Name : " + result.getString(1) + "\n");

            buffer.append("Password : " + result.getString(2) + "\n");

            buffer.append("Gender : " + result.getString(3) + "\n");
        } while (result.moveToNext());


        Display(buffer.toString());
    }

    public void Display(String data) {

        mshow.setText(data);
    }

    public void update(View view) {

        boolean checker  = databaseHelper.update(mid.getText().toString(), mname.getText().toString(), mpassword.getText().toString(), mgender.getText().toString());

        if (checker== true) {
            Toast.makeText(getApplicationContext(), "successfull Update Data ", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "UnSuccessfull  update Data", Toast.LENGTH_LONG).show();
        }


    }

    public void Delete(View view) {
        int deletechecker =databaseHelper.delete(mid.getText().toString());
        if (deletechecker>0){
            Toast.makeText(getApplicationContext(), " Data Delete successfull  ", Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(getApplicationContext(), "Data Delete Unsuccessfull", Toast.LENGTH_LONG).show();

        }
    }
}
