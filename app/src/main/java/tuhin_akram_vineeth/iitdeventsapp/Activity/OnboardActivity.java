package tuhin_akram_vineeth.iitdeventsapp.Activity;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

import tuhin_akram_vineeth.iitdeventsapp.R;

public class OnboardActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Create Account", "Hassle free account creation. Sign up for free!", R.drawable.ic_supervisor_account_white_48dp);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Find Friends", "Search for your friends and add them", R.drawable.ic_person_add_white_48dp);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Set Reminder", "Don't worry about missing an event. Just add event to calender.", R.drawable.ic_event_white_48dp);

        AhoyOnboarderCard ahoyOnboarderCard4 = new AhoyOnboarderCard("Add Poll", "Not sure how many people will attend, just add a poll", R.drawable.ic_poll_white_48dp);
        AhoyOnboarderCard ahoyOnboarderCard5 = new AhoyOnboarderCard("Chat", "Chat with your friends or in a group", R.drawable.ic_chat_white_48dp);

        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent_image);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent_image);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent_image);
        ahoyOnboarderCard4.setBackgroundColor(R.color.black_transparent_image);
        ahoyOnboarderCard5.setBackgroundColor(R.color.black_transparent_image);


        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        pages.add(ahoyOnboarderCard4);
        pages.add(ahoyOnboarderCard5);


        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_600);
            //page.setTitleTextSize(dpToPixels(12, this));
            //page.setDescriptionTextSize(dpToPixels(8, this));
            //page.setIconLayoutParams(width, height, marginTop, marginLeft, marginRight, marginBottom);
        }

        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setImageBackground(R.drawable.background);
        //set the button style you created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        }

        //Typeface face = Typeface.createFromAsset(getAssets(),getAssets().)
        //setFont(face);

        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();

    }
}

