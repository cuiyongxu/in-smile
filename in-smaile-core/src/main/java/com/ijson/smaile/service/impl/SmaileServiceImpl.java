package com.ijson.smaile.service.impl;

import com.ijson.smaile.common.SmaileType;
import com.ijson.smaile.manager.GetManagerType;
import com.ijson.smaile.manager.SmaileManager;
import com.ijson.smaile.model.SmaileEntity;
import com.ijson.smaile.service.SmaileService;
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
