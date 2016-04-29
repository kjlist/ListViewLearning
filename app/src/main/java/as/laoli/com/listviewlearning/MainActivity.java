package as.laoli.com.listviewlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoadListView.ILoadListener{
    private List<item_bean> mDatas;
    private LoadListView listView;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (LoadListView) findViewById(R.id.listview);
//        下面这行也很重要;
        listView.setInterface(this);
        //下面一行容易忘记啊!!!!
        mDatas=new ArrayList<item_bean>();
        for (int i=0;i<10;i++){
        mDatas.add(new item_bean(R.mipmap.ic_launcher,"title"+i,"content"+i));
        }
        myAdapter=new MyAdapter(this,mDatas);
        listView.setAdapter(myAdapter);
    }

    @Override
    public void load() {
        //获取更多数据
        for (int i=44;i<46;i++){
            mDatas.add(new item_bean(R.mipmap.ic_launcher,"JIA DE title"+i,"content"+i));
        }
        // 通知ListView更新界面
        showListView(mDatas);
        listView.loadComplete();
    }

    private void showListView(List<item_bean> mDatas) {
        if (myAdapter==null){
            listView= (LoadListView) findViewById(R.id.listview);
            myAdapter=new MyAdapter(this,mDatas);
            listView.setAdapter(myAdapter);
        }
        else {
            myAdapter.OnDataChange(mDatas);
        }
    }
}
