package com.id22.employees;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.id22.employees.core.data.model.EmployeeModel;
import com.id22.employees.core.data.model.ResponseModel;
import com.id22.employees.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MainView, MainAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ActivityMainBinding bind;
    private MainPresenter presenter = new MainPresenter();
    private ArrayList<EmployeeModel> mEmployee = new ArrayList<>();
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        View view = bind.getRoot();
        setContentView(view);

        bind.srLayout.setOnRefreshListener(this);

        initPresenter();
        onAttachView();
        setOnCLickListener();

        presenter.getAllData();
        setAdapter();
    }

    @Override
    public void onRefresh() {
        bind.srLayout.setRefreshing(false);
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    private void initPresenter() {
        presenter = new MainPresenter();
    }

    private void setOnCLickListener() {
        bind.btnSave.setOnClickListener(v -> callApi());
        bind.btnUpdate.setOnClickListener(v -> callApiUpdate());
    }

    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        MainAdapter mainAdapter = new MainAdapter(this, mEmployee, this);
        bind.rvEmployee.setAdapter(mainAdapter);
        bind.rvEmployee.setLayoutManager(layoutManager);
    }

    public void callApi() {
        String name = Objects.requireNonNull(bind.etName.getText()).toString();
        String gender = Objects.requireNonNull(bind.etGender.getText()).toString();
        String pob = Objects.requireNonNull(bind.etPob.getText()).toString();
        String dob = Objects.requireNonNull(bind.etDob.getText()).toString();
        String status = Objects.requireNonNull(bind.etStatus.getText()).toString();
        String address = Objects.requireNonNull(bind.etAddress.getText()).toString();

        presenter.createData(name, gender, pob, dob, status, address);
    }

    public void callApiUpdate() {
        String name = Objects.requireNonNull(bind.etName.getText()).toString();
        String gender = Objects.requireNonNull(bind.etGender.getText()).toString();
        String pob = Objects.requireNonNull(bind.etPob.getText()).toString();
        String dob = Objects.requireNonNull(bind.etDob.getText()).toString();
        String status = Objects.requireNonNull(bind.etStatus.getText()).toString();
        String address = Objects.requireNonNull(bind.etAddress.getText()).toString();

        presenter.updateData(id, name, gender, pob, dob, status, address);
    }

    @Override
    public void onSuccess(ResponseModel data) {
        mEmployee = data.getData();
        setAdapter();
    }

    @Override
    public void onSuccessCreate() {
        Toast.makeText(this, "Create Data Success", Toast.LENGTH_SHORT).show();
        onRefresh();
    }

    @Override
    public void onSuccessUpdate() {
        Toast.makeText(this, "Update Data Success", Toast.LENGTH_SHORT).show();
        onRefresh();
    }

    @Override
    public void onSuccessDelete() {
        Toast.makeText(this, "Delete Data Success", Toast.LENGTH_SHORT).show();
        onRefresh();
    }

    @Override
    public void onError() {
        Log.e("EmployeeModel", "");
    }

    @Override
    public void onErrorCreated() {
        onRefresh();
    }

    @Override
    public void onErrorUpdate() {
        onRefresh();
    }

    @Override
    public void onErrorDelete() {
        onRefresh();
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    public void onItemClick(EmployeeModel data) {
        id = data.getId();
        bind.etName.setText(data.getName());
        bind.etGender.setText(data.getGender());
        bind.etPob.setText(data.getPlaceOfBirth());
        bind.etDob.setText(data.getDateOfBirth());
        bind.etStatus.setText(data.getStatus());
        bind.etAddress.setText(data.getAddress());

        bind.btnSave.setVisibility(View.GONE);
        bind.btnUpdate.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemDelete(int id) {
        presenter.deleteData(id);
    }
}