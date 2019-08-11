package com.velaphi.untamed.utils;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.velaphi.untamed.features.categories.CategoryModel;
import com.velaphi.untamed.features.licenses.LicenceModel;

public class UntamedFirebaseAnalytics implements Analytics {

    private FirebaseAnalytics firebaseAnalytics;

    public UntamedFirebaseAnalytics(FirebaseAnalytics firebaseAnalytics) {
        this.firebaseAnalytics = firebaseAnalytics;
    }

    @Override
    public void trackOpenCategories() {
        firebaseAnalytics.logEvent(FirebaseAnalyticsEnums.VIEW_CATEGORY_SCREEN.label, null);
    }

    @Override
    public void trackOpenLicences() {
        firebaseAnalytics.logEvent(FirebaseAnalyticsEnums.VIEW_CATEGORY_SCREEN.label, null);
    }

    @Override
    public void trackCategories(CategoryModel categoryModel) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalyticsEnums.VIEW_CATEGORY_NAME.label, categoryModel.getName());
        firebaseAnalytics.logEvent(FirebaseAnalyticsEnums.SELECTION_CATEGORY_NAME.label, bundle);
    }

    @Override
    public void trackLicenses(LicenceModel licenceModel) {
        firebaseAnalytics.logEvent(FirebaseAnalyticsEnums.VIEW_LICENCE_SCREEN.label, null);
    }

    public enum FirebaseAnalyticsEnums {
        VIEW_CATEGORY_SCREEN("view_categories"),
        VIEW_LICENCE_SCREEN("view_categories"),
        VIEW_CATEGORY_NAME("Category_name"),
        SELECTION_CATEGORY_NAME("clicked_category");

        public final String label;

        FirebaseAnalyticsEnums(String label) {
            this.label = label;
        }

    }
}
