package com.charlie.recyclerview.activity;

import java.util.ArrayList;
import java.util.List;

import com.charlie.recyclerview.adapter.DataAdapter;
import com.charlie.recyclerview.adapter.StaggeredDataAdapter;
import com.charlie.recyclerview.customview.CustomRecyclerView;
import com.charlie.recyclerview.customview.CustomRecyclerView.OnItemScrollListener;
import com.charlie.recyclerview.customview.DividerGridItemDecoration;
import com.charlie.recyclerview.customview.DividerGridRVDecoration;
import com.charlie.recyclerview.customview.DividerItemDecoration;
import com.charlie.recyclerview.interfaces.OnItemClickLitener;
import com.charlie.recyclerview.utils.C;
import com.example.recyclerviewpro.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DataAdapter adapter;
	private StaggeredDataAdapter StaggeredAdapter;
	
	private List<String> list;
	private RecyclerView rv;
	private ImageView  iv_show_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        iv_show_detail= (ImageView) findViewById(R.id.iv_show_detail);
        rv= (RecyclerView) findViewById(R.id.rv_test);
        adapter=new  DataAdapter(MainActivity.this,list);
       StaggeredAdapter=new StaggeredDataAdapter(MainActivity.this,list);
       
    // 设置item动画
       rv.setItemAnimator(new DefaultItemAnimator());

     
      
    }
	public void initGridView(View v) {
//		rv.setLayoutManager(new GridLayoutManager(this,4,0,false));
		   //4行，横向排列
//       rv.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
		   //4列，纵向排列
       rv.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
       rv.setAdapter(adapter);
//		   rv.addItemDecoration(DividerGridRVDecoration.getInstance(MainActivity.this,rv));
		  
		   initEvent(adapter);
	}
	public void initStaggeredGridView(View v) {
//		rv.setLayoutManager(new GridLayoutManager(this,4,0,false));
		//4行，横向排列
//       rv.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
		//4列，纵向排列
		rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
		rv.setAdapter(StaggeredAdapter);
		rv.addItemDecoration(DividerGridRVDecoration.getInstance(MainActivity.this,rv));
		
		initEvent(StaggeredAdapter);
	}

	public void initListView(View v) {
		LinearLayoutManager manager=new LinearLayoutManager(this);
		manager.setOrientation(LinearLayoutManager.HORIZONTAL);
		rv.setLayoutManager(manager);
		rv.setAdapter(adapter);
//		rv.addItemDecoration(DividerItemDecoration.getInstance(this, rv,DividerItemDecoration.VERTICAL_LIST));
		initEvent(adapter);

	}
    private void initList() {
    	list=new ArrayList<String>();
		for(char i='A';i<='Z';i++){
			list.add(i+"");
		}
		
	}


    private void initEvent(Adapter mAdapter)
    {
    	OnItemClickLitener litener = new OnItemClickLitener() {
    		@Override
    		public void onItemClick(View view, int position)
    		{
    			Toast.makeText(MainActivity.this, position + " click",
    					Toast.LENGTH_SHORT).show();
    			iv_show_detail.setImageResource(C.ICONS[position]);
    		}
    		
    		@Override
    		public void onItemLongClick(View view, int position)
    		{
    			Toast.makeText(MainActivity.this, position + " long click",
    					Toast.LENGTH_SHORT).show();
    			iv_show_detail.setImageResource(C.ICONS[position]);
    		}
    	};
    	if(mAdapter instanceof DataAdapter){
    		DataAdapter	adapter=(DataAdapter)mAdapter;
    		adapter.setOnItemClickLitener(litener);
    	}else{
    		StaggeredDataAdapter adapter=((StaggeredDataAdapter)mAdapter);
    		adapter.setOnItemClickLitener(litener);
    		
    	}
    	
    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.item_add) {
        	adapter.addItem(1);
        	adapter.notifyDataSetChanged();//不加此行会变形
            return true;
        }
        if (id == R.id.item_delete) {
        	adapter.deleteItem(1);
        	adapter.notifyDataSetChanged();
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
