package me.luocaca.rebate.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.luocaca.rebate.R;
import me.luocaca.rebate.ui.fragment.HomeFragment;

public class loginActivity extends AppCompatActivity {
    private Button mbtnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mbtnlogin=findViewById(R.id.login);
        mbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
