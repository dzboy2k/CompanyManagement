package com.vti.academy.companymanagement.model.response.company;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListCompanyResponse {
	private List<CompanyResponse> companyResponseList;
	private long total;
	private Pageable pageable;
}
