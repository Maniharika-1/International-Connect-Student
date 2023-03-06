package com.example.studentinternationalconnect.domain.util;

import com.google.firebase.database.DataSnapshot;

public interface GetDataListener {

    void onSuccess(DataSnapshot snapshot);
    void onStart();
    void onFailure();

}
