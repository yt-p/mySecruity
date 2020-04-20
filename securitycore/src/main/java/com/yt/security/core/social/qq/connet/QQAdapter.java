package com.yt.security.core.social.qq.connet;

import com.yt.security.core.social.qq.api.QQ;
import com.yt.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/20 0020 08:17
 */
public class QQAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        try {
            QQUserInfo userInfo = qq.getUserInfo();
            connectionValues.setDisplayName(userInfo.getNickname());
            connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
            connectionValues.setProfileUrl(null);
            connectionValues.setProviderUserId(userInfo.getOpenId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
