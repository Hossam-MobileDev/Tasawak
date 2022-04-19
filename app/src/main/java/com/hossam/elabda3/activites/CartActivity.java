package com.hossam.elabda3.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import com.hossam.elabda3.R;
import com.hossam.elabda3.adapters.CartAdapter;
import com.hossam.elabda3.dao.AppDatabase;
import com.hossam.elabda3.dao.CartOffline;
import com.hossam.elabda3.databinding.ActivityCartBinding;
import com.hossam.elabda3.interfaces.LoginListnraer;
import com.hossam.elabda3.models.UserRoot;
import com.hossam.elabda3.popups.ConfirmationPopup;
import com.hossam.elabda3.retrofit.Const;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class CartActivity extends BaseActivity {
    ActivityCartBinding binding;
    CartAdapter cartAdapter = new CartAdapter();
    private AppDatabase db;
    private double totalPrice = 0;
    CompositeDisposable disposable = new CompositeDisposable();
    List<String> product_ids = new ArrayList<>();
    private List<CartOffline> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);

        db = Room.databaseBuilder(this,
                AppDatabase.class, Const.DB_NAME).allowMainThreadQueries().build();
        cartAdapter.initAdapter(this);
        getCartData();
        initView();
    }

    private void getCartData() {
        list.clear();
        list = db.cartDao().getall();
        cartAdapter.addData(list);
        cartAdapter.setOnCartClickListnear(new CartAdapter.OnCartClickListnear() {
            @Override
            public void onNotify() {
                calculateTotal();
            }

            @Override
            public void onDelete(CartOffline product, int pos) {
                new ConfirmationPopup(CartActivity.this, getString(R.string.deleteproduct), getString(R.string.deleteconfirlmsg), new ConfirmationPopup.OnPopupClickListner() {
                    @Override
                    public void onPositive() {
                        deleteProduct(product, pos);
                    }

                    @Override
                    public void onNegative() {

                    }
                }, "Yes", "No");


            }
        });
        calculateTotal();
        if (list.isEmpty()) {
            binding.lyt404.setVisibility(View.VISIBLE);
        }
    }

    private void deleteProduct(CartOffline product, int pos) {


        AppDatabase db = Room.databaseBuilder(this,
                AppDatabase.class, Const.DB_NAME).allowMainThreadQueries().build();
        db.cartDao().deleteObjbyPid(product.getPriceUnitId());
        Log.d("TAG", "deleteProduct: " + product.getName());
        cartAdapter.removeItem(pos);
        list.remove(product);
        calculateTotal();
        if (list.isEmpty()) {
            binding.lyt404.setVisibility(View.VISIBLE);
        }
    }

    private void calculateTotal() {
        Log.d("TAG", "calculateTotal: ");
        totalPrice = 0;
        for (CartOffline product : db.cartDao().getall()) {

            product_ids.add(String.valueOf(product.getPid()));

            double p = Double.parseDouble(product.getPrice());
            long quantity = product.getQuantity();
            double price = p * quantity;
            Log.d("TAG", "calculateTotal: forrr " + price + "  qq " + quantity);
            totalPrice = (totalPrice + price);
        }


        binding.tvTotalAmount.setText(Const.getCurrency() + new DecimalFormat("###.##").format(totalPrice));

    }

    private void initView() {
        binding.rvCart.setAdapter(cartAdapter);
        binding.tvCheckout.setOnClickListener(v -> {
            if (list.isEmpty()) {
                Toast.makeText(this, "Your Cart Is Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (sessionManager.getBooleanValue(Const.IS_LOGIN)) {
                checkProductAvaiblity();


            } else {


                openLoginSheet(new LoginListnraer() {
                    @Override
                    public void onLoginSuccess(UserRoot.User u) {
                        startActivity(new Intent(CartActivity.this, DeliveryOptionsActivity.class));

                    }

                    @Override
                    public void onFailure() {

                    }

                    @Override
                    public void onDismiss() {

                    }
                });
            }


        });
    }

    private void checkProductAvaiblity() {
        startActivity(new Intent(CartActivity.this, DeliveryOptionsActivity.class));


    }

    public void onClickBack(View view) {
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cartAdapter.notifyDataSetChanged();
    }
}