package com.example.juanfrancisco.parse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button cerrar = (Button)findViewById(R.id.button2);
        TextView name = (TextView)findViewById(R.id.name);
        ProfilePictureView profilePictureView;

        profilePictureView = (ProfilePictureView) findViewById(R.id.image);

        AccessToken at = AccessToken.getCurrentAccessToken();
        profilePictureView.setProfileId(at.getUserId());

        final SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);

        name.setText(sharedPref.getString("name", null));

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("name",null);
                //editor.putString("lastname",null);

                startActivity(i);
                finish();
            }
        });

    }
}
