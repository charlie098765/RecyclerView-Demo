package com.charlie.recyclerview.adapter;

import java.util.ArrayList;
import java.util.List;

import com.charlie.recyclerview.interfaces.OnItemClickLitener;
import com.charlie.recyclerview.utils.C;
import com.charlie.recyclerview.utils.ResourceUtil;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StaggeredDataAdapter extends RecyclerView.Adapter<StaggeredDataAdapter.CustomViewHolder>{
	private Context context;
	List<? extends Object> list;
	List<Integer> heigtsList;
	
	public StaggeredDataAdapter(Context context,List<? extends Object> list){
		this.context=context;
		this.list=list;
		heigtsList=new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			heigtsList.add(i,getRandom());
		}
		
	}
	private int getRandom() {
		return (int) (Math.random()*300+200);
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
			heigtsList.add(position, getRandom());
			list=listStr;
		}
		notifyItemInserted(position);
	}
	public void deleteItem(int position){
		if(list.get(position) instanceof Object){
			@SuppressWarnings("unchecked")
			List<Object> listStr=(List<Object>) list;
			listStr.remove(position);
			heigtsList.remove(position);
			list=listStr;
		}
		notifyItemRemoved(position);
	}
	


	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public void onBindViewHolder(final CustomViewHolder holder, int position) {
		if(holder.tv_item.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams){
			StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) holder.tv_item.getLayoutParams();
			lp.height=heigtsList.get(position);
			holder.tv_item.setLayoutParams(lp);
		}else if(holder.tv_item.getLayoutParams() instanceof LinearLayout.LayoutParams){
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.tv_item.getLayoutParams();
			lp.height=heigtsList.get(position);
			holder.tv_item.setLayoutParams(lp);
		}
		
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
                    int pos = holder.getPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getPosition();
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
