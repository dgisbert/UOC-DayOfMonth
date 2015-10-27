package com.booreg.apps.dayofmonth.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.booreg.apps.dayofmonth.R;

import org.apache.commons.lang3.StringUtils;

/**
 * Controller class for Main activity
 */

public class MainActivity extends AppCompatActivity
{
    private EditText dayOfMonth;

    //********************************************************************************************
    // Private section
    //********************************************************************************************

    /**
     * Initializes visual components
     */

    private void initializeVisualComponents()
    {
        dayOfMonth = (EditText) findViewById(R.id.dayOfMonth);
    }

    //********************************************************************************************
    // Protected section
    //********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        try
        {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

            setTitle(getString(R.string.app_name) + " " + pInfo.versionName);
        }
        catch (Exception e) { e.printStackTrace(); }

        initializeVisualComponents();
    }

    //********************************************************************************************
    // Public section
    //*******************************************************************************************

    /**
     * Shows the Result Activity, passng the value entered by the user.
     */

    public void btnSendOnClick(View view)
    {
        String textDayOfMonth = dayOfMonth.getText().toString();

        if      (StringUtils.isBlank(textDayOfMonth)) dayOfMonth.setError(getString(R.string.MSG00001));
        else if (Integer.parseInt(textDayOfMonth) > Constants.MAX_DAY_OF_MONTH) dayOfMonth.setError(getString(R.string.MSG00002));
        else
        {
            this.dayOfMonth.setText(""); // Delete the value entered by user to be cleared when returning from result activity.

            Intent intent = new Intent(this, ResultActivity.class);

            intent.putExtra(Constants.EXTRA_DAY_OF_MONTH, textDayOfMonth);

            startActivity(intent);
        }
    }
}
