package com.itliv.community.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itliv.community.dto.NotificationDTO;
import com.itliv.community.mapper.NotificationMapper;
import com.itliv.community.model.Notification;
import com.itliv.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationMapper notificationMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Notification record) {
        return notificationMapper.insert(record);
    }

    @Override
    public int insertSelective(Notification record) {
        return 0;
    }

    @Override
    public Notification selectByPrimaryKey(Integer id) {
        return notificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Notification record) {
        return notificationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Notification record) {
        return 0;
    }

    @Override
    public List<NotificationDTO> selectByReceiver(int receiverId, int status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return notificationMapper.selectByReceiver(receiverId,status);
    }

    @Override
    public int selectCountByReceiverId(Integer receiverId) {
        return notificationMapper.selectCountByReceiverId(receiverId);
    }
}
