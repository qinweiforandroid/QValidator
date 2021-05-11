package com.qw.validator;

import java.util.HashMap;

/**
 * Created by qinwei on 2017/12/23.
 */

public class ValidatorFactory {

    private static HashMap<String, Class<? extends IValidator>> validators = new HashMap<>();

    static {
        validators.put(ValidatorType.V_MOBILE, MobileValidator.class);
        validators.put(ValidatorType.V_EMPTY, EmptyValidator.class);
    }

    public static void add(String type, Class<? extends IValidator> clz) {
        if (validators.containsKey(type)) {
            throw new IllegalArgumentException("type:" + type + " is already exist");
        }
        validators.put(type, clz);
    }

    public static IValidator create(String type) {
        if (!validators.containsKey(type)) {
            throw new IllegalArgumentException("not support type " + type + " please custom you validator");
        }
        try {
            return validators.get(type).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}