package com.velaphi.untamed.utils;

import com.velaphi.untamed.features.categories.CategoryModel;
import com.velaphi.untamed.features.licenses.LicenceModel;

public interface Analytics {

    void trackOpenCategories();

    void trackOpenLicences();

    void trackCategories(CategoryModel categoryModel);

    void trackLicenses(LicenceModel licenceModel);
}
