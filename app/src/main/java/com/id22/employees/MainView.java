package com.id22.employees;

import com.id22.employees.core.data.model.EmployeeModel;
import com.id22.employees.core.data.model.ResponseModel;
import com.id22.employees.core.utils.View;

public interface MainView extends View {
    void onSuccess(ResponseModel data);

    void onSuccessCreate();

    void onSuccessUpdate();

    void onSuccessDelete();

    void onError();
    void onErrorCreated();
    void onErrorUpdate();
    void onErrorDelete();
}
