package com.id22.employees;

import com.id22.employees.core.data.model.ResponseModel;
import com.id22.employees.core.data.remote.ApiClient;
import com.id22.employees.core.data.remote.ApiInterface;
import com.id22.employees.core.utils.Presenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements Presenter<MainView> {

    MainView mView;
    ApiInterface mInterface;
    CompositeDisposable disposes = new CompositeDisposable();

    @Override
    public void onAttach(MainView view) {
        mView = view;
        mInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void onDetach() {
        mView = null;
        disposes.clear();
    }

    public void getAllData() {
        mInterface.getAllEmployee()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (d != null) {
                            disposes.add(d);
                        }
                    }

                    @Override
                    public void onNext(ResponseModel response) {
                        if (response != null) {
                            mView.onSuccess(response);
                        } else {
                            mView.onError();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void createData(String name, String gender, String pob, String dob, String status, String address) {
        mInterface.createEmployee(name, gender, pob, dob, status, address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (d != null) {
                            disposes.add(d);
                        }
                    }

                    @Override
                    public void onNext(ResponseModel response) {
                        if (response != null) {
                            mView.onSuccessCreate();
                        } else {
                            mView.onErrorCreated();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onErrorCreated();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void updateData(int id, String name, String gender, String pob, String dob, String status, String address) {
        mInterface.updateEmployee(id, name, gender, pob, dob, status, address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (d != null) {
                            disposes.add(d);
                        }
                    }

                    @Override
                    public void onNext(ResponseModel response) {
                        if (response != null) {
                            mView.onSuccessUpdate();
                        } else {
                            mView.onErrorUpdate();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onErrorUpdate();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void deleteData(int id) {
        mInterface.deleteEmployee(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (d != null) {
                            disposes.add(d);
                        }
                    }

                    @Override
                    public void onNext(ResponseModel response) {
                        if (response != null) {
                            mView.onSuccessDelete();
                        } else {
                            mView.onErrorDelete();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onErrorDelete();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
