package com.charlie.recyclerview.customview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.View;

public class CustomRecyclerView extends RecyclerView {
	//记住第一张的位置
	private View mCurrentView;
	
	public CustomRecyclerView(Context context){
		this(context,null);
	}
	public CustomRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				View mNewCurrentView=getChildAt(0);
				if(mItemScrollListener!=null){
					if(mNewCurrentView!=null&&mCurrentView!=null){
						mCurrentView=mNewCurrentView;
						mItemScrollListener.onItemScrollChanged(mCurrentView, getChildPosition(mCurrentView));
					}
				}
				
				
				
			}
		});
	}
	public interface OnItemScrollListener{
		void onItemScrollChanged(View view,int position);
	}
	
	private OnItemScrollListener mItemScrollListener;
	public void setOnItemScrollListener(OnItemScrollListener mItemScrollListener){
		this.mItemScrollListener=mItemScrollListener;
	}
	
	

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		mCurrentView=getChildAt(0);
	}
	
	

	


}
