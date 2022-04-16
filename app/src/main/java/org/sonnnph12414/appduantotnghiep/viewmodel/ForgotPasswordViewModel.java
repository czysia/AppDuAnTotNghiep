package org.sonnnph12414.appduantotnghiep.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.sonnnph12414.appduantotnghiep.ApiService;

public class ForgotPasswordViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<Boolean> isRegisterSuccess = new MutableLiveData<>();

}
