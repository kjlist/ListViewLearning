package as.laoli.com.listviewlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<item_bean> mDatas;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.listview);
        //下面一行容易忘记啊!!!!
        mDatas=new ArrayList<item_bean>();
        for (int i=0;i<10;i++){
        mDatas.add(new item_bean(R.mipmap.ic_launcher,"title"+i,"content"+i));
        }
        listView.setAdapter(new MyAdapter(this,mDatas));

    }
}
