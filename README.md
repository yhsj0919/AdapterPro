# AdapterPro
整理的bingoogolapple的开源库，源地址 https://github.com/bingoogolapple/BGAAdapter-Android

新增了tablayout的adapter和帮助类。

BaseListViewAdapter适用于list，GridView

BaseRecyclerViewAdapter 适用于RecyclerView

BaseTabLayoutAdapter 适用于tablayout和ViewPager的组合

```java

//不带自定义tab

tabLayoutHelper.bindTab(context, tabLayout, viewPager);

//带自定义tab

tabLayoutHelper.bindTab(tabLayout, viewPager,adapter);

//添加，删除

 tabLayoutHelper.addFragment(new Fragment_Test(), "待办");
 tabLayoutHelper.addFragment(new Fragment_Test(), "消息");
 tabLayoutHelper.addFragment(new Fragment_Test(), "联系人");
 tabLayoutHelper.addFragment(new Fragment_Test(), "我的");
 tabLayoutHelper.removeFragment("我的");

```