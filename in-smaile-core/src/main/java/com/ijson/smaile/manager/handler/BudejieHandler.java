package com.ijson.smaile.manager.handler;

import com.ijson.smaile.common.DataType;
import com.ijson.smaile.manager.SmaileManager;
import com.ijson.smaile.model.SmaileEntity;
import com.ijson.util.MD5Util;
import com.ijson.util.RandomUtil;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cuiyongxu on 17/9/25.
 */
public class BudejieHandler implements SmaileManager {
    @Override
    public List<SmaileEntity> execute(Map<String, Object> params) {

        DataType dataType = (DataType) params.get("dataType");
        List<SmaileEntity> entities = null;
        if (DataType.text == dataType) {
            entities = getText(params);
        } else if (DataType.image == dataType) {
            entities = getImage(params);
        }


        return entities;
    }

    private List<SmaileEntity> getImage(Map<String, Object> params) {
        List<SmaileEntity> entities = Lists.newArrayList();

        String baseUrl = "http://www.budejie.com/";
        String textUrl = baseUrl + "pic/" + RandomUtil.getRamdomNumer(50);
        try {
            Document root = Jsoup.connect(textUrl).get();
            Elements links = root.select("div[class=j-r-list-c]");
            int bs = 1;
            for (Element link : links) {
                SmaileEntity smaileEntity = new SmaileEntity();
                smaileEntity.setNumber(bs);
                smaileEntity.setSource(link.baseUri());

                Elements textNode = link.select("div[class=j-r-list-c-desc]");
                String text = textNode.get(0).text();

                Elements imagesNode = link.select("div[class=j-r-list-c-img]");
                StringBuffer sb = new StringBuffer();
                sb.append(text+"</br>");
                Element link2 = imagesNode.select("img").last();
                sb.append("<img src=\""+link2.attr("data-original")+"\" style=\"width:100%\"/>");
                smaileEntity.setText(sb.toString());
                smaileEntity.setType(DataType.image);
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

    private List<SmaileEntity> getText(Map<String, Object> params) {
        List<SmaileEntity> entities = Lists.newArrayList();

        String baseUrl = "http://www.budejie.com/";
        String textUrl = baseUrl + "text/" + RandomUtil.getRamdomNumer(50);
        try {
            Document root = Jsoup.connect(textUrl).get();
            Elements links = root.select("div[class=j-r-list-c-desc]");
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
