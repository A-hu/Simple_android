package jenhu.simple_android;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenhu on 16/10/2017.
 */

public class LinearLayout extends MainActivity{
    List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
        views = new ArrayList<>();
    }

    public void onImageG1Click(View view){
        view.setVisibility(View.INVISIBLE);
        views.add(view);
    }

    public void onImageG2Click(View view){
        view.setVisibility(View.GONE);
        views.add(view);
    }

    public void onResetClick(View view){
        if(views != null && views.size() > 0){
            for(View v : views){
                v.setVisibility(View.VISIBLE);
            }
        }

    }
}
