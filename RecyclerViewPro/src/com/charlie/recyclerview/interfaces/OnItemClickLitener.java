package com.charlie.recyclerview.interfaces;

import android.view.View;

public interface OnItemClickLitener
{
    void onItemClick(View view, int position);
    void onItemLongClick(View view , int position);
}
