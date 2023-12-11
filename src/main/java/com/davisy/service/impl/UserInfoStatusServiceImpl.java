package com.davisy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.davisy.dto.UserInfoStatusDTO;
import com.davisy.entity.User;
import com.davisy.mongodb.MongoDBUtils;
import com.davisy.mongodb.documents.ModeratorPostReported;
import com.davisy.mongodb.documents.UserInfoStatus;
import com.davisy.service.CacheService;
import com.davisy.service.UserInfoStatusService;

@Service
public class UserInfoStatusServiceImpl implements UserInfoStatusService {

	@Autowired
	private MongoDBUtils dbUtils;

	@Value("${davis.mongodb.collectionUserInfoStatus}")
	private String collectionUserInfoStatus;

	@Autowired
	private CacheService cacheService;

	private String birthday = "birthday";
	private String gender = "gender";
	private String location = "location";

	@Override
	public void senData(User user) {
		try {
			for (int i = 0; i < 3; i++) {
				if (i == 0) {
					UserInfoStatus userInfoStatus = new UserInfoStatus();
					userInfoStatus.setUser_id(user.getUser_id().toString());
					userInfoStatus.setInfor_id(birthday);

					insert(userInfoStatus);

				} else if (i == 1) {
					UserInfoStatus userInfoStatus = new UserInfoStatus();
					userInfoStatus.setUser_id(user.getUser_id().toString());
					userInfoStatus.setInfor_id(gender);

					insert(userInfoStatus);

				} else {
					UserInfoStatus userInfoStatus = new UserInfoStatus();
					userInfoStatus.setUser_id(user.getUser_id().toString());
					userInfoStatus.setInfor_id(location);

					insert(userInfoStatus);
				}
			}
		} catch (Exception e) {
			System.out.println("Lỗi sendata trong UserInfoStatusServiceImpl" + e);
		}
	}

	@Override
	public UserInfoStatusDTO checkUserInfoStatus(String userID) {

		UserInfoStatusDTO uStatus = new UserInfoStatusDTO();
		try {

			List<UserInfoStatus> listUserStatus = findAllByColumn("user_id", userID);
			System.out.println(listUserStatus);
			for (UserInfoStatus userStatus : listUserStatus) {
				if (userStatus.getInfor_id().equals(birthday)) {
					uStatus.setBirthdayStatus(userStatus.isStatus());
				} else if (userStatus.getInfor_id().equals(gender)) {
					uStatus.setGenderStatus(userStatus.isStatus());
				} else {
					uStatus.setLocationStatus(userStatus.isStatus());
				}
//			UserInfoStatus uBirhdayStatus = findByTwoColumn("user_id", userID, "info_id", "birthday");
//			uStatus.setBirthdayStatus(uBirhdayStatus.isStatus());
//
//			//
//			UserInfoStatus uGenderStatus = findByTwoColumn("user_id", userID, "info_id", gender);
//			uStatus.setGenderStatus(uGenderStatus.isStatus());
//
//			//
//			UserInfoStatus uLocationStatus = findByTwoColumn("user_id", userID, "info_id", location);
//			uStatus.setLocationStatus(uLocationStatus.isStatus());
			}

			return uStatus;

		} catch (Exception e) {
			System.out.println("Lỗi checkUserInfoStatus trong UserInfoStatusServiceImpl" + e);
			return uStatus;
		}
	}

	@Override
	public UserInfoStatus findByTwoColumn(String column1, String id1, String column2, String id2) {
		return dbUtils.findByTwoColumn(UserInfoStatus.class, collectionUserInfoStatus, column1, id1, column2, id2);
	}

	@Override
	public UserInfoStatus findByColumn(String column, String data) {
		return dbUtils.findByColumn(UserInfoStatus.class, collectionUserInfoStatus, column, data);
	}

	@Override
	public List<UserInfoStatus> findAllByColumn(String column, String data) {
		return dbUtils.findAllByColumn(UserInfoStatus.class, collectionUserInfoStatus, column, data);
	}

	@Override
	public List<UserInfoStatus> findAll() {
		return dbUtils.findAll(UserInfoStatus.class, collectionUserInfoStatus);
	}

	@Override
	public UserInfoStatus insert(UserInfoStatus userInfoStatus) {
		return dbUtils.insert(userInfoStatus, UserInfoStatus.class, collectionUserInfoStatus);
	}

	@Override
	public List<UserInfoStatus> inserts(List<UserInfoStatus> userInfoStatus) {
		return dbUtils.inserts(userInfoStatus, collectionUserInfoStatus);
	}

	@Override
	public UserInfoStatus update(String column, String data, UserInfoStatus userInfoStatusUpdate) {
		return dbUtils.updateFirstByColumn(UserInfoStatus.class, collectionUserInfoStatus, column, data,
				userInfoStatusUpdate);
	}

	@Override
	public long delete(String column, String data) {
		return dbUtils.deletesByColumn(UserInfoStatus.class, collectionUserInfoStatus, column, data);
	}

}
