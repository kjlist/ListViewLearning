package as.laoli.com.listviewlearning;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by laoli on 16/4/29.
 */
public class ReflashListView extends ListView {
    View header;
    int headerheight;//顶部布局文件高度
    public ReflashListView(Context context) {
        super(context);
        init(context);
    }

    public ReflashListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public ReflashListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public ReflashListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
//初始化界面,添加顶部布局文件到listView
    private void init(Context context) {
        header= LayoutInflater.from(context).inflate(R.layout.head_layout,null);
        measureView(header);
        headerheight=header.getMeasuredHeight();
        topPadding(-headerheight);
        this.addHeaderView(header);
    }
    //通知父布局,占用宽和高
    private void measureView(View view){
        ViewGroup.LayoutParams p=view.getLayoutParams();
        if (p==null){
            p=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width=ViewGroup.getChildMeasureSpec(0,0,p.width);
        int height;
        int tempHeight=p.height;
        if (tempHeight>0){
            height=MeasureSpec.makeMeasureSpec(tempHeight,MeasureSpec.EXACTLY);
        }
        else {
            height=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        }
        view.measure(width,height);

    }
    //设置header布局的上边距
    private void topPadding(int topPadding){
        header.setPadding(header.getPaddingLeft(),topPadding,header.getPaddingRight(),header.getPaddingBottom());
        header.invalidate();
    }
}
