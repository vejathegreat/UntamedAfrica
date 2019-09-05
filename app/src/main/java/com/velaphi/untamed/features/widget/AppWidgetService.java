package com.velaphi.untamed.features.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.velaphi.untamed.features.animalDetails.models.Fact;
import com.velaphi.untamed.utils.Prefs;

public class AppWidgetService extends RemoteViewsService {

    public static void updateWidget(Context context, Fact fact) {
        Prefs.saveFact(context, fact);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, NewAppWidget.class));
        NewAppWidget.updateAppWidgets(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        return null;
    }

}
