package as.laoli.com.listviewlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
//1.添加提示界面
//监听滚动事件
//监听onTouch事件
//
public class MainActivity extends AppCompatActivity {
    private List<item_bean> mDatas;
    private ReflashListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ReflashListView) findViewById(R.id.listview);
        //下面一行容易忘记啊!!!!
        mDatas=new ArrayList<item_bean>();
        for (int i=0;i<10;i++){
        mDatas.add(new item_bean(R.mipmap.ic_launcher,"title"+i,"content"+i));
        }
        listView.setAdapter(new MyAdapter(this,mDatas));

    }
}
