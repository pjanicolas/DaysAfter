package com.example.daysafter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.daysafter.Database.DatabaseOperations;
import com.example.daysafter.Database.Days;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    /**
     * @var databaseOperator
     */
    private DatabaseOperations databaseOperator;


    DatePicker objDatePicker;
    TextView objResultView;
    Button objSaveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseOperator = new DatabaseOperations(this);
        databaseOperator.open();

        List values = databaseOperator.getAllDays();

        if (values.isEmpty()) {
            return;
        }

        objDatePicker = (DatePicker) findViewById(R.id.currentDatepicker);
        objResultView = (TextView) findViewById(R.id.tvResult);
        objSaveButton = (Button) findViewById(R.id.btnSubmit);
        GodClass godCLass = new GodClass();

        String sSavedDate = values.get(0) + "";

        int iSavedMonth = Integer.parseInt(godCLass.getValueInString(sSavedDate, godCLass.MONTHS));
        int iSavedDay = Integer.parseInt(godCLass.getValueInString(sSavedDate, godCLass.DAYS));
        int iSavedYear = Integer.parseInt(godCLass.getValueInString(sSavedDate, godCLass.YEAR));

        objDatePicker.updateDate(iSavedYear, iSavedMonth - 1, iSavedDay);
        objSaveButton.performClick();
    }

    /**
     *
     * @param
     * @throws ParseException
     */
    public void submitBtnListener(View v) throws ParseException {
        objDatePicker = (DatePicker) findViewById(R.id.currentDatepicker);
        objResultView = (TextView) findViewById(R.id.tvResult);

        String sTargetDate = objDatePicker.getDayOfMonth() + "/" + (objDatePicker.getMonth() + 1) + "/" + objDatePicker.getYear();
        deleteAllDays();
        saveTargetedDay(sTargetDate);

        GodClass godCLass = new GodClass();

        Date dateCurrentDate = new Date();
        Date dateTargetDate = godCLass.convertStringToDate(sTargetDate);

        String sDaysAndMonthsMessage = godCLass.generateDayAndMonthDifference(dateCurrentDate, dateTargetDate);
        objResultView.setText(sDaysAndMonthsMessage);
    }

    private void saveTargetedDay(String sTargetedDay)
    {
        Days day = databaseOperator.addDay(sTargetedDay);
    }

    private void deleteAllDays()
    {
        Days day = databaseOperator.deleteAllDays();
    }
}
