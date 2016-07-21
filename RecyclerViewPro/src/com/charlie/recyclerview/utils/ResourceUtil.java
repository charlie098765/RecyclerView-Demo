package com.charlie.recyclerview.utils;


import java.lang.reflect.Field;

import android.content.Context;

public  class ResourceUtil {

	public static int getLayoutId(Context paramContext, String paramString) {
		return paramContext.getResources().getIdentifier(paramString, "layout",
				paramContext.getPackageName());
	}

	public static int getStringId(Context paramContext, String paramString) {
		return paramContext.getResources().getIdentifier(paramString, "string",
				paramContext.getPackageName());
	}

	public static int getDrawableId(Context paramContext, String paramString) {
		return paramContext.getResources().getIdentifier(paramString,
				"drawable", paramContext.getPackageName());
	}

	public static int getStyleId(Context paramContext, String paramString) {
		return paramContext.getResources().getIdentifier(paramString, "style",
				paramContext.getPackageName());
	}


	public static final int getId(Context paramContext, String paramString) {
		return paramContext.getResources().getIdentifier(paramString, "id",
				paramContext.getPackageName());
	}

	public static int getColorId(Context paramContext, String paramString) {
		return paramContext.getResources().getIdentifier(paramString, "color",
				paramContext.getPackageName());
	}
	
	public static int getDimenId(Context paramContext, String paramString) {
		return paramContext.getResources().getIdentifier(paramString, "dimen",
				paramContext.getPackageName());
	}
	
	public static int getAnimId(Context paramContext, String paramString) {
		return paramContext.getResources().getIdentifier(paramString, "anim",
				paramContext.getPackageName());
	}
	public static int getXmlId(Context paramContext, String paramString) {
		return paramContext.getResources().getIdentifier(paramString, "xml",
				paramContext.getPackageName());
	}
	
	
	/**对于context.getResources().getIdentifier无法获取的数据,或者数组资源反射值*/
	private static Object getResourceId(Context context,String name, String type) {
	
		String className = context.getPackageName() +".R";
	
		try {
		
			Class<?> cls = Class.forName(className);
		
			for (Class<?> childClass : cls.getClasses()) {
		
			String simple = childClass.getSimpleName();
		
			if (simple.equals(type)) {
			
				for (Field field : childClass.getFields()) {
				
					String fieldName = field.getName();
				
					if (fieldName.equals(name)) {
				
						System.out.println(fieldName);
					
						return field.get(null);
				
					}
				
				}
			
			}
		
			}
		
		} catch (Exception e) {
	
			e.printStackTrace();
	
		}
		return null;

	}
	/**context.getResources().getIdentifier无法获取到styleable的数据 */
	public static int getStyleableId(Context context, String name) {

		return ((Integer)getResourceId(context, name,"styleable")).intValue();

	}

	 

	/** 获取styleable的ID号数组*/
	public static int[] getStyleableArray(Context context,String name) {

		return (int[])getResourceId(context, name,"styleable");

	}



}
