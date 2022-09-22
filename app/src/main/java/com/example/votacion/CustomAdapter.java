package com.example.votacion;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by arthonsystechnologiesllp on 10/03/17.
 */

public class CustomAdapter extends BaseAdapter {

    Activity activity;
    List<UserModel> users;
    LayoutInflater inflater;

    //short to create constructer using command+n for mac & Alt+Insert for window


    public CustomAdapter(Activity activity) {
        this.activity = activity;
    }

    public CustomAdapter(Activity activity, List<UserModel> users) {
        this.activity   = activity;
        this.users      = users;

        inflater        = activity.getLayoutInflater();
    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null){

            view = inflater.inflate(R.layout.list_view, viewGroup, false);

            holder = new ViewHolder();

            holder.tvUserName = (TextView)view.findViewById(R.id.TV_name);
            holder.tv_casilla =(TextView)view.findViewById(R.id.TV_casilla);
            holder.ivCheckBox = (ImageView) view.findViewById(R.id.iv_check_box);
            holder.iv_candidato = (ImageView) view.findViewById(R.id.iv_foto_candi);

            view.setTag(holder);
        }else
            holder = (ViewHolder)view.getTag();

        UserModel model = users.get(i);

        holder.tvUserName.setText(model.getUserName());
        holder.tv_casilla.setText(model.getCasilla());
        holder.iv_candidato.setImageResource(model.getImagen());

        if (model.isSelected())
            holder.ivCheckBox.setBackgroundResource(R.drawable.checked);

        else
            holder.ivCheckBox.setBackgroundResource(R.drawable.check);

        return view;

    }

    public void updateRecords(List<UserModel>  users){
        this.users = users;

        notifyDataSetChanged();
    }

    class ViewHolder{

        TextView tvUserName,tv_casilla;
        ImageView ivCheckBox,iv_candidato;

    }
}
