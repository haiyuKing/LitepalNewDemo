package com.why.project.litepalnewdemo;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.why.project.litepalnewdemo.bean.LoginUserBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private Button btn_save;
	private Button btn_saveOrUpdate;
	private Button btn_saveAll;

	private Button btn_update;
	private Button btn_updateAll;

	private Button btn_find;

	private Button btn_del;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initEvents();
	}

	private void initViews() {
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_saveOrUpdate = (Button) findViewById(R.id.btn_saveOrUpdate);
		btn_saveAll = (Button) findViewById(R.id.btn_saveAll);

		btn_update = (Button) findViewById(R.id.btn_update);
		btn_updateAll = (Button) findViewById(R.id.btn_updateAll);

		btn_find = (Button) findViewById(R.id.btn_find);

		btn_del = (Button) findViewById(R.id.btn_del);
	}

	private void initEvents() {
		btn_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LoginUserBean loginUserBean = new LoginUserBean();
				loginUserBean.setUserId("00001");
				loginUserBean.setUserName("用户名1");
				loginUserBean.setPassWord("密码1");
				loginUserBean.setTel("18600001");

				loginUserBean.save();
			}
		});

		btn_saveOrUpdate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LoginUserBean loginUserBean = new LoginUserBean();
				loginUserBean.setUserId("00001");
				loginUserBean.setUserName("用户名1_");
				loginUserBean.setPassWord("密码1_");
				loginUserBean.setTel("18600001");

				loginUserBean.saveOrUpdate("userid=?",loginUserBean.getUserId());
			}
		});

		btn_saveAll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LoginUserBean loginUserBean2 = new LoginUserBean();
				loginUserBean2.setUserId("00002");
				loginUserBean2.setUserName("用户名2");
				loginUserBean2.setPassWord("密码2");
				loginUserBean2.setTel("18600002");

				LoginUserBean loginUserBean3 = new LoginUserBean();
				loginUserBean3.setUserId("00003");
				loginUserBean3.setUserName("用户名3");
				loginUserBean3.setPassWord("密码3");
				loginUserBean3.setTel("18600003");

				List<LoginUserBean> loginList = new ArrayList<LoginUserBean>();
				loginList.add(loginUserBean2);
				loginList.add(loginUserBean3);

				LitePal.saveAll(loginList);
			}
		});

		btn_update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ContentValues values = new ContentValues();
				values.put("username","用户名2_");
				int updateNum = LitePal.update(LoginUserBean.class,values,2);//修改id值等于2的那一行数据
				Log.w(TAG,"{btn_update}updateNum="+updateNum);
			}
		});

		btn_updateAll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ContentValues values = new ContentValues();
				values.put("username","用户名3_");
				int updateAllNum = LitePal.updateAll(LoginUserBean.class,values,"userid=?","00003");//修改userid=00003的那一行数据
				Log.w(TAG,"{btn_update}updateAllNum="+updateAllNum);
			}
		});

		btn_find.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LoginUserBean firstModel = LitePal.findFirst(LoginUserBean.class);
				Log.w(TAG,"userId = " + firstModel.getUserId());

				LoginUserBean lastModel = LitePal.findLast(LoginUserBean.class);
				Log.w(TAG,"userId = " + lastModel.getUserId());

				List<LoginUserBean> findAllList = LitePal.findAll(LoginUserBean.class);
				if(findAllList.size() > 0){
					for(LoginUserBean model : findAllList){
						Log.w(TAG,"model.getUserId()" + model.getUserId());
					}
				}

				List<LoginUserBean> findWhereList = LitePal.where("userid=?","00003").find(LoginUserBean.class);
				if(findWhereList.size() > 0){
					for(LoginUserBean model : findWhereList){
						Log.w(TAG,"model.getUserId()" + model.getUserId());
					}
				}

				List<LoginUserBean> findSelectList = LitePal.select("userid","username").where("userid=?","00003").find(LoginUserBean.class);
				if(findSelectList.size() > 0){
					for(LoginUserBean model : findSelectList){
						Log.w(TAG,"model.getUserId()" + model.getUserId());
					}
				}

				List<LoginUserBean> findOrderList = LitePal.select("userid","username")
						.where("userid=?","00003")
						.order("userid desc")
						.find(LoginUserBean.class);
				if(findOrderList.size() > 0){
					for(LoginUserBean model : findOrderList){
						Log.w(TAG,"model.getUserId()" + model.getUserId());
					}
				}

				List<LoginUserBean> findlimitList = LitePal.select("userid","username")
						.where("userid=?","00003")
						.limit(10)
						.find(LoginUserBean.class);
				if(findlimitList.size() > 0){
					for(LoginUserBean model : findlimitList){
						Log.w(TAG,"model.getUserId()" + model.getUserId());
					}
				}

				List<LoginUserBean> findoffsetList = LitePal.select("userid","username")
						.where("userid=?","00003")
						.limit(10)
						.offset(10)
						.find(LoginUserBean.class);
				if(findoffsetList.size() > 0){
					for(LoginUserBean model : findoffsetList){
						Log.w(TAG,"model.getUserId()" + model.getUserId());
					}
				}

				LoginUserBean findWhereFisrt = LitePal.where("userid=?","00003").findFirst(LoginUserBean.class);
				Log.w(TAG,"findWhereFisrt.getUserId()" + findWhereFisrt.getUserId());

				LoginUserBean findWhereLast = LitePal.where("userid=?","00003").findLast(LoginUserBean.class);
				Log.w(TAG,"findWhereFisrt.getUserId()" + findWhereLast.getUserId());

				boolean isExist = LitePal.isExist(LoginUserBean.class,"userid=?","00003");
				Log.w(TAG,"isExist=" + isExist);
			}
		});

		btn_del.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int DelNum = LitePal.delete(LoginUserBean.class,1);//删除ID值等于1的那一行数据
				Log.w(TAG,"DelNum=" + DelNum);

				int delAllNum = LitePal.deleteAll(LoginUserBean.class,"userid=?","00002");
				Log.w(TAG,"delAllNum=" + delAllNum);

//				int delAllNum = LitePal.deleteAll(LoginUserModel.class);
//				Log.w(TAG,"delAllNum=" + delAllNum);
			}
		});
	}
}
