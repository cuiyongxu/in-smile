package com.facishare.smaile.service.impl;

import com.facishare.smaile.common.SmaileType;
import com.facishare.smaile.manager.GetManagerType;
import com.facishare.smaile.manager.SmaileManager;
import com.facishare.smaile.model.SmaileEntity;
import com.facishare.smaile.service.SmaileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by cuiyongxu on 17/9/25.
 */
@Service("smaileService")
public class SmaileServiceImpl implements SmaileService {

    @Override
    public List<SmaileEntity> getSmaile(SmaileType smaileType, Map<String,Object> params) {
        SmaileManager smaileManager= GetManagerType.valueOf(smaileType.name());
        return smaileManager.execute(params);
    }
}
