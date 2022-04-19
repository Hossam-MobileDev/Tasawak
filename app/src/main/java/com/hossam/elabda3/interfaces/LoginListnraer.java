package com.hossam.elabda3.interfaces;

import com.hossam.elabda3.models.UserRoot;

public interface LoginListnraer {
    void onLoginSuccess(UserRoot.User user);

    void onDismiss();

    void onFailure();
}
