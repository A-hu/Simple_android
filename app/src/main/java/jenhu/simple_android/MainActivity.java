package jenhu.simple_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etPasswrd, etPhone, etAge;
    private TextView tvResult;
    private Button btLogin, btClear;
    private TextView tvMessage, tvSubmitMessage;
    private int pointCount;
    private TextView tvCount;
    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private int count = 0;
    private WebView webView;
    private RatingBar ratingBar;
    private TextView tvText;
    private SeekBar seekBar;

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
        findCountView();
        findSeekBarViews();
        tvCount.setText(String.valueOf(count));

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true); // loading Javascript
        webView.loadUrl("http://www.google.com");
        webView.setWebViewClient(new WebViewClient(){ // web view handled by app, not mobile browser
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               view.loadUrl(url);
                return true;
            }
        });

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
                String text = rating + " star(s)";
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
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

    private void findCountView(){
        tvCount = (TextView) findViewById(R.id.tvCount);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
    }

    public void onAddClick(View view){
        count++;
        tvCount.setText(String.valueOf(count));

        TextView textView = new TextView(this);
        textView.setText(String.valueOf(count));
        linearLayout.addView(textView);

        // New thread
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){ // use back button to handle website
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void findSeekBarViews(){
        tvText = (TextView) findViewById(R.id.tvText);
        seekBar = (SeekBar) findViewById(R.id.sbSize);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvText.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "start size = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "stop size = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
