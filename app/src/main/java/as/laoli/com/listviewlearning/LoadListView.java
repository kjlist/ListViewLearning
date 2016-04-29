package as.laoli.com.listviewlearning;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by laoli on 16/4/29.
 * 滚动没有反应啊?!哪里出问题了
 */
public class LoadListView extends ListView implements AbsListView.OnScrollListener{
    int totalItemCount;
    int lastVisibleItem;
    View footer;
    boolean isLoading;
    ILoadListener iLoadListener;
    public LoadListView(Context context) {
        super(context);
        init(context);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
//添加底部加载提示布局
    private void init(Context context) {
        //下面两行的意思???
        footer=LayoutInflater.from(context).inflate(R.layout.footer_layout,null);
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
//        footer.setVisibility(View.GONE);
        //上面两者的区别?
        this.addFooterView(footer);
        Log.d("worinige","初始化");

    }
    //完毕后隐藏
    public void loadComplete(){
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
        isLoading=false;
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount==lastVisibleItem&&scrollState==SCROLL_STATE_IDLE){
            Log.d("TAG","滚动到底部了");
            //加载更多数据
            if (!isLoading){
//            footer.setVisibility(View.VISIBLE);
                footer.findViewById(R.id.load_layout).setVisibility(View.VISIBLE);
                isLoading=true;
            }
        }
    }
    //加载更多数据需要采用接口回调的方式,注意思考
    public interface ILoadListener{
        //加载更多数据的回调接口
        public void load();
    }
    public void setInterface(ILoadListener iLoadListener){
        this.iLoadListener=iLoadListener;
    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem=firstVisibleItem+visibleItemCount;
        this.totalItemCount=totalItemCount;
        Log.d("worinige","滚动中");

    }
}
