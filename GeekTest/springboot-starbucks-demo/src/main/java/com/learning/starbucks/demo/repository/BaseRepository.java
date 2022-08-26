package com.learning.starbucks.demo.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @Package: com.dwt.springbootjpa.repository
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/23 21:01
 */
@NoRepositoryBean
public interface BaseRepository<T, Long> extends PagingAndSortingRepository<T, Long> {
	List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}
