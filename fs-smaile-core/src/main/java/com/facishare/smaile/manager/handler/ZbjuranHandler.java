package com.facishare.smaile.manager.handler;

import com.facishare.smaile.common.DataType;
import com.facishare.smaile.manager.SmaileManager;
import com.facishare.smaile.model.SmaileEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by cuiyongxu on 17/9/26.
 */
public class ZbjuranHandler implements SmaileManager
{
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
        return null;
    }

    private List<SmaileEntity> getText(Map<String, Object> params) {
        return null;
    }
}
