package as.laoli.com.listviewlearning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by laoli on 16/4/29.
 */
public class MyAdapter extends BaseAdapter {
    LayoutInflater mInflate;
    List<item_bean>mDatas;
    public MyAdapter(Context context, List<item_bean> mDatas) {
        mInflate =LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView=mInflate.inflate(R.layout.item_lv,null);
            holder=new ViewHolder();
            holder.image_icon= (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.title= (TextView) convertView.findViewById(R.id.tv_title);
            holder.content= (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.image_icon.setImageResource(mDatas.get(position).imageId);
        holder.title.setText(mDatas.get(position).title);
        holder.content.setText(mDatas.get(position).title);
        return convertView;
    }
    class ViewHolder{
        public ImageView image_icon;
        public TextView title;
        public TextView content;

        public ViewHolder() {
        }
    }
}
