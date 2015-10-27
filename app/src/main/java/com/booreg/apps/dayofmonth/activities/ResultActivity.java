package com.booreg.apps.dayofmonth.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.booreg.apps.dayofmonth.R;

import java.util.Calendar;

/**
 * Controller class for Result activity
 */

public class ResultActivity extends AppCompatActivity
{
    private TextView txtRight ;
    private Button   btnStartAgain;

    private TextView txtFailed;
    private Button   btnTryAgain;

    //********************************************************************************************
    // Private section
    //********************************************************************************************

    /**
     * Initializes visual components
     */

    private void initializeVisualComponents()
    {
        txtRight      = (TextView) findViewById(R.id.txtRight);
        btnStartAgain = (Button) findViewById(R.id.btnStartAgain);
        txtFailed     = (TextView) findViewById(R.id.txtFailed);
        btnTryAgain   = (Button) findViewById(R.id.btnTryAgain);
    }

    /**
     * Gets the number entered by the user when calling this activity.
     */

    private int getDayOfMonth()
    {
        Intent intent = getIntent();

        return Integer.parseInt(intent.getStringExtra(Constants.EXTRA_DAY_OF_MONTH));
    }

    /**
     * Returns true or false if the value specified in dayOfMonth corresponds to the real current
     * day number of the current month
     */

    private boolean isCurrentDayOfMonth(int dayOfMonth)
    {
        Calendar cal = Calendar.getInstance();
        int currentDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        return dayOfMonth == currentDayOfMonth;
    }

    //********************************************************************************************
    // Protected section
    //********************************************************************************************

    /**
     * On creation, shows a message if the user as guessed the current day number or not, playing a
     * sound for each case
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        initializeVisualComponents();

        if (isCurrentDayOfMonth(getDayOfMonth()))
        {
            txtRight.setVisibility(View.VISIBLE);
            btnStartAgain.setVisibility(View.VISIBLE);
            txtFailed.setVisibility(View.GONE);
            btnTryAgain.setVisibility(View.GONE);

            MediaPlayer.create(this, R.raw.okay).start();
        }
        else
        {
            txtRight.setVisibility(View.GONE);
            btnStartAgain.setVisibility(View.GONE);
            txtFailed.setVisibility(View.VISIBLE);
            btnTryAgain.setVisibility(View.VISIBLE);

            MediaPlayer.create(this, R.raw.o_o).start();
        }
    }

    //********************************************************************************************
    // Public section
    //********************************************************************************************

    /**
     * Finishes the activiy returning to previous one.
     */

    public void btnReturn(View view)
    {
        finish();
    }
}
