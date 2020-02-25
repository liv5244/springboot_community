package com.itliv.community.mapper;

import com.itliv.community.dto.NotificationDTO;
import com.itliv.community.model.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notification record);

    int insertSelective(Notification record);

    Notification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notification record);

    int updateByPrimaryKey(Notification record);

    List<NotificationDTO> selectByReceiver(int receiverId, int status);

    int selectCountByReceiverId(int receiverId);
}