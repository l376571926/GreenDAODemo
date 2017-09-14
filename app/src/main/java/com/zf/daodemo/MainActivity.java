package com.zf.daodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNameEt;
    private EditText mAgeEt;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEt = (EditText) findViewById(R.id.nameEt);
        mAgeEt = (EditText) findViewById(R.id.ageEt);

        findViewById(R.id.insertBtn).setOnClickListener(this);
        findViewById(R.id.queryBtn).setOnClickListener(this);
        findViewById(R.id.deleteBtn).setOnClickListener(this);
        findViewById(R.id.updateBtn).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name = mNameEt.getText().toString();
        String age = mAgeEt.getText().toString();
        switch (view.getId()) {
            case R.id.insertBtn:

                User user = new User();
                if (TextUtils.isEmpty(name)) {
                    user.setName("未命名");
                } else {
                    user.setName(name);
                }

                if (TextUtils.isEmpty(age)) {
                    user.setAge(20);
                } else {
                    user.setAge(Integer.parseInt(age));
                }
                //android.database.sqlite.SQLiteException: table USER has no column named AGE (code 1): , while compiling: INSERT INTO "USER" ("_id","NAME","AGE") VALUES (?,?,?)
                App.mUserDao.save(user);
                break;
            case R.id.queryBtn:
                List<User> userList = App.mUserDao.queryBuilder().build().list();
                Log.e(TAG, "onClick: " + userList.toString());
                break;
            case R.id.deleteBtn:
                if (TextUtils.isEmpty(name)) {
                    return;
                }
                List<User> list = App.mUserDao.queryBuilder().build().list();
                User user11 = null;
                for (User user1 : list) {
                    if (TextUtils.equals(name, user1.getName())) {
                        user11 = user1;
                        break;
                    }
                }
                if (user11 != null) {
                    App.mUserDao.delete(user11);
                }
                break;
            case R.id.updateBtn:
                if (TextUtils.isEmpty(name)) {
                    return;
                }
                if (TextUtils.isEmpty(age)) {
                    return;
                }
                List<User> userList1 = App.mUserDao.queryBuilder().build().list();
                User user1 = null;
                for (User user111 : userList1) {
                    if (TextUtils.equals(name, user111.getName())) {
                        user1 = user111;
                        break;
                    }
                }
                if (user1 != null) {
                    user1.setAge(Integer.parseInt(age));
                    App.mUserDao.update(user1);
                }
                break;
            default:
                break;
        }
    }
}
