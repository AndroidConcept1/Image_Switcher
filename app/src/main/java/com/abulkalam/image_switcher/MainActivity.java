package com.abulkalam.image_switcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private ImageSwitcher simpleImageSwitcher;
    Button btnNext;

    //Array of image id to show in ImageSwitcher
    int imageIds[] = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4,
            R.drawable.food5, R.drawable.food6, R.drawable.food7, R.drawable.food8, R.drawable.food9};

    int count = imageIds.length;
    //to keep current index of ImageId array
    int currentIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the reference of Button & ImageSwitcher
        btnNext = findViewById(R.id.btnNext);
        simpleImageSwitcher = findViewById(R.id.imageSwitcher);

        //set the ViewFactory of the ImageSwitcher that will create an ImageView object when asked
        simpleImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                //Create a new ImageView and set its properties
                ImageView imageView = new ImageView(getApplicationContext());
                //Set scaleType of ImageView to Fit Center
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                //Set the width and height of ImageView to FILL PARENT
               // imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
                  imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });

        //Declare in and out animations and load them using AnimationUtils class
        Animation in  = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        //Set the animation type to ImageSwitcher
        simpleImageSwitcher.setAnimation(in);
        simpleImageSwitcher.setAnimation(out);

        //ClickListener for NEXT Button
        //When clicked on button ImageSwitcher will switch between Images
        //The current Image will go out and next Image will come in with specified position
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                //check if index reaches maximum then reset it
                if (currentIndex == count)
                    currentIndex = 0;
                simpleImageSwitcher.setImageResource(imageIds[currentIndex]); //set the specified Image in ImageSwitcher
            }
        });
    }
}