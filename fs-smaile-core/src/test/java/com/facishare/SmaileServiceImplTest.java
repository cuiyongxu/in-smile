package com.facishare;

import com.facishare.smaile.common.DataType;
import com.facishare.smaile.common.SmaileType;
import com.facishare.smaile.model.SmaileEntity;
import com.facishare.smaile.service.SmaileService;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by cuiyongxu on 17/9/25.
 */
@ContextConfiguration(locations = "classpath:spring-rest-proxy.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SmaileServiceImplTest {

    @Autowired
    private SmaileService smaileService;


    @Test
    public void getSmaile() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("dataType", DataType.text);
        List<SmaileEntity> value = smaileService.getSmaile(SmaileType.pengfu, params);
        System.out.println(111);
    }

}
