package com.example.anura.anuragprofile;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    private boolean isOpen= false;
    private ConstraintSet layout1,layout2;
    private ConstraintLayout constraintLayout;
    private ImageView imageViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //changing the status bar color to transparent
        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        layout1=new ConstraintSet();
        layout2=new ConstraintSet();
        imageViewPhoto=findViewById(R.id.photo);
        constraintLayout=findViewById(R.id.constraint_layout);
        layout2.clone(this,R.layout.profile_expanded);
        layout1.clone(constraintLayout);

        imageViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpen)
                {
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    layout2.applyTo(constraintLayout);
                    isOpen=!isOpen;
                }
                else
                {
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    layout1.applyTo(constraintLayout);
                    isOpen=!isOpen;
                }
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
