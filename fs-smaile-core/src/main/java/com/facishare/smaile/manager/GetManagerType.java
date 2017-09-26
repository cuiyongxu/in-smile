package com.facishare.smaile.manager;

import com.facishare.smaile.manager.handler.BudejieHandler;
import com.facishare.smaile.manager.handler.PengfuHandle;
import com.facishare.smaile.manager.handler.ShenHuifHandler;
import com.facishare.smaile.manager.handler.ZbjuranHandler;
import com.facishare.smaile.model.SmaileEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by cuiyongxu on 17/9/25.
 */
public enum GetManagerType implements SmaileManager {

    budejie {
        @Override
        public List<SmaileEntity> execute(Map<String, Object> params) {
            return new BudejieHandler().execute(params);
        }
    },
    shenhuif {
        @Override
        public List<SmaileEntity> execute(Map<String, Object> params) {
            return new ShenHuifHandler().execute(params);
        }
    },
    zbjuran{
        @Override
        public List<SmaileEntity> execute(Map<String, Object> params) {
            return new ZbjuranHandler().execute(params);
        }
    },
    pengfu{
        @Override
        public List<SmaileEntity> execute(Map<String, Object> params) {
            return new PengfuHandle().execute(params);
        }
    }
}
