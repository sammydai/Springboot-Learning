package com.dwt.springboottaskquartz.mapper;

import com.dwt.springboottaskquartz.entity.domain.JobAndTrigger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JobMapper {

	List<JobAndTrigger> list();
}
