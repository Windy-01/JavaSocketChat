package com._4.javasocketchat.repository;

import com._4.javasocketchat.dto.*;
import com._4.javasocketchat.entity.*;

import java.sql.PreparedStatement;

import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;

@Repository
public class UserRepository {
    private final HikariDataSource dataSource;

    public UserRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isExist(String userAccount) throws Exception {
        try(var conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQLContents.isExist());){
            ps.setString(1, userAccount);
            var rs = ps.executeQuery();
            if(rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
    }

    public String getHashPassword(String userAccount) throws Exception {
        try(var conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQLContents.getHashPassword());){
            ps.setString(1, userAccount);
            var rs = ps.executeQuery();
            if(rs.next()) {
                String hashPassword = rs.getString("password_hash");
                return hashPassword;
            }else {
                throw new Exception("User not found");
            }
        }catch(Exception e){
            throw new Exception("Failed to get hash password",e);
        }
    }

    public void add(UserEntity userEntity) throws Exception {
        try(var conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQLContents.addUser());){
            ps.setString(1, userEntity.getUserName());
            ps.setString(2, userEntity.getUserAccount());
            ps.setString(3, userEntity.getHashPassword());
            var rs = ps.executeUpdate();
            if(rs == 0) throw new Exception("User already exists"); 
        }catch(Exception e){
            throw new Exception("Failed to add new user",e);
        }
    }
}
