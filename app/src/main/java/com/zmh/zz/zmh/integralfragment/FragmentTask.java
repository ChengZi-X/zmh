package com.zmh.zz.zmh.integralfragment;

import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseFragment;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.AddAddress;
import com.zmh.zz.zmh.activity.AddBankCard;
import com.zmh.zz.zmh.activity.CheckCalendar;
import com.zmh.zz.zmh.activity.RealNameAuthenticationFalse;

/**
 * 任务
 */
public class FragmentTask extends BaseFragment implements View.OnClickListener {
    private View view;
    private LinearLayout mSign, mReal_name_authentication, mAdd_bank_card, mAdd_address;
    private TextView mTv_sign, mTv_Real_name_authentication, mTv_add_bank_card, mTv_add_address;

    @Override
    public View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_task, null);
        LinearLayoutFindViewById();
        TextViewFindViewById();
        return view;
    }

    private void LinearLayoutFindViewById() {
        mSign = (LinearLayout) view.findViewById(R.id.sign);
        mSign.setOnClickListener(this);
        mReal_name_authentication = (LinearLayout) view.findViewById(R.id.real_name_authentication);
        mReal_name_authentication.setOnClickListener(this);
        mAdd_bank_card = (LinearLayout) view.findViewById(R.id.add_bank_card);
        mAdd_bank_card.setOnClickListener(this);
        mAdd_address = (LinearLayout) view.findViewById(R.id.add_address);
        mAdd_address.setOnClickListener(this);
    }

    private void TextViewFindViewById() {
        mTv_sign = (TextView) view.findViewById(R.id.tv_sign);
        SpannableString spanText1 = new SpannableString("签到得积分,连续签到积分更高");
        spanText1.setSpan(new ForegroundColorSpan(Color.parseColor("#FB6A6D")), 12, 14, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTv_sign.setText(spanText1);
        mTv_Real_name_authentication = (TextView) view.findViewById(R.id.tv_real_name_authentication);
        SpannableString spanText2 = new SpannableString("实名认证可得200积分");
        spanText2.setSpan(new ForegroundColorSpan(Color.parseColor("#FB6A6D")), 6, 9, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTv_Real_name_authentication.setText(spanText2);
        mTv_add_bank_card = (TextView) view.findViewById(R.id.tv_add_bank_card);
        SpannableString spanText3 = new SpannableString("添加银行卡可得200积分,上限250积分");
        spanText3.setSpan(new ForegroundColorSpan(Color.parseColor("#FB6A6D")), 7, 10, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTv_add_bank_card.setText(spanText3);
        mTv_add_address = (TextView) view.findViewById(R.id.tv_add_address);
        SpannableString spanText4 = new SpannableString("添加地址可得50积分");
        spanText4.setSpan(new ForegroundColorSpan(Color.parseColor("#FB6A6D")), 6, 8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTv_add_address.setText(spanText4);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign:
                startActivity(new Intent(getActivity(), CheckCalendar.class));
                break;
            case R.id.real_name_authentication:
                startActivity(new Intent(getActivity(), RealNameAuthenticationFalse.class));
                break;
            case R.id.add_bank_card:
                startActivity(new Intent(getActivity(), AddBankCard.class));
                break;
            case R.id.add_address:
                startActivity(new Intent(getActivity(), AddAddress.class));
                break;
        }
    }
}
