# AdapterPro
整理的bingoogolapple的开源库，源地址 https://github.com/bingoogolapple/BGAAdapter-Android

新增了tablayout的adapter和帮助类。

```java

//不带自定义tab

tabLayoutHelper.bindTab(context, tabLayout, viewPager);

//带自定义tab

tabLayoutHelper.bindTab(tabLayout, viewPager,adapter);

```