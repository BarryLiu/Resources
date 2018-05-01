---
layout: post
title:  "note41_RecyclerView"
date:   2014-11-17 13:31:01 +0800
categories: android
tag: android
---

* content
{:toc}
##RecyclerView.Adapter
- getItemCount:设置控件显示数量
- onCreateViewHolder:创建ViewHolder,通知创建视图,只会创建可见的View,而且每一栏只会创建一次

- onBindViewHolder:将数据存放到ViewHolder,从Holder中获取到控件,填充数据

##BaseAdatper
- getView():
- 两件事情
- 1,创建视图
- 2,将数据绑定到视图
- 两个优化
- 1,避免重复创建View
- 2,避免重复使用findViewId。创建一个ViewHolder,保存控件的引用的。将ViewHolder放入到被传递的convertView中

#实现点击事件
- 实现itemView的onClickListener方法
- 使用接口实现点击事件方法

        //1,创建接口
        public interface OnItemClickListener{
        public boolean onItemClick(View view,int position);
        }
        
        //2,创建引用
        OnItemClickListener onItemClickListener;
        //3,实现接受方法
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        }

      	//4,实现点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(holder.itemView,position);
                }
            }
        });

#界面的构架
- 使用BaseActivity避免重复代码
- 要在继承的BaseActivity中做到代码差异化

