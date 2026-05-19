package demos.android.list.view.demo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val listView = findViewById<ListView>(R.id.listView)
        val items = listOf("苹果", "香蕉", "橙子", "葡萄", "西瓜", "草莓", "桃子", "梨子")
        
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter
        
        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "点击了: ${items[position]}", Toast.LENGTH_SHORT).show()
        }
    }
}
