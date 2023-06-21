
package com.example.medcenter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityOformlenieZ extends AppCompatActivity {
    Button btGetZakaz;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oformlenie_z);
        TextView tvWho=findViewById(R.id.tvWho);
        String t = "<font color=#7E7E9A>Кто будет сдавать анализы?</font> <font color=#B71C1C>*</font>";
        tvWho.setText(Html.fromHtml(t));
        btGetZakaz=findViewById(R.id.btGetZakaz);
        btGetZakaz.setEnabled(false);
    }


}