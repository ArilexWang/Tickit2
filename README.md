

# TICKIT

## 混合开发

---

GITHUB地址：

https://github.com/ArilexWang/Tickit2

### 项目概述

------

> #### 项目目的

- 实现安卓端票务线上平台

> #### 开发环境

- android studio：compileSdkVersion  26
- 虚拟机 Nexus 5X API 26
- Django  1.11.3
  - Python3.6
  - Ubuntu16.04

> #### 基本需求

- 管理员实时推出最新票务/文案信息
- 用户线上购票退票以及定时抢票
- 摇一摇功能

### 功能详情

> ### V

- 开源库

  - Fragment对比

    [AdvancedPagerSlidingTabStrip](https://github.com/HomHomLin/AdvancedPagerSlidingTabStrip).`implementation 'homhomlin.lib:apsts:1.8.0'`

  - Image

    [FrescoImageView](https://github.com/HomHomLin/FrescoImageView)`implementation 'homhomlin.lib:frescoimageview:1.2.0'`

    [fresco](https://github.com/facebook/fresco)`implementation 'com.facebook.fresco:fresco:0.12.0'`

  - Material View Pager

    [MaterialViewPager](https://github.com/florent37/MaterialViewPager)`implementation 'com.github.florent37:materialviewpager:1.2.3'`

    附带封装好的toolbar & menu

    动画收缩等效果

  - SearchBar

    [SearchBar](https://github.com/mancj/MaterialSearchBar)`implementation 'com.github.mancj:MaterialSearchBar:0.7.5'`

    Suggestion & menu 封装

  - Banner

    [Banner](https://github.com/youth5201314/banner)`implementation 'com.youth.banner:banner:1.4.10'`

  - Camera

    [CameraFragment](https://github.com/florent37/CameraFragment)`implementation 'com.github.florent37:camerafragment:1.0.8'`


  - Material Design 

    ​

  > #### M

  - 服务器搭建

  - 后端框架

  - 正则表达式搜索

    ​

  > #### P

  - 对管理员的设置

  - greendao

  - 拍照界面以及摇一摇界面

  - 开源库

    - okhttp

    - rxjava2

    - retroift

    - glide

    - Django + uWSGI + nginxc

      ​

  ​

  > #### 接口

  - 电话

    用于系统服务部分

  - 本地相册/相机

    用于用户图像更换等

  - 传感器 

    加速度传感器加监听

    当监测到的运动幅度足够大便触发重载的onSensorChanged函数进行相应操作

    用于摇一摇功能

  - web

    用于票务详情介绍部分

  ### 流程概述

  > #### 用户使用流程

  ​

  ![用户](/Users/yuhanyin/Documents/TJ/ANDROID Development/TICKIT1.11/用户.png)

  ​

  > #### 管理员使用流程

  ![管理员](/Users/yuhanyin/Documents/TJ/ANDROID Development/TICKIT1.11/管理员.png)

  ​


## ScreenShot

![1](/Users/yuhanyin/AndroidStudioProjects/TICKIT666/img/1.png)

![2](/Users/yuhanyin/AndroidStudioProjects/TICKIT666/img/2.png)

![3](/Users/yuhanyin/AndroidStudioProjects/TICKIT666/img/3.png)

![4](/Users/yuhanyin/AndroidStudioProjects/TICKIT666/img/4.png)

![5](/Users/yuhanyin/AndroidStudioProjects/TICKIT666/img/5.png)

![6](/Users/yuhanyin/AndroidStudioProjects/TICKIT666/img/6.png)

![7](/Users/yuhanyin/AndroidStudioProjects/TICKIT666/img/7.png)

![8](/Users/yuhanyin/AndroidStudioProjects/TICKIT666/img/8.png)




## Statement

该项目仅供交流学习使用，如果该项目有侵犯版权问题，或被告知需停止共享与使用，本人会及时删除此页面与整个项目。

## 感谢


* [RXJava](https://github.com/ReactiveX/RxJava)
* [okhttp](https://github.com/square/okhttp)
* [retrofit](https://github.com/square/retrofit)
* [glide](https://github.com/bumptech/glide)


