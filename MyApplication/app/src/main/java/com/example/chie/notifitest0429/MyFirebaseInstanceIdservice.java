package com.example.chie.notifitest0429;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Map;

import static com.example.chie.notifitest0429.MainActivity.flag;
import static com.example.chie.notifitest0429.MainActivity.uid;

/**
 * Created by chie on 2017/04/29.
 */

public class MyFirebaseInstanceIdservice extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIdservice";
    public static String token;
    private static long createdAt;
    private long endedAt;

    //ユーザIDの指定
    private static String userId = "AB0012-4";

    //データセット
    private Map<String,Object> dataMap;
    private JSONObject dataset = new JSONObject();

    @Override
    public void onTokenRefresh() {
        //アプリ初起動時にFCMトークンを生成
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "RefreshedToken = " + token);
        createdAt = new Date().getTime() /1000L;
        endedAt = 0;

        //TODO
        //生成されたトークンをmain_activityのtoken_viewに指定する

        //トークンの値をサーバーへ送信
        submit();
    }

    private void submit() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.d(TAG, "getInstance");

        // 2017/6/3　変更部分
        // token,createdAt,endedAtを,usersTokenの子として登録

        /*JSONObject
        DatabaseReference refUsersTokens = database.getReference("/test");
        try {
            dataset.put("token", token);
            dataset.put("createdAt", createdAt);
            dataset.put("endedAt", endedAt);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        refUsersTokens.child(usersId).setValue(dataset);
        */

        //TODO
        //ユーザIDに対してデータが見つかればendedAtを更新
        //endedAt = new Date().getTime()/1000L;

        DatabaseReference reference = database.getReference("/usersTokens/" + userId);
        reference.child("token").setValue(token);
        reference.child("createdAt").setValue(createdAt);
        reference.child("endedAt").setValue(endedAt);

        Log.d(TAG, "setValue");

        //setValue失敗時→ログにpermission denied の表示

    }

}
