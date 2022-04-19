package com.hossam.elabda3.adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hossam.elabda3.R;
import com.hossam.elabda3.databinding.ItemBannerBinding;
import com.hossam.elabda3.models.HomePageRoot;
import com.hossam.elabda3.retrofit.Const;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapterExample extends
        RecyclerView.Adapter<SliderAdapterExample.SliderAdapterVH> {


    private List<HomePageRoot.BannerItem> mSliderItems = new ArrayList<>();

    public SliderAdapterExample(List<HomePageRoot.BannerItem> mSliderItems) {

        this.mSliderItems = mSliderItems;
    }


    @NonNull
    @NotNull
    @Override
    public SliderAdapterVH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        HomePageRoot.BannerItem sliderItem = mSliderItems.get(position);


        Glide.with(viewHolder.itemView)
                .load(Const.IMAGE_URL + sliderItem.getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .fitCenter()

                .into(viewHolder.binding.img);
        Log.d("TAG", "onBindViewHolder:ll " + position);

    }

    @Override
    public int getItemCount() {
        return mSliderItems.size();
    }

    public void moveObj() {
        HomePageRoot.BannerItem o1 = mSliderItems.get(0);
        mSliderItems.remove(0);
        mSliderItems.add(mSliderItems.size(), o1);
        notifyDataSetChanged();
    }


    class SliderAdapterVH extends RecyclerView.ViewHolder {


        ItemBannerBinding binding;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            binding = ItemBannerBinding.bind(itemView);
        }
    }

}
