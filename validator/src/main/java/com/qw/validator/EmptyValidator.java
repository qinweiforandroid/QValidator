package com.qw.validator;


import android.text.TextUtils;

/**
 * Created by qinwei on 2018/1/23.
 */

public class EmptyValidator implements IValidator {
    @Override
    public boolean validate(String content) {
        return !TextUtils.isEmpty(content);
    }
}
