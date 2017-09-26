package com.facishare;

import com.facishare.smaile.common.DataType;
import com.facishare.smaile.common.SmaileType;
import com.facishare.smaile.model.SmaileEntity;
import com.facishare.smaile.service.SmaileService;
import com.facishare.util.RandomUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by cuiyongxu on 17/9/25.
 */
@Controller()
@RequestMapping("/fs/smaile")
public class SmaileController {

    @Autowired
    private SmaileService smaileService;

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSmaile() {
        Map<String, Object> map = Maps.newHashMap();
        Map<String, Object> result = Maps.newHashMap();
        List<SmaileType> type = Lists.newArrayList(SmaileType.budejie);//SmaileType.shenhuif,,SmaileType.pengfu
        List<DataType> dataType = Lists.newArrayList(DataType.text, DataType.image);
        DataType type2 = getRandomDataType(dataType);
        map.put("dataType",type2);
        List<SmaileEntity> smaileEntities = getResult(type, map);
        result.put("list", smaileEntities);
        result.put("dataType", type2.name());
        return result;
    }


    private List<SmaileEntity> getResult(List<SmaileType> type, Map<String, Object> map) {
        SmaileType smaileType = getRandomElement(type);
        List<SmaileEntity> smaileEntities;
        try {
            smaileEntities = smaileService.getSmaile(smaileType, map);
        } catch (Exception e) {
            type.remove(smaileType);
            smaileEntities = getResult(type, map);
        }
        return smaileEntities;
    }


    private static SmaileType getRandomElement(List<SmaileType> items) {
        for (int i = 0; i < items.size(); i++) {
            return items.get(RandomUtil.getRamdomNumer(items.size()));
        }
        return null;
    }

    private static DataType getRandomDataType(List<DataType> items) {
        for (int i = 0; i < items.size(); i++) {
            return items.get(RandomUtil.getRamdomNumer(items.size()));
        }
        return null;
    }


}
