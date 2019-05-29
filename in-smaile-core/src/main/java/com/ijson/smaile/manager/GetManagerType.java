package com.ijson.smaile.manager;

import com.ijson.smaile.model.SmaileEntity;
import com.ijson.smaile.manager.handler.BudejieHandler;
import com.ijson.smaile.manager.handler.PengfuHandle;
import com.ijson.smaile.manager.handler.ShenHuifHandler;
import com.ijson.smaile.manager.handler.ZbjuranHandler;

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
