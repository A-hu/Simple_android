package jenhu.simple_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etPasswrd;
    private TextView tvResult;
    private Button btLogin;
    private TextView tvMessage;
    private int pointCount;

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

        findViews();
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

//    public void onLoginClick(View view) {
//        String name = etName.getText().toString();
//        String password = etPasswrd.getText().toString();
//        String text = "Name : " + name + "\nPassword :" + password;
//        tvResult.setText(text);
//    }
}
