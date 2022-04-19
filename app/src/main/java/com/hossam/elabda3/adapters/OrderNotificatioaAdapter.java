package com.hossam.elabda3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hossam.elabda3.R;
import com.hossam.elabda3.VegiUtils;
import com.hossam.elabda3.databinding.ItemOrderNotificationBinding;
import com.hossam.elabda3.models.OrderNotificationRoot;

import java.util.ArrayList;
import java.util.List;

public class OrderNotificatioaAdapter extends RecyclerView.Adapter<OrderNotificatioaAdapter.NotificationViewHolder> {

    List<OrderNotificationRoot.DataItem> data = new ArrayList<>();
    private Context context;

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.setNotification(position);
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_notification, parent, false);
        return new NotificationViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addData(List<OrderNotificationRoot.DataItem> data) {

        this.data.addAll(data);
        notifyItemRangeInserted(this.data.size(), data.size());
    }


    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        ItemOrderNotificationBinding binding;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemOrderNotificationBinding.bind(itemView);
        }

        public void setNotification(int position) {
            OrderNotificationRoot.Order order = data.get(position).getOrder();
            OrderNotificationRoot.DataItem notification = data.get(position);
            binding.tvId.setText(order.getOrderId());
//            if (notification.getStartDelivery() ==6){
//                binding.tvNotification.setText("Your order is out for delivery");
//            }else {
            binding.tvNotification.setText("Your order is " + VegiUtils.getOrderStatus(notification.getStatus()));
//            }

            binding.tvdate.setText(order.getDate());
        }
    }
}
