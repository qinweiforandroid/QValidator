package com.qw.validator;


import android.text.TextUtils;

/**
 * 手机号格式验证
 * Created by qinwei on 2017/12/23.
 */

public class MobileValidator implements IValidator {
    @Override
    public boolean validate(String content) {
        if (!TextUtils.isEmpty(content) && content.startsWith("1") && content.length() == 11) {
            return true;
        }
        return false;
    }
}
