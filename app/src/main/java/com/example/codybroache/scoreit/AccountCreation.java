package com.example.codybroache.scoreit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountCreation extends AppCompatActivity {

    Button createBtn, cancelBtn;
    EditText nameText,usernameText,passwordText,confirmText;
    SharedPreferences prefs_user_pass;
    SharedPreferences prefs_user_name;
    static private final String TAG_UP = "scoreit_user_pass";
    static private final String TAG_UN = "scoreit_user_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        prefs_user_pass = getSharedPreferences(TAG_UP, MODE_PRIVATE);
        prefs_user_name = getSharedPreferences(TAG_UN, MODE_PRIVATE);
        nameText=(EditText)findViewById(R.id.full_name_acc);
        usernameText=(EditText)findViewById(R.id.username_acc);
        passwordText=(EditText)findViewById(R.id.password_acc);
        confirmText=(EditText)findViewById(R.id.password_confirm_acc);
        createBtn=(Button)findViewById(R.id.create_btn_acc);
        cancelBtn=(Button)findViewById(R.id.cancel_btn_acc);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String valueStr = prefs_user_pass.getString(usernameText.getText().toString(), null);
                if(checkName(nameText.getText().toString())
                        && checkUsername(usernameText.getText().toString())
                        && checkPass(passwordText.getText().toString(), confirmText.getText().toString())) {
                    if(valueStr == null) {
                        SharedPreferences.Editor editor1 = prefs_user_pass.edit();
                        editor1.putString(usernameText.getText().toString(), passwordText.getText().toString());
                        editor1.commit();
                        SharedPreferences.Editor editor2 = prefs_user_name.edit();
                        editor2.putString(usernameText.getText().toString(), nameText.getText().toString());
                        editor2.commit();
                        Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account_creation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkName(String name){
        if(name.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkUsername(String username){
        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a username", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkPass(String pass, String confirm){
        if(pass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!pass.equals(confirm)){
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(pass.length() < 6){
            Toast.makeText(getApplicationContext(), "Passwords must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
