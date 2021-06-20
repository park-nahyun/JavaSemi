import java.util.Hashtable;
import java.util.Vector;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.io.Serializable;


class ReservData implements Serializable
{
	private boolean exist;		// ReservData에 특정 유저의 존재 유무
	private String appDir;
	private File reservList;
	private Hashtable ht;

	static private String[] saveStart;
	static private Vector<String[]> hsReserv;
	static private String[] saveEnd;
	static private Vector<String[]> heReserv;
	static private Vector<Reserv> vReserv;

	ReservData()
	{
		saveStart = new String[5];
		hsReserv = new Vector<String[]>();
		saveEnd = new String[6];
		heReserv = new Vector<String[]>();
		vReserv = new Vector<Reserv>();
		exist = false;
	}
	
	// 새 예약 정보 생성하는 곳
	public void putReservStartData(Reserv reserv)
	{
		saveStart[0] = reserv.getStartYear();
		saveStart[1] = reserv.getStartMonth();
		saveStart[2] = reserv.getStartDate();
		saveStart[3] = reserv.getStartHour();
		saveStart[4] = reserv.getStartMinute();
		hsReserv.add(saveStart);
		vReserv.add(reserv);
	}

	public void putReservEndData(Reserv reserv)
	{
		saveEnd[0] = reserv.getEndYear();
		saveEnd[1] = reserv.getEndMonth();
		saveEnd[2] = reserv.getEndDate();
		saveEnd[3] = reserv.getEndHour();
		saveEnd[4] = reserv.getEndMinute();
		saveEnd[5] = reserv.getEndCheck();
		heReserv.add(saveEnd);
		vReserv.add(reserv);
	}

	// 외부에서 User벡터에 접근할 수 있는 메소드
	public static Vector<Reserv> getReserv()
	{
		return vReserv; 		
	}

	public static void printReserv()
	{
		Iterator it = vReserv.iterator();
		Reserv r1;
		while(it.hasNext())
		{
			r1 = (Reserv)it.next();
			System.out.println(r1);
		}
	}
}