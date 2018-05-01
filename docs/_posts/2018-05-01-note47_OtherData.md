---
layout: post
title:  "note47_Other_Data"
date:   2014-11-17 13:31:01 +0800
categories: android
tag: android
---

* content
{:toc}
#企业技术
- 登陆其他企业的开发者平台
- 注册账号
- 查看用户手册和使用指南
- 申请应用
- 下载sdk和demo
- 运行demo和将sdk植入到项目中

#获取第三的数据
- 聚合数据，直接使用案例代码获取第三方数据
- NoSql

#使用短信验证码
- 1，首先将aar和jar导入到工程中 
- 2，在Application中初始化

       SMSSDK.initSDK(this,AppKey,AppSercet);
- 3，注册回掉事件

     	SMSSDK.registerEventHandler(new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        //发送注册请求
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        Toast.makeText(LoginActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                }
            }
        });
- 4，发送获取验证码的请求，带上手机号

      	SMSSDK.getVerificationCode("86",et_tel.getText().toString());
- 5，发送获取的验证码进行验证
     ​    	
     SMSSDK.submitVerificationCode("86",et_tel.getText().toString(),et_code.getText().toString());
