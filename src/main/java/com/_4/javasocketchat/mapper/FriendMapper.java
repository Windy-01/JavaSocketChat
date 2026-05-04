package com._4.javasocketchat.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface FriendMapper {
    // 新建好友关系
    @Insert("INSERT INTO friendships(id_a, id_b) VALUES(#{UID_a}, #{UID_b})")
    int addFriendship(@Param("UID_a") int UID_a, @Param("UID_b") int UID_b);

    // 删除好友关系
    @Delete("DELETE FROM friendships WHERE (id_a = #{UID_a} AND id_b = #{UID_b}) OR (id_a = #{UID_b} AND id_b = #{UID_a})")
    int deleteFriendship(@Param("UID_a") int UID_a, @Param("UID_b") int UID_b);
}
