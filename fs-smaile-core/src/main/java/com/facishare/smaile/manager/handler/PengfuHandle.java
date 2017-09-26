package com.facishare.smaile.manager.handler;

import com.facishare.smaile.common.DataType;
import com.facishare.smaile.manager.SmaileManager;
import com.facishare.smaile.model.SmaileEntity;
import com.facishare.util.MD5Util;
import com.facishare.util.RandomUtil;
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
 * Created by cuiyongxu on 17/9/26.
 */
public class PengfuHandle implements SmaileManager {
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
        String baseUrl = "https://www.pengfu.com/qutu_";
        String textUrl = baseUrl + RandomUtil.getRamdomNumer(25) + ".html";
        try {
            Document root = Jsoup.connect(textUrl).get();
            Elements links = root.select("dd");
            int bs = 1;
            for (Element link : links) {
                SmaileEntity smaileEntity = new SmaileEntity();
                smaileEntity.setNumber(bs);
                smaileEntity.setSource(link.baseUri());

                Elements textNode = link.select("h1[class=dp-b]");
                String text = "";
                if (textNode.size() > 0) {
                    text = textNode.get(0).text();
                }

                Elements imagesNode = link.select("div[class=content-img clearfix pt10 relative]");
                StringBuffer sb = new StringBuffer();
                sb.append(text + "</br>");
                Element link2 = imagesNode.select("img").last();
                if (link2 == null) {
                    continue;
                }
                String url = link2.attr("jpgsrc");
                url = url.replace("https","http");
                sb.append("<img src=\"" + url + "\" style=\"width:100%\"/>");
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

        String baseUrl = "https://www.pengfu.com/xiaohua_";
        String textUrl = baseUrl + RandomUtil.getRamdomNumer(50) + ".html";
        try {
            Document root = Jsoup.connect(textUrl).get();
            Elements links = root.select("dd");
            int bs = 1;
            for (Element link : links) {
                SmaileEntity smaileEntity = new SmaileEntity();
                smaileEntity.setNumber(bs);
                smaileEntity.setSource(link.baseUri());
                String text = "";
                Elements textNode = link.select("h1[class=dp-b]");
                if (textNode.size() > 0) {
                    text = textNode.get(0).text();
                }

                Elements imagesNode = link.select("div[class=content-img clearfix pt10 relative]");
                StringBuffer sb = new StringBuffer();
                sb.append(text + "</br>");
                sb.append(imagesNode.text());
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
}
