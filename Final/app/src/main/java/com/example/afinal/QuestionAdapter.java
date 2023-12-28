package com.example.afinal;

import android.widget.BaseAdapter;
import java.util.ArrayList;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;


import java.util.List;
public class QuestionAdapter extends BaseAdapter{
    private ArrayList<QuestionData> QuestionData;

    QuestionAdapter(ArrayList<QuestionData> QuestionData) {
        this.QuestionData = QuestionData;
    }


    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return QuestionData.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return QuestionData.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return Integer.parseInt(QuestionData.get(position).questionID);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.question_list_view, null);
        } else viewProduct = convertView;

        //Bind sữ liệu phần tử vào View
        QuestionData question = (QuestionData) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.questionId)).setText(String.format(String.valueOf(question.questionID)));
        ((TextView) viewProduct.findViewById(R.id.questionField)).setText(String.format(String.valueOf(question.field)));
        ((TextView) viewProduct.findViewById(R.id.questionHead)).setText(String.format(String.valueOf(question.question)));
        return viewProduct;
    }
}
