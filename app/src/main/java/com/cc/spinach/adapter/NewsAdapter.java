package com.cc.spinach.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.spinach.R;
import com.cc.spinach.model.NewModel;
import com.cc.spinach.net.PicassoUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/23 15:20
 * 邮箱：971859818@qq.com
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;


    private List<NewModel> mData;
    private boolean mShowFooter = true;

    private OnItemClickListener mOnItemClickListener;


    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setDate(List<NewModel> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news, parent, false);
            ItemViewHolder vh = new ItemViewHolder(view);
            return vh;
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_footer, parent, false);
            view.setLayoutParams(new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            NewModel newsBean = mData.get(position);
            if (newsBean == null) {
                return;
            }
            ((ItemViewHolder) holder).mTitle.setText(newsBean.getTitle());
            ((ItemViewHolder) holder).mDesc.setText(newsBean.getDigest());
            PicassoUtils.display(context,((ItemViewHolder) holder).mNewsImg,newsBean.getImgsrc());
        }
    }


    @Override
    public int getItemCount() {
        int begin = mShowFooter ? 1 : 0;
        if (mData == null) {
            return begin;
        }
        return mData.size() + begin;
    }

    public NewModel getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.ivNews)
        ImageView mNewsImg;
        @Bind(R.id.tvTitle)
        TextView mTitle;
        @Bind(R.id.tvDesc)
        TextView mDesc;

        public ItemViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
            v.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, this.getAdapterPosition());
            }
        }
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public boolean isShowFooter() {
        return mShowFooter;
    }

    public void setShowFooter(boolean mShowFooter) {
        this.mShowFooter = mShowFooter;
        this.notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}
