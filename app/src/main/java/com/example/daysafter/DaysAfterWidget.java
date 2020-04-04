package com.example.daysafter;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.daysafter.Database.DatabaseOperations;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class DaysAfterWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) throws ParseException {

        String sWidgetText = setText(context);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.days_after_widget);
        views.setTextViewText(R.id.appwidget_text, sWidgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            try {
                updateAppWidget(context, appWidgetManager, appWidgetId);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /**
     *
     * @param context
     * @return
     * @throws ParseException
     */
    static String setText(Context context) throws ParseException {
        DatabaseOperations databaseOperator = new DatabaseOperations(context);
        databaseOperator.open();
        List values = databaseOperator.getAllDays();

        if (values.isEmpty()) {
            return "Not yet initialized";
        }
        GodClass godClass = new GodClass();

        String sSavedDate = values.get(0) + "";
        Date dateCurrentDate = new Date();
        Date dateTargetDate = godClass.convertStringToDate(sSavedDate);
        return godClass.generateDayAndMonthDifference(dateCurrentDate, dateTargetDate);
    }
}

