package com.lrb.extract.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lrb.extract.R;
import com.lrb.test.InfoBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private List<InfoBean.DatasBean> datas = new ArrayList<>();
    private Context context;


    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<InfoBean.DatasBean> datas) {

        this.datas.addAll(datas);
        notifyDataSetChanged();

    }

    public void refresh(List<InfoBean.DatasBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        InfoBean.DatasBean datasBean = datas.get(i);
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(datasBean.getAvatar()).apply(requestOptions).into(viewHolder.iv_img);
        viewHolder.tv_title.setText(datasBean.getTitle());
        viewHolder.tv_desc.setText(datasBean.getAuthor());





    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         ImageView iv_img;
         TextView tv_title;
         TextView tv_desc;
         Button btn_login;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img=itemView.findViewById(R.id.iv_img);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_desc=itemView.findViewById(R.id.tv_desc);
            btn_login=itemView.findViewById(R.id.btn_login);
        }
    }
}
