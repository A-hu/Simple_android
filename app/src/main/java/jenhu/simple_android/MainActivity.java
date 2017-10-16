package jenhu.simple_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etPasswrd, etPhone, etAge;
    private TextView tvResult;
    private Button btLogin, btClear;
    private TextView tvMessage, tvSubmitMessage;
    private int pointCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        etName = (EditText) findViewById(R.id.etName);
//        etPasswrd = (EditText) findViewById(R.id.etPassword);
//        tvResult = (TextView) findViewById(R.id.tvResult);
//        btLogin = (Button) findViewById(R.id.btLogin);
//        btLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = etName.getText().toString();
//                String password = etPasswrd.getText().toString();
//                String text = "Name : " + name + "\nPassword :" + password;
//                tvResult.setText(text);
//            }
//        });

        findViews();
        findSubmitViews();
    }

    private void findViews(){
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.relativelayout);

        relativelayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("first pointer: (%.1f, %.1f), %n",
                        event.getX(), event.getY()));
                sb.append("touch state: ");
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sb.append("ACTION_DOWN\n");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        sb.append("ACTION_MOVE\n");
                        break;
                    case MotionEvent.ACTION_UP:
                        sb.append("ACTION_UP\n");
                        break;
                    default:
                        sb.append("\n");
                        break;
                }

                int pointCount = event.getPointerCount();
                sb.append(String.format("point count: %d %n", pointCount));
                for(int i = 0; i < pointCount; i++){
                    sb.append(String.format("pointer: %d: (%.1f, %.1f) %n",
                            event.getPointerId(i), event.getX(i), event.getY(i)));
                }
                tvMessage.setText(sb);
                return true;
            }

        });
    }

    private void findSubmitViews(){
        etName = (EditText) findViewById(R.id.etName);
        etPasswrd = (EditText) findViewById(R.id.etPassword);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etAge = (EditText) findViewById(R.id.etAge);
        btClear = (Button) findViewById(R.id.btClear);
        tvSubmitMessage = (TextView) findViewById(R.id.tvSubmitMessage);

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText(null);
                etPasswrd.setText(null);
                etPhone.setText(null);
                etAge.setText(null);
                tvSubmitMessage.setText(null);

                Toast.makeText(
                        MainActivity.this,
                        R.string.msg_ClearFields,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    public void onSubmitClick(View view){
        String user = etName.getText().toString().trim();
        String password = etPasswrd.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        String text = "";

        text += "user name = " + user + "\n";
        text += "password = " + password + "\n";
        text += "phone = " + phone + "\n";
        text += "age = " + age + "\n";

        tvSubmitMessage.setText(text);
    }

//    public void onLoginClick(View view) {
//        String name = etName.getText().toString();
//        String password = etPasswrd.getText().toString();
//        String text = "Name : " + name + "\nPassword :" + password;
//        tvResult.setText(text);
//    }
}
