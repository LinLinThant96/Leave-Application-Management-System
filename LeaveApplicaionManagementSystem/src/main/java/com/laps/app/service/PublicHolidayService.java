package com.laps.app.service;

import java.util.List;

import com.laps.app.model.PublicHoliday;

public interface PublicHolidayService {

	  List<PublicHoliday> findAllHolidays();
	  PublicHoliday findPublicHoliday(Integer holidayId);
	  PublicHoliday createPublicHoliday(PublicHoliday publicHoliday);
	  PublicHoliday changePublicHoliday(PublicHoliday publicHoliday);
	  void removePublicHoliday(PublicHoliday publicHoliday);
	}

