package com.example.afinal;

import android.widget.BaseAdapter;
import java.util.ArrayList;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.ViewGroup;

public class QuestionListViewAdapter extends BaseAdapter {
    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final ArrayList<FieldData> listQuestion;

    QuestionListViewAdapter(ArrayList<FieldData> listQuestion) {
        this.listQuestion = listQuestion;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return listQuestion.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return listQuestion.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return listQuestion.get(position).fieldID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.activity_list_field, null);
        } else viewProduct = convertView;

        //Bind sữ liệu phần tử vào View
        FieldData product = (FieldData) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.nameField)).setText(String.format(String.valueOf(product.name)));
        ((TextView) viewProduct.findViewById(R.id.detailField)).setText(String.format(String.valueOf(product.detail)));
        ((ImageView) viewProduct.findViewById(R.id.imageField)).setImageResource(product.image);

        return viewProduct;
    }
}