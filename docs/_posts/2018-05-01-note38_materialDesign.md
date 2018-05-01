---
layout: post
title:  "note38_materialDesign"
date:   2014-11-17 13:31:01 +0800
categories: android
tag: android
---

* content
{:toc}
##安卓5.0新出来特性

#TextInputLayout
- 包括了一个EditText控件
- 里面的EditText使用setError();会弹出提示框

#DrawerLayout
- 抽屉布局:主题和侧滑部分

#CoordinatorLayout
- 协调者布局,用于协调5.0界面中新控件的交互的

#AppBarLayout
- 用于构建Toolbar的布局
- 内部要放一个Toolbar

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置支持toolbar
        setSupportActionBar(toolbar);
- 在onCreateOptionsMenu中创建菜单

     	getMenuInflater().inflate(R.menu.main, menu);
- 在xml中添加不同menuItem
- 在onOptionsItemSelected中实现点击事件

#FloatingActionButton
- 浮动动作按键
- Snackbar:底部弹出菜单

         Snackbar.make(view,
             "真的要这样么？", Snackbar.LENGTH_LONG)
             .show();

- 通过setAction设置事件

     setAction("是的", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        })
