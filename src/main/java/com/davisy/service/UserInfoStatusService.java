package com.davisy.service;

import java.util.List;

import com.davisy.dto.UserInfoStatusDTO;
import com.davisy.entity.User;
import com.davisy.mongodb.documents.ModeratorPostReported;
import com.davisy.mongodb.documents.UserInfoStatus;

public interface UserInfoStatusService {
	public UserInfoStatus findByColumn(String column, String data);

	public UserInfoStatus findByTwoColumn(String column1, String id1, String column2, String id2);
	
	public List<UserInfoStatus> findAllByColumn(String column, String data);
	
	public List<UserInfoStatus> findAll();
	
	public UserInfoStatus insert(UserInfoStatus userInfoStatus);
	
	public List<UserInfoStatus> inserts(List<UserInfoStatus> userInfoStatus);

	public UserInfoStatus update(String column, String data, UserInfoStatus userInfoStatusUpdate);
	
	public long delete(String column, String data);
	
	public void senData(User user);
	
	public UserInfoStatusDTO checkUserInfoStatus(String userID);
	
	
	
}
