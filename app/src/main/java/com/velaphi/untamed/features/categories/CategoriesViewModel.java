package com.velaphi.untamed.features.categories;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.velaphi.untamed.injection.UntamedAfricaComponent;
import com.velaphi.untamed.repository.contracts.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

public class CategoriesViewModel extends ViewModel implements UntamedAfricaComponent.Injectable {

    @Inject
    CategoryRepository categoryRepository;

    private MutableLiveData<List<CategoryModel>> categoryListLiveData = new MutableLiveData<>();
    private MutableLiveData<Exception> exceptionStatus = new MutableLiveData<>();

    @Override
    public void inject(UntamedAfricaComponent untamedAfricaComponent) {
        untamedAfricaComponent.inject(this);
    }

    void retrieveListOfCategoriesFromFirebase() {
        categoryRepository.getListOfCategories(new CategoryRepository.RepositoryCallback() {
            @Override
            public void onSuccess(List<CategoryModel> categoryModelList) {
                categoryListLiveData.setValue(categoryModelList);

            }

            @Override
            public void onError(Exception exception) {
                exceptionStatus.setValue(exception);
            }
        });
    }

    MutableLiveData<List<CategoryModel>> getCategoryListLiveData() {
        return categoryListLiveData;
    }

    MutableLiveData<Exception> getExceptionMessage() {
        return exceptionStatus;
    }
}
