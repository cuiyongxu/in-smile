package com.facishare.smaile.manager;

import com.facishare.smaile.model.SmaileEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by cuiyongxu on 17/9/25.
 */
public interface SmaileManager {

    List<SmaileEntity> execute(Map<String,Object> params) ;
}
