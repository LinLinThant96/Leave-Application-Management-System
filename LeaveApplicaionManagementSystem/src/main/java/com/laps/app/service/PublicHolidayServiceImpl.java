package com.laps.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.laps.app.model.PublicHoliday;
import com.laps.app.repo.PublicHolidayRepository;

@Service
public class PublicHolidayServiceImpl implements PublicHolidayService {

	@Resource
	public PublicHolidayRepository holidayRepository;
	
	@Override
	public List<PublicHoliday> findAllHolidays() {
		// TODO Auto-generated method stub
		return holidayRepository.findAll();
	}

	@Override
	public PublicHoliday findPublicHoliday(Integer holidayId) {
		// TODO Auto-generated method stub
		return holidayRepository.findById(holidayId).orElse(null);
	}

	@Override
	public PublicHoliday createPublicHoliday(PublicHoliday publicHoliday) {
		// TODO Auto-generated method stub
		return holidayRepository.saveAndFlush(publicHoliday);
	}

	@Override
	public PublicHoliday changePublicHoliday(PublicHoliday publicHoliday) {
		// TODO Auto-generated method stub
		return holidayRepository.saveAndFlush(publicHoliday);
	}

	@Override
	public void removePublicHoliday(PublicHoliday publicHoliday) {
		// TODO Auto-generated method stub
		holidayRepository.delete(publicHoliday);
	}

}

