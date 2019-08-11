package com.velaphi.untamed.repository.contracts;

import com.velaphi.untamed.features.categories.CategoryModel;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface CategoryRepository {

    void getListOfCategories(@NonNull RepositoryCallback callback);

    interface RepositoryCallback {

        void onSuccess(List<CategoryModel> categoryModelList);

        void onError(Exception exception);
    }
}
