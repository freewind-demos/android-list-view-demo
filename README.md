# Android ListView 列表演示

## 简介

本 Demo 演示 Android ListView 的基本用法。

## 基本原理

ListView 是 Android 中用于显示垂直列表的组件。

## 教程

```kotlin
val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
listView.adapter = adapter
listView.setOnItemClickListener { _, _, position, _ ->
    // 处理点击
}
```

## 注意事项

1. 推荐使用 RecyclerView 替代
2. 使用 ViewHolder 优化性能
