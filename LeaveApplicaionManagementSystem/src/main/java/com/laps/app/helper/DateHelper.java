package com.laps.app.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laps.app.model.PublicHoliday;
import com.laps.app.service.EmployeeService;
import com.laps.app.service.PublicHolidayService;



@Service
public class DateHelper{
    private final Set<LocalDate> Publicholidays = new HashSet<>();
   
    @Autowired
    private PublicHolidayService holidayService;
    
   /* public void addHoliday(final MonthDay monthDay) {
    Publicholidays.add(monthDay);
    }*/

    public boolean isHoliday(final LocalDate localDate) {
        return isWeekend(localDate) || Publicholidays.contains((localDate));
    }

    public int numberOfWorkingDaysBetween(final LocalDate fromDate, final LocalDate endDate) {
        int c = 0;
        for (LocalDate i = fromDate; !i.isAfter(endDate); i = i.plusDays(1)) {
            if (!isHoliday(i)) {
                c++;
            }
        }
        return c;
    }

    private boolean isWeekend(final LocalDate localDate) {
        final DayOfWeek dow = localDate.getDayOfWeek();
        return dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY;
    }

    private static MonthDay toMonthDay(final LocalDate localDate) {
        return MonthDay.of(localDate.getMonth(), localDate.getDayOfMonth());
    }
    

    public int calculateLeavePeriod(final LocalDate fromDate, final LocalDate endDate) {
    	  int c = 0;
          for (LocalDate i = fromDate; !i.isAfter(endDate); i = i.plusDays(1)) {
              c++;
          }
          return c;	
    }
    public void loadHolidays() {
    	
    	List<PublicHoliday>publicholidaylist = holidayService.findAllHolidays();
    	          for(PublicHoliday publicholiday:publicholidaylist) {
    	        	  Publicholidays.add( publicholiday.getHolidayDate());
    	        	  }
    	          System.out.println("public hliday in datehepler"+Publicholidays);
    	
    	// List<PublicHoliday> findAllHolidays()
    }
 

	/*
	 * public static void main(String[] args) { final CustomHolidays ch = new
	 * CustomHolidays(); ch.addHoliday(MonthDay.of(Month.DECEMBER, 26));
	 * ch.addHoliday(MonthDay.of(Month.DECEMBER, 27));
	 * ch.addHoliday(MonthDay.of(Month.DECEMBER, 28));
	 * 
	 * System.out.println("Number of Working Days ==> "+ch.
	 * numberOfWorkingDaysBetween(LocalDate.of(2022, 12, 25), LocalDate.of(2022, 12,
	 * 31 ))); }
	 */
}
