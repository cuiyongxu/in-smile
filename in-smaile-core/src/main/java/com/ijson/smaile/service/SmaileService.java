package com.ijson.smaile.service;

import com.ijson.smaile.common.SmaileType;
import com.ijson.smaile.model.SmaileEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by cuiyongxu on 17/9/25.
 */
public interface SmaileService {

    List<SmaileEntity> getSmaile(SmaileType smaileType, Map<String,Object> params);
}
