package com.framework.upload;

/**
 * 文件状态  1：上传未保存  2：已保存
 *
 * @author wang
 * @create 2017-03-22 16:26
 **/
public enum FileStatusEnum {
    NOSAVE(1), SAVE(2);

    private int value;

    FileStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
