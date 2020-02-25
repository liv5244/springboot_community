package com.itliv.community.service;

import com.itliv.community.dto.NotificationDTO;
import com.itliv.community.model.Notification;

import java.util.List;

public interface NotificationService {
    int deleteByPrimaryKey(Integer id);

    int insert(Notification record);

    int insertSelective(Notification record);

    Notification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notification record);

    int updateByPrimaryKey(Notification record);

    List<NotificationDTO> selectByReceiver(int receiverId, int status ,int pageNum, int pageSize);

    int selectCountByReceiverId(Integer receiverId);
}
