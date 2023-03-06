package com.id22.employees.core.utils;

public interface Presenter<T extends View> {
    void onAttach(T view);

    void onDetach();
}