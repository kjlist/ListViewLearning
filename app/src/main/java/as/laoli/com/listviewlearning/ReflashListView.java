package as.laoli.com.listviewlearning;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by laoli on 16/4/29.
 */
public class ReflashListView extends ListView implements AbsListView.OnScrollListener{
    View header;
    int headerheight;//顶部布局文件高度
    int firstVisibleItem;//当前第一个可见item的位置
    boolean isRemark;
    int startY;
    int state;
    int scrollState;
    final int NONE=0;
    final int PULL=1;
    final int RELESE=2;
    final int REFLASHING=3;

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
        this.setOnScrollListener(this);
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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        this.scrollState=scrollState;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (firstVisibleItem==0){
                    if (isRemark){

                    }
                    else {
                        isRemark=true;
                        startY=(int)ev.getY();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                OnMove(ev);
                break;
            case MotionEvent.ACTION_UP:
                if (state==RELESE){
                    state=REFLASHING;
                    //加载最新数据;
                }
                else if (state==PULL){
                    state=NONE;
                    isRemark=false;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
    //判断移动过程中的操作
    private void OnMove(MotionEvent ev){
        if (isRemark){
            int tempY= (int) ev.getY();
            int space=tempY-startY;
            int topPadding=space-headerheight;
            switch (state){
                case NONE:
                    if (space>0){
                        state=PULL;
                    }
                    reflashViewByState();
                    break;
                case PULL:
                    topPadding(topPadding);
                    if (space>headerheight+30&&scrollState==SCROLL_STATE_TOUCH_SCROLL){
                        state=RELESE;
                    }
                    reflashViewByState();

                    break;
                case RELESE:
                    topPadding(topPadding);
                    if (space<headerheight+30){
                        state=PULL;
                    }
                    else if (space<=0){
                        state=NONE;
                        isRemark=false;
                    }
                    reflashViewByState();

                    break;
                case REFLASHING:
                    reflashViewByState();
                    break;
            }
        }
        else {
            return;
        }
    }
    //根据当前状态改变界面显示
    private void reflashViewByState(){
        TextView tip= (TextView) header.findViewById(R.id.tip);
        ProgressBar progress= (ProgressBar) header.findViewById(R.id.progress);
        switch (state){
            case NONE:
               topPadding(-headerheight);
                break;
            case PULL:

                break;
            case RELESE:

                break;
            case REFLASHING:
                break;
        }
    }
    public void reflashComplete(){
        state=NONE;
        isRemark=false;
        reflashViewByState();
    }
}
