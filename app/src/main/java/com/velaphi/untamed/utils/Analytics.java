package com.velaphi.untamed.utils;

import com.velaphi.untamed.features.categories.CategoryModel;

public interface Analytics {

    void trackOpenCategories();

    void trackCategories(CategoryModel categoryModel);
}
