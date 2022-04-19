package com.hossam.elabda3.activites;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.hossam.elabda3.R;
import com.hossam.elabda3.adapters.WishlistAdapter;
import com.hossam.elabda3.databinding.ActivityWishlistBinding;
import com.hossam.elabda3.models.ProductItem;
import com.hossam.elabda3.popups.ConfirmationPopup;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends BaseActivity {
    ActivityWishlistBinding binding;
    WishlistAdapter wishlistAdapter;
    private List<ProductItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wishlist);


        getWishlistData();
    }

    private void getWishlistData() {
        wishlistAdapter = new WishlistAdapter();
        binding.rvWishlist.setAdapter(wishlistAdapter);
        list = sessionManager.getWishlist();

        wishlistAdapter.addData(list);
        wishlistAdapter.setOnWishlistClickListnear((product, position) -> {
            new ConfirmationPopup(this, getString(R.string.deleteproduct), getString(R.string.deleteconfirlmsg), new ConfirmationPopup.OnPopupClickListner() {
                @Override
                public void onPositive() {
                    removeWishlistItem(product, position);
                }

                @Override
                public void onNegative() {

                }
            }, "Yes", "No");


        });
        if (list.isEmpty()) {
            binding.lytEmpty.setVisibility(View.VISIBLE);
        }
    }

    private void removeWishlistItem(ProductItem product, int position) {
        sessionManager.toggleWishlist(product);
        wishlistAdapter.removeItem(position);
        list.remove(position);
        if (list.isEmpty()) {
            binding.lytEmpty.setVisibility(View.VISIBLE);
        }
    }

    public void onClickBack(View view) {
        onBackPressed();
    }
}