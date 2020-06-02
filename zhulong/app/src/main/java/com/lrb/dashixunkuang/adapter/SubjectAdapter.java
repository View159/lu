package com.lrb.dashixunkuang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.lrb.dashixunkuang.R;
import com.lrb.dashixunkuang.view.design.RoundImage;
import com.lrb.infobean.SpecialtyChooseEntity;
import com.yiyatech.utils.newAdd.GlideUtil;

import java.util.List;


/**
 * Created by 任小龙 on 2020/6/2.
 */
public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    private List<SpecialtyChooseEntity> mList;
    private Context mContext;

    public SubjectAdapter(List<SpecialtyChooseEntity> pList, Context pContext) {
        mList = pList;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_child_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpecialtyChooseEntity entity = mList.get(position);
        GlideUtil.loadImage(holder.leftRoundImage,entity.getIcon());
        holder.itemTitle.setText(entity.getBigspecialty());
        holder.itemRecyclerview.setLayoutManager(new GridLayoutManager(mContext,4));
        holder.itemRecyclerview.setAdapter(new SubjectChildAdapter(entity.getData(),mContext,this));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundImage leftRoundImage;
        TextView itemTitle;
        RecyclerView itemRecyclerview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            leftRoundImage=itemView.findViewById(R.id.left_round_image);
            itemTitle=itemView.findViewById(R.id.item_title);
            itemRecyclerview=itemView.findViewById(R.id.item_recyclerview);

        }
    }
}
