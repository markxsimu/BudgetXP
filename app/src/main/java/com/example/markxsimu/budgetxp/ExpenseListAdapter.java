package com.example.markxsimu.budgetxp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by CarlosWalker on 2/18/18.
 */

public class ExpenseListAdapter extends ArrayAdapter<ExpenseEntry>{

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView Type;
        TextView Date;
        TextView ExpenseAmount;
    }

    /**
     * Default constructor for the ExpenseListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ExpenseListAdapter(Context context, int resource, ArrayList<ExpenseEntry> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String type = getItem(position).expType;
        double expense = getItem(position).expense;
        String date = getItem(position).date;

        //Create the person object with the information
        ExpenseEntry exp = new ExpenseEntry();
        exp.expType = type;
        exp.expense = expense;
        exp.date = date;

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.Type =  convertView.findViewById(R.id.Type);
            holder.Date = convertView.findViewById(R.id.Date);
            holder.ExpenseAmount = convertView.findViewById(R.id.ExpAmt);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }


        holder.Type.setText(exp.expType);
        holder.Date.setText(exp.date);
        holder.ExpenseAmount.setText(String.valueOf(exp.expense));

        return convertView;
    }
}
