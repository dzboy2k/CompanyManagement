package com.vti.academy.companymanagement.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.vti.academy.companymanagement.common.EnumUtil;
import com.vti.academy.companymanagement.common.Validate;
import com.vti.academy.companymanagement.model.entity.Company;
import com.vti.academy.companymanagement.model.resquest.company.SearchCompanyRequest;

@Component
public class CompanyDao {
	@Autowired
	private EntityManager entityManager;
	
	public Page<Company> searchCompany(SearchCompanyRequest company, Pageable page){
		Map<String, Object> params = new HashMap<>();
		String sql = new String("SELECT * FROM tblcompany WHERE 1=1 AND isactive = true ");
		StringBuilder where =  new StringBuilder();
		StringBuilder order = new StringBuilder();
		StringBuilder limit = new StringBuilder();
		
		if(Validate.isNotEmptyString(company.getCompanyCode())) {
			where.append(" AND companycode ILIKE concat('%', :companycode, '%') ");
			params.put("companycode", company.getCompanyCode());
		}
		if(Validate.isNotEmptyString(company.getCompanyName())) {
			where.append(" AND companyname ILIKE concat('%', :companyname, '%') ");
			params.put("companyname", company.getCompanyName());
		}
		if(Validate.isNotEmptyString(company.getEmail())) {
			where.append(" AND email ILIKE concat('%', :email, '%') ");
			params.put("email", company.getEmail());
		}
		if(Validate.isNotEmptyString(company.getPhoneNumber())) {
			where.append(" AND phonenumber ILIKE concat('%', :phonenumber, '%') ");
			params.put("phonenumber", company.getPhoneNumber());
		}
		if(EnumUtil.isNotEmptyEnum(company.getStatus())) {
			where.append(" AND status = :status ");
			params.put("status", company.getStatus().getValue());
		}
		if(page != null && page.getSort().isSorted()) {
			order.append(" ORDER BY companycode ASC ");
			/*
			int i=0;
			page.getSort().forEach((sort) ->{
				order.append(" :orderby"+i);
				params.put("orderby"+i, sort.getProperty());
				if(sort.getDirection().isDescending()) {
					order.append(" DESC ");
				}else {
					order.append(" ASC ");
				}
				order.append(",");
			});
			order.delete(order.length()-1, order.length());//xóa dấu , ở cuối
			*/
			
			limit.append(" LIMIT ").append(page.getPageSize()).append(" OFFSET ").append((page.getPageNumber()-1)*page.getPageSize());
		}
		
		Query query = entityManager.createNativeQuery(sql.concat(where.toString()).concat(order.toString()).concat(limit.toString()), Company.class);
		params.forEach(query::setParameter);
		@SuppressWarnings("unchecked")
		List<Company> companyList = query.getResultList();
		
		String sqlCount = "SELECT COUNT(*) FROM tblcompany WHERE 1=1 AND isactive = true ";
		Query countQuery = entityManager.createNativeQuery(sqlCount.concat(where.toString()));
		params.forEach(countQuery::setParameter);
		long total = ((Number) countQuery.getSingleResult()).longValue();
		
		return new PageImpl<Company>(companyList, page, total);
	}
}
