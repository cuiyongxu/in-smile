package com.facishare.smaile.model;

import com.facishare.smaile.common.DataType;
import lombok.Data;

/**
 * Created by cuiyongxu on 17/9/26.
 */
@Data
public class SmaileEntity {

    private String title;
    //内容,图片或者文字
    private String text;
    // 内容类型,图片,文字,图文
    private DataType type;
    //来源
    private String source;
    // md5值
    private String md5;
    // 序号
    private int number;

    private String createTime;

    private String createor;
}
