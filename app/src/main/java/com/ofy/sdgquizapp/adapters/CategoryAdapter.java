package com.ofy.sdgquizapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ofy.sdgquizapp.Constant;
import com.ofy.sdgquizapp.R;
import com.ofy.sdgquizapp.activity.LevelActivity;
import com.ofy.sdgquizapp.activity.SubcategoryActivity;
import com.ofy.sdgquizapp.helper.AppController;
import com.ofy.sdgquizapp.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemRowHolder> {



    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private List<Category> dataList;
    private Context mContext;

    public CategoryAdapter(Context context, ArrayList<Category> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }


    @Override
    public CategoryAdapter.ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_category_item, parent, false);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ItemRowHolder holder, final int position) {

        final Category category = dataList.get(position);
//        holder.image.setDefaultImageResId(R.drawable.ic_launcher);
        holder.image.setImageUrl(category.getImage(), imageLoader);
        //when item is clicked
        holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constant.CATE_ID = Integer.parseInt(category.getId());
                        Constant.cate_name = category.getName();
                        if (!category.getNoOfCate().equals("0")) {
                            Intent intent = new Intent(mContext, SubcategoryActivity.class);
                            mContext.startActivity(intent);
                        } else {

                            if (category.getMaxLevel() == null) {
                                Constant.TotalLevel = 0;
                            } else if (category.getMaxLevel().equals("null")) {
                                Constant.TotalLevel = 0;
                            } else {
                                Constant.TotalLevel = Integer.parseInt(category.getMaxLevel());
                            }
                            Intent intent = new Intent(mContext, LevelActivity.class);
                            intent.putExtra("fromQue", "cate");
                            mContext.startActivity(intent);
                        }

                    }
                });
    }

    @Override
    public int getItemCount() {

        return this.dataList!=null ? dataList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class ItemRowHolder extends RecyclerView.ViewHolder {
        public NetworkImageView image;
        public ViewGroup layout;

        public ItemRowHolder(View itemView) {
            super(itemView);
            image =  itemView.findViewById(R.id.ivCategoryImg);
            layout = itemView.findViewById(R.id.cat_layout);
        }
    }
}
