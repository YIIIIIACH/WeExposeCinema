package test;

import java.util.Date;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Bcrypt.*;
public class TestMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		HashMap<Integer, String> hm = new HashMap<Integer, String>();
//		hm.put( 1, "aa");
//		hm.put(2, "bb");
//		for( HashMap.Entry<Integer, String> t : hm.entrySet()) {
//			System.out.println( t.getKey()+ " "+ t.getValue());
//		}
//		hm.put(1, "cc");
//		for( HashMap.Entry<Integer, String> t : hm.entrySet()) {
//			System.out.println( t.getKey()+ " "+ t.getValue());
//		}
//		String str = "2023-10-09 14:30:20.000";
//        DateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss.sss");
//        try {
//			Date date = format.parse(str);
//			System.out.printf( "%tY-%tm-%td %tH:%tM:%tS",date,date,date,date,date,date,date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Date d = new Date();
//		System.out.println(d);
		int i=0;
		while( i<=10) {
			String hashed = BCrypt.hashpw("123", BCrypt.gensalt());
//			System.out.print(BCrypt.checkpw("123", hashed));
			System.out.println(hashed);
			i++;
		}
	}	

}
