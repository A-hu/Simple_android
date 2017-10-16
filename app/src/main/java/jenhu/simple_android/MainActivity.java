package jenhu.simple_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etPasswrd;
    private TextView tvResult;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText) findViewById(R.id.etName);
        etPasswrd = (EditText) findViewById(R.id.etPassword);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String password = etPasswrd.getText().toString();
                String text = "Name : " + name + "\nPassword :" + password;
                tvResult.setText(text);
            }
        });
    }

//    public void onLoginClick(View view) {
//        String name = etName.getText().toString();
//        String password = etPasswrd.getText().toString();
//        String text = "Name : " + name + "\nPassword :" + password;
//        tvResult.setText(text);
//    }
}
