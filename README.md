# Android ListView 列表演示

## 简介

本 Demo 演示 Android ListView 的基本用法，展示如何创建垂直列表并处理点击事件。

## 基本原理

ListView 是 Android 中用于显示垂直滚动列表的组件，是早期 Android 开发中最常用的列表组件。它继承自 AbsListView，通过 Adapter 模式提供数据。

ListView 的核心概念：
- **Adapter**：数据适配器，负责提供每个 item 的视图和数据
- **ViewHolder**：优化列表滚动性能的机制
- **LayoutInflater**：将 XML 布局转换为视图对象

**注意**：ListView 已在 2014 年被标记为过时，官方推荐使用 RecyclerView 替代。

## 启动和使用

### 环境要求
- Android Studio
- JDK 17
- Gradle 8.x

### 安装和运行

1. 用 Android Studio 打开项目
2. 连接 Android 设备或模拟器
3. 点击 Run 运行

### 使用方法
- 上下滑动查看列表
- 点击列表项查看 Toast 提示

## 教程

### 什么是 ListView？

ListView 是 Android 提供的垂直列表组件，用于显示可滚动的数据列表。它的设计遵循 MVC 模式：
- Model（模型）：数据列表
- View（视图）：ListView 组件和 item 布局
- Controller（控制器）：Adapter

### 基本用法

1. 在布局中添加 ListView：

```xml
<ListView
    android:id="@+id/listView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

2. 准备数据并设置 Adapter：

```kotlin
val listView = findViewById<ListView>(R.id.listView)
val items = listOf("苹果", "香蕉", "橙子", "葡萄")

val adapter = ArrayAdapter(
    this,
    android.R.layout.simple_list_item_1,
    items
)
listView.adapter = adapter
```

3. 设置点击监听：

```kotlin
listView.setOnItemClickListener { _, _, position, _ ->
    Toast.makeText(this, "点击了: ${items[position]}", Toast.LENGTH_SHORT).show()
}
```

### ArrayAdapter

ArrayAdapter 是最简单的适配器，适用于简单的文本列表：

```kotlin
// 使用系统提供的布局
ArrayAdapter(context, android.R.layout.simple_list_item_1, data)

// 使用自定义布局
ArrayAdapter(context, R.layout.item_layout, data)
```

### 自定义 Adapter

对于复杂的列表项，需要自定义 BaseAdapter：

```kotlin
class MyAdapter(private val data: List<String>) : BaseAdapter() {
    override fun getCount(): Int = data.size
    override fun getItem(position: Int): String = data[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        view.findViewById<TextView>(R.id.textView).text = data[position]
        return view
    }
}
```

### ViewHolder 优化

使用 ViewHolder 可以避免重复调用 findViewById，提高性能：

```kotlin
override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val holder: ViewHolder
    val view = if (convertView == null) {
        holder = ViewHolder()
        view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        holder.textView = view.findViewById(R.id.textView)
        view.tag = holder
        view
    } else {
        view = convertView
        holder = view.tag as ViewHolder
    }
    holder.textView.text = data[position]
    return view
}

class ViewHolder {
    lateinit var textView: TextView
}
```

### 注意事项

1. **过时组件**：新项目应使用 RecyclerView 替代 ListView
2. **性能优化**：大量数据时必须使用 ViewHolder 模式
3. **分页加载**：大量数据建议实现分页加载
4. **点击事件**：setOnItemClickListener 处理 item 点击

## 关键代码详解

### MainActivity.kt

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstance state: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_main)

        // 1. 获取 ListView 组件
        val listView = findViewById<ListView>(R.id.listView)

        // 2. 准备数据列表
        val items = listOf(
            "苹果", "香蕉", "橙子", "葡萄",
            "西瓜", "草莓", "桃子", "梨子"
        )

        // 3. 创建 ArrayAdapter
        // 参数1: 上下文
        // 参数2: item 布局（simple_list_item_1 是系统提供的单行文本布局）
        // 参数3: 数据列表
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            items
        )

        // 4. 将 Adapter 设置到 ListView
        listView.adapter = adapter

        // 5. 设置 item 点击监听器
        // 参数说明：parent-ListView，view-点击的视图，position-位置，id-行id
        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this,
                "点击了: ${items[position]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
```

### activity_main.xml

```xml
<!-- 根布局：垂直线性布局 -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- 标题 -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="ListView 列表演示"
        android:textSize="20sp"
        android:textStyle="bold"
        android:gravity="center"
        android:paddingBottom="16dp" />

    <!-- ListView 组件 -->
    <ListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>
```
