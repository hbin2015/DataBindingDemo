package com.example.databindingdemo;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.databindingdemo.adapter.Linker;
import com.example.databindingdemo.bean.StudentBean;
import com.example.databindingdemo.bean.TeacherBean;
import com.example.databindingdemo.bean.UserBean;
import com.example.databindingdemo.databinding.ActivityDatabindBinding;

public class DataBindActivity extends AppCompatActivity {

    //public final ObservableArrayList<StudentBean> showDatas = new ObservableArrayList<>();
    public final ObservableArrayList<Object> showDatas = new ObservableArrayList<>();
    public final ObservableArrayList<Linker> linkers = new ObservableArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_databind);

        UserBean user = new UserBean("xiao", "ming");

        for(int n = 0; n < 20; n++) {
            if (n % 3 != 0) {
                showDatas.add(new TeacherBean("老师:" + (n + 1), "年龄:" + (n + 20)));
            }
            else {
                showDatas.add(new StudentBean("学生:" + (n + 1)));
            }
        }

        linkers.add(
                new Linker(
                        o -> o instanceof TeacherBean,
                        R.layout.item_teacher
                )
        );
        linkers.add(
                new Linker(
                        o -> o instanceof StudentBean,
                        R.layout.item_student
                )
        );


        ActivityDatabindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_databind);
        binding.setUser(user);
        binding.setActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBindItem(ViewDataBinding binding, Object data, int position) {
        binding.getRoot().setOnClickListener(v -> Toast.makeText(this,
                "你点击了: item_" + (position + 1) + " " + data.toString(), Toast.LENGTH_SHORT).show());

/*        binding.getRoot().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "你点击了: item_" + position + " " + data.toString(),
                        Toast.LENGTH_SHORT).show();
            }

        });*/
    }

}
