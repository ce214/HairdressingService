package demo.hairdressstudio.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import demo.hairdressstudio.Reservation;

public class ReservationManipulation {
	
	public static Reservation ReservationToBeDeleted(Date date, List<Reservation> list){
		
		Iterator<Reservation> it = list.iterator();
		Reservation reserve = it.next();
		while (it.hasNext()) {
			if (reserve.getInitialHour().equals(date)) {
				return reserve;
			}
		}
		return null;		
	}

}
