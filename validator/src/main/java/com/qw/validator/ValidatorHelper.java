package com.qw.validator;

import java.util.ArrayList;

/**
 * 验证器工具类
 * Created by qinwei on 2017/12/23.
 */

public class ValidatorHelper {

    private ArrayList<Item> validators;

    public ValidatorHelper() {
        validators = new ArrayList<>();
    }

    public static ValidatorHelper create() {
        return new ValidatorHelper();
    }

    public class Item {
        String id;
        String content;
        String tip;
        IValidator iValidator;

        public Item(String content, String tip, IValidator validator) {
            this("", content, tip, validator);
        }

        public Item(String id, String content, String tip, IValidator iValidator) {
            this.content = content;
            this.tip = tip;
            this.iValidator = iValidator;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id='" + id + '\'' +
                    ", content='" + content + '\'' +
                    ", tip='" + tip + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public String getTip() {
            return tip;
        }
    }


    public ValidatorHelper add(String id, String content, String tip, String type) {
        return add(id, content, tip, ValidatorFactory.create(type));
    }


    public ValidatorHelper add(String content, String tip, String type) {
        validators.add(new Item(content, tip, ValidatorFactory.create(type)));
        return this;
    }

    /**
     * 添加到待验证列表
     *
     * @param id         表单唯一标示
     * @param content    验证的内容
     * @param tip        不通过的文案提示
     * @param iValidator 验证逻辑
     * @return 验证工具
     */
    public ValidatorHelper add(String id, String content, String tip, IValidator iValidator) {
        validators.add(new Item(id, content, tip, iValidator));
        return this;
    }


    public ValidatorHelper add(String content, String tip, IValidator iValidator) {
        return add("", content, tip, iValidator);
    }

    public void verify(VerifyCallback callback) {
        for (Item item : validators) {
            if (!item.iValidator.validate(item.content)) {
                callback.onFailure(item);
                return;
            }
        }
        callback.onSuccess();
    }

    public interface VerifyCallback {
        void onSuccess();

        /**
         * @param item 返回错误的表单信息实体
         */
        void onFailure(Item item);
    }
}
