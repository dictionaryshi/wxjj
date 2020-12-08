package com.wx.domain.passport.service;

import java.util.Date;

import com.scy.core.CollectionUtil;
import com.scy.core.ObjectUtil;
import com.wx.dao.warehouse.mapper.UserPassportDOMapper;
import com.wx.dao.warehouse.model.UserPassportDO;
import com.wx.domain.passport.entity.UserPassportEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

/**
 * UserPassportDomainServiceTest
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@SpringBootTest(classes = UserPassportDomainServiceTest.class)
public class UserPassportDomainServiceTest {

    @InjectMocks
    private UserPassportDomainService userPassportDomainService;

    @Mock
    private UserPassportDOMapper userPassportDOMapper;

    @Test
    public void getUserPassportForNull() {
        when(userPassportDOMapper.selectByExample(any())).thenReturn(null);

        UserPassportEntity userPassport = userPassportDomainService.getUserPassport("13264232894");
        assert ObjectUtil.isNull(userPassport);
    }

    @Test
    public void getUserPassport() {
        UserPassportDO userPassportDO = new UserPassportDO();
        userPassportDO.setId(123456L);
        userPassportDO.setPassport("13264232894");
        userPassportDO.setPassword("Ab123456");
        userPassportDO.setCreatedAt(new Date());
        when(userPassportDOMapper.selectByExample(any())).thenReturn(CollectionUtil.newArrayList(userPassportDO));

        UserPassportEntity userPassport = userPassportDomainService.getUserPassport("13264232894");
        assert !ObjectUtil.isNull(userPassport);
    }
}
