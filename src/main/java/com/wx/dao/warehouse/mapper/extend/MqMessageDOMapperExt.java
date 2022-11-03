package com.wx.dao.warehouse.mapper.extend;

import com.google.common.collect.Lists;
import com.scy.core.db.SqlUtil;
import com.wx.dao.warehouse.model.MqMessageDO;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @author : shichunyang
 * Date    : 2022/11/3
 * Time    : 5:14 下午
 * ---------------------------------------
 * Desc    : MqMessageDOMapperExt
 */
public interface MqMessageDOMapperExt {

    @UpdateProvider(type = MqMessageDOMapperExtSqlProvider.class, method = "batchInsert")
    int batchInsert(List<MqMessageDO> rows);

    class MqMessageDOMapperExtSqlProvider {
        public String batchInsert(List<MqMessageDO> rows) {
            return SqlUtil.batchInsert(MqMessageDO.class, "mq_message", Lists.newArrayList("id", "created_at", "updated_at"));
        }
    }
}
