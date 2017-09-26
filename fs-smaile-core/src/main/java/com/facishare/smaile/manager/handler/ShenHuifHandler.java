package com.facishare.smaile.manager.handler;

import com.facishare.smaile.common.DataType;
import com.facishare.smaile.manager.SmaileManager;
import com.facishare.smaile.model.SmaileEntity;
import com.facishare.util.MD5Util;
import com.facishare.util.RandomUtil;
import com.google.common.collect.Lists;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cuiyongxu on 17/9/26.
 */
public class ShenHuifHandler implements SmaileManager {
    @Override
    public List<SmaileEntity> execute(Map<String, Object> params) {
        List<SmaileEntity> entities = Lists.newArrayList();

        String baseUrl = "http://www.shenhuif.com/";

        int number = RandomUtil.getRamdomNumer(25);

        String textUrl;
        if (number == 0) {
            textUrl = baseUrl + "text.html";
        } else {
            textUrl = baseUrl + "text_" + number + ".html";
        }

        try {
            Connection con = HttpConnection.connect(textUrl);
            con.header("accept", "application/json, text/javascript, */*; q=0.01");
            con.header("accept-encoding", "gzip, deflate, br");
            con.header("accept-language", "zh-CN,zh;q=0.8,ja;q=0.6,en-GB;q=0.4,en;q=0.2,zh-TW;q=0.2");
            con.header("user-agent", ":Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
            con.header("x-requested-with", "XMLHttpRequest");
            Document root = con.get();

            Elements links = root.select("dd[class=content]");
            int bs = 1;
            for (Element link : links) {
                SmaileEntity smaileEntity = new SmaileEntity();
                smaileEntity.setNumber(bs);
                smaileEntity.setSource(link.baseUri());
                smaileEntity.setText(link.text());
                smaileEntity.setType(DataType.text);
                smaileEntity.setMd5(MD5Util.getMD5(link.text()));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                smaileEntity.setCreateTime(sdf.format(new Date()));
                smaileEntity.setCreateor("纷享销客");
                entities.add(smaileEntity);
                bs++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
