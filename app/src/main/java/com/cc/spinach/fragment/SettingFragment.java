package com.cc.spinach.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.cc.spinach.AppContext;
import com.cc.spinach.R;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/24 11:04
 * 邮箱：971859818@qq.com
 */
public class SettingFragment extends PreferenceFragment {

    Preference mCache;
    Preference mAbout;
    CheckBoxPreference mShowMap;
    CheckBoxPreference mReceiveMsg;
    AppContext mApp = AppContext.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        // 是否显示图片
        mShowMap = (CheckBoxPreference) findPreference("pref_show_map");
        mShowMap.setChecked(mApp.getIsShowMap());
        mShowMap.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                mApp.setLoadMap(mShowMap.isChecked());
                return true;
            }
        });

        // 是否接收消息
        mReceiveMsg = (CheckBoxPreference) findPreference("pref_receive_msg");
        mReceiveMsg.setChecked(mApp.getIsReceiveMsg());
        mReceiveMsg.setSummary(getActivity().getString(mReceiveMsg.isChecked()
                ? R.string.settings_use_jsonapi_summary
                : R.string.settings_use_browser_summary));
        mReceiveMsg.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                mApp.setReceiveMsg(mReceiveMsg.isChecked());
                mReceiveMsg.setSummary(getActivity().getString(mReceiveMsg.isChecked()
                        ? R.string.settings_use_jsonapi_summary
                        : R.string.settings_use_browser_summary));
                return true;
            }
        });





        // 清除缓存
        mCache = findPreference("pref_cache");
        mCache.setSummary("1.68M");
        //mCache.setSummary(FileUtils.getFileSize(FileUtils.getCacheSize(getActivity())));
        mCache.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.settings_dialog_hint)
                        .setMessage(R.string.settings_clear_cache_or_not)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //FileUtils.clearAppCache(getActivity());
                                mCache.setSummary("0KB");
                            }
                        })
                        .setNegativeButton("取消", null).show();
                return true;
            }
        });

        // 关于我们
        mAbout = findPreference("pref_about");
        mAbout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                //showAboutMe();
                return true;
            }
        });
    }
}
