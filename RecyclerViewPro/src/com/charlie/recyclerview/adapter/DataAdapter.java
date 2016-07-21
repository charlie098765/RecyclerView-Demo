package com.charlie.recyclerview.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.charlie.recyclerview.interfaces.OnItemClickLitener;
import com.charlie.recyclerview.utils.C;
import com.charlie.recyclerview.utils.ResourceUtil;

import android.R;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.CustomViewHolder>{
	private Context context;
	List<? extends Object> list;
/*	private static DataAdapter adapter;
	public static DataAdapter getInstance(Context context,List<? extends Object> list){
		if(adapter==null){
			adapter=new DataAdapter(context, list);
		}
		return adapter;
	}*/
	public DataAdapter(Context context,List<? extends Object> list){
		this.context=context;
		this.list=list;
	}
	
	class CustomViewHolder extends ViewHolder{
		private TextView tv_item;
		private ImageView iv_item;

		public CustomViewHolder(View view) {
			super(view);
			tv_item=(TextView) view.findViewById(ResourceUtil.getId(context, "tv_item"));
			iv_item=(ImageView) view.findViewById(ResourceUtil.getId(context, "iv_item"));
		}
		
	}
	@SuppressWarnings("unchecked")
	public void addItem(int position){
		if(list.get(position) instanceof Object){
			List<Object> listStr=(List<Object>) list;
			listStr.set(position, "hello world"+position);
			list=listStr;
			notifyItemInserted(position);
		}
	}
	public void deleteItem(int position){
		if(list.get(position) instanceof Object){
			@SuppressWarnings("unchecked")
			List<Object> listStr=(List<Object>) list;
			listStr.remove(position);
			list=listStr;
			notifyItemRemoved(position);
		}
	}
	


	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public void onBindViewHolder(final CustomViewHolder holder, int position) {
		holder.tv_item.setText(list.get(position).toString()+" "+position);
		holder.iv_item.setBackgroundResource(C.ICONS[position]);
		
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return true;
                }
            });
        }
    
	}

	@Override
	public CustomViewHolder onCreateViewHolder(ViewGroup parent, int position) {
		View view = LayoutInflater.from(context).inflate( ResourceUtil.getLayoutId(context, "recyclerview_item"), parent,false);
		CustomViewHolder cvh=new CustomViewHolder(view);
		
		return cvh;
	}


    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
