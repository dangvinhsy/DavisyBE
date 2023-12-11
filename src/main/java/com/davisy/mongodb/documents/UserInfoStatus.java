package com.davisy.mongodb.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoStatus {
	private String user_id;
	private String infor_id;
	private boolean status = true;
}
