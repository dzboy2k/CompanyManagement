package com.vti.academy.companymanagement.model.response.user;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListUserResponse {
	private List<UserResponse> userResponseList;
	private long total;
	private Pageable pageable;
}
