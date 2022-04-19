package com.hossam.elabda3.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hossam.elabda3.fragments.NotificationFragment;
import com.hossam.elabda3.fragments.OrderNotificationFragment;


public class NotificationPagerAdapter extends FragmentPagerAdapter {


    public NotificationPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NotificationFragment();
        } else {
            return new OrderNotificationFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Notifications";
        } else {
            return "Orders Updates";
        }
    }
}
