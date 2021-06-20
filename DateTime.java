import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Date;


class InputDate
{
	ReservData rd;
	String[] reserveInfo = new String[4];
	static boolean successEnd = false;
	User loginUser;

	String[] startDateArr;
	String[] endDateArr;
	
	{
		rd = new ReservData();
		loginUser = new User();
	}

	InputDate(User loginUser)
	{
		this.loginUser = loginUser; 
	}
	
	InputDate()	
	{	
	}

	Scanner sc = new Scanner(System.in);
	String startDate, startTime;
	String endDate, endTime;
	int userDaysStart,userDaysEnd;
	int userTimeStart,userTimeEnd;
	static int rentTime;

	Date time = new Date();
	
	// 현재 시각 가져오기
	SimpleDateFormat nowTime1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
	SimpleDateFormat nowTime2 = new SimpleDateFormat ("yyyy-MM-dd");
	SimpleDateFormat nowTime3 = new SimpleDateFormat ("HH:mm");
	String nowtime1 = nowTime1.format(time);
	String nowtime2 = nowTime2.format(time);
	String nowtime3 = nowTime3.format(time);
	String[] nowtime = nowtime3.split("");
	String nowtime10 = nowtime[0]+nowtime[1]+nowtime[2]+nowtime[3];

	// 현재 연,월,일 가져오기
	Calendar cal = Calendar.getInstance();
	int nowY = cal.get(Calendar.YEAR);
	int nowM = cal.get(Calendar.MONTH)+1;
	int nowD = cal.get(Calendar.DATE);
	int nowH = cal.get(Calendar.HOUR_OF_DAY);
	int nowMn = cal.get(Calendar.MINUTE);
	int nowDays = compareDate(nowY,nowM,nowD);
	int nowTime = compareTime(nowH,nowMn);
	

	// 달력 가로로 두 개 출력
	void twoCalendar()
	{
		int w, w2, last, last2;
		int d = 1, n = 1;
		int n7;

		SimpleDateFormat year = new SimpleDateFormat ("yyyy");
		SimpleDateFormat month = new SimpleDateFormat ("MM");
		
		// 현재 달과 그 다음 달까지의 달력만을 출력하기 위함
		int y = Integer.parseInt(year.format(time));
		int m = Integer.parseInt(month.format(time));

		// 왼쪽 달력 설정
		Calendar cal = new GregorianCalendar();
		cal.set(y,m-1,1);	//--입력받은 연도와 월의 1일로 설정
		w = cal.get(Calendar.DAY_OF_WEEK);	//--1일이 해당하는 요일(일요일: 1)
		last = cal.getActualMaximum(Calendar.DATE);	//--날짜 최대값(마지막 날)

		// 오른쪽 달력 설정
		Calendar cal2 = new GregorianCalendar();
		cal2.set(y,m,1);
		w2 = cal2.get(Calendar.DAY_OF_WEEK);
		last2 = cal2.getActualMaximum(Calendar.DATE);

		// 출력
		System.out.print("\n-----------------------------------------------------------------------\n\n");
		System.out.println("\n*  날짜 선택으로 이동합니다.  *");

		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}

		System.out.print("\n\n-----------------------------------------------------------------------\n\n");
		System.out.print("\n【 날짜 선택 】\n");

		System.out.printf("\n\n        [%d년 %d월]\t\t\t   [%d년 %d월]",y,m,y,m+1);
		System.out.print("\n\n 일  월  화  수  목  금  토         일  월  화  수  목  금  토\n============================       ============================\n");
		

		// 1일 이전의 공백 출력
		for (int i=1; i<w; i++)
			System.out.print("    ");

		for (int i=1; i<=42; i++)
		{	
			// 왼쪽 달력 채우기
			// 마지막 일까지 숫자 채웠으면 남은 칸은 공백으로 채우기
			if (d <= last)
			{
				System.out.printf("%4d", d);
				d++;
			}
			else
				System.out.print("    ");

			// 오른쪽 달력은 한 주씩 나눠서 입력
			// 오른쪽 달력 첫째 주
			if (w == 7)
			{
				System.out.printf("       ");

				for (int j=1; j<w2; j++)
					System.out.print("    ");

				for (int j=1; j<=8-w2; j++)
				{
					System.out.printf("%4d", j);
					// 오른쪽 달력 날짜 저장하는 변수 n
					n++;
				}
			}

			// 오른쪽 달력 둘째, 셋째, 넷째, 다섯째 주
			if (w == 14 || w == 21 || w == 28 || w == 35)
			{
				System.out.printf("       ");
				
				// 한 주에 해당하는 일만 출력하기 위함
				n7 = n+7;

				for (int j=n; j<n7; j++)
				{
					System.out.printf("%4d", j);
					if (j==last2)
						break;
					n++;
				}
			}

			// 오른쪽 달력 여섯째 주
			if (w == 42)
			{
				System.out.printf("       ");
				
				// 오른쪽 달력에서 여섯째 주에 들어가야 하는 일이 있으면 출력
				if (n<last2)
				{
					for (int j=n; j<=last2; j++)
					{
						if (n<=last2)
							System.out.printf("%4d", j);
					}
				}
			}

			// 개행
			if (w%7==0 && w != 42 && i != last)
				System.out.println();
			w++;
		}

		System.out.print("\n============================       ============================\n");
	}// twoCalendar() 끝

	// 대여 시작 입력
	boolean userInputStart()
	{
		boolean success = false;
		String[] startDateSplit, startTimeSplit;
		int y, m , d, h, mn;
		y = m = d = h = mn = 0;
		int countD = 0;
		int countT = 0;

		// 달력 출력
		twoCalendar();
		
		// 안내 문구 출력
		System.out.println("\n* 대여 시작 날짜는 오늘부터 14일 이내의 날짜 중에서 선택 가능합니다. *");
		System.out.println("* 현재 시각에서 1시간 이후부터 예약 가능합니다. *");
		System.out.println("* 예약은 최소 2시간부터 최대 14일까지 가능합니다. *\n");
		System.out.println("* 예약 및 반납시간은 10분 단위로 선택 가능합니다. *\n");
		System.out.printf("\n* 현재 시각: %s *\n\n\n",nowtime1);

		try
		{	
			do
			{
				if (countD != 0)
					System.out.println("\n** 날짜를 잘못 입력하셨습니다. **\n");

				System.out.printf("대여 시작 날짜를 입력하세요.(예: %s) : ",nowtime2);
				startDate = sc.next();
				startDateSplit = startDate.split("-");
				y = Integer.parseInt(startDateSplit[0]);
				m = Integer.parseInt(startDateSplit[1]);
				d = Integer.parseInt(startDateSplit[2]);
				userDaysStart = compareDate(y,m,d); 
				
				countD++;

				if ((userDaysStart-nowDays) > 14)
				{
					System.out.println("\n** 14일 이내의 날짜까지 선택 가능합니다. 다시 입력해주세요. **\n");
					countD = 0;
				}

				if ((userDaysStart-nowDays) < 0)
				{
					System.out.println("\n** 현재 시각 이후부터 선택 가능합니다. 다시 입력해주세요. **\n");
					countD = 0;
				}
			}
			while ((y != nowY) || (m<1 || m>12) || (d<1 || d>31) || ((userDaysStart-nowDays) > 14) || (userDaysStart-nowDays) < 0);
		}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException f)
		{
			System.out.println("\n** 올바른 형식으로 입력하세요.**\n");
			sc = new Scanner(System.in);
			userInputStart();
		}

		try
		{
			do
			{
				if (countT != 0)
					System.out.println("\n** 시각을 잘못 입력하셨습니다. **\n");

				System.out.printf("대여 시작 시간을 입력하세요.(예: %s0): ",nowtime10);
				startTime = sc.next();
				startTimeSplit = startTime.split(":");
				h = Integer.parseInt(startTimeSplit[0]);
				mn = Integer.parseInt(startTimeSplit[1]);
				userTimeStart = compareTime(h,mn) + (userDaysStart-nowDays)*1440;
				
				countT++;
			
				while (mn!=0 && mn%10!=0) 
				{
					System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *\n");
				
					System.out.printf("대여 시작 시간을 입력하세요.(예: %s0): ",nowtime10);
					startTime = sc.next();
					startTimeSplit = startTime.split(":");
					h = Integer.parseInt(startTimeSplit[0]);
					mn = Integer.parseInt(startTimeSplit[1]);
					userTimeStart = compareTime(h,mn) + (userDaysStart-nowDays)*1440;
				}
        	    	
				if ((userTimeStart-nowTime) < 60)
				{
					System.out.println("\n** 1시간 이후부터 선택 가능합니다. 다시 입력해주세요. **\n");
					countT = 0;
				}
				

			}
			while ((h<0 || h>23) || (m<0 || m>59) || ((userTimeStart-nowTime) < 60) || ((userTimeStart-nowTime) < 0));
		}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException f)
		{
			System.out.println("\n** 올바른 형식으로 입력하세요.**\n");
			sc = new Scanner(System.in);
			userInputStart();
		}

		startDateArr = new String[]{Integer.toString(y), Integer.toString(m), Integer.toString(d), Integer.toString(h), Integer.toString(mn)};
		success = true;

		return success;
	} // userInputStart() 끝

	// 반납 예정 입력
	String[] userInputEnd()
	{
		System.out.println();

		String[] endDateSplit, endTimeSplit;
		int y, m , d, h, mn;
		y = m = d = h = mn = 0;
		int countD = 0;
		int countT = 0;

		try
		{
			do
			{
				if (countD != 0)
					System.out.println("\n** 날짜를 잘못 입력하셨습니다. **");

				System.out.printf("반납 예정 날짜를 입력하세요.(예: %s) : ",nowtime2);
				endDate = sc.next();
				endDateSplit = endDate.split("-");
				y = Integer.parseInt(endDateSplit[0]);
				m = Integer.parseInt(endDateSplit[1]);
				d = Integer.parseInt(endDateSplit[2]);
				userDaysEnd = compareDate(y,m,d); 
				
				countD++;

				if ((userDaysEnd-userDaysStart) > 14)
				{
					System.out.println("\n** 14일 이내의 날짜까지 선택 가능합니다. 다시 입력해주세요. **\n");
					countD = 0;
				}

				if ((userDaysEnd-userDaysStart) < 0)
				{
					System.out.println("\n** 현재 시각 이후부터 선택 가능합니다. 다시 입력해주세요. **\n");
					countD = 0;
				}
			}
			while ((y != nowY) || (m<1 || m>12) || (d<1 || d>31) || ((userDaysEnd-userDaysStart) > 14) || (userDaysEnd-userDaysStart) < 0);
		}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException f)
		{
			sc = new Scanner(System.in);
			System.out.println("\n** 올바른 형식으로 입력하세요.**\n");
			userInputEnd();
		}

		try
		{
			do
			{
				if (countT != 0)
					System.out.println("\n** 시각을 잘못 입력하셨습니다. **");

				System.out.printf("반납 예정 시간을 입력하세요.(예: %s0): ",nowtime10);
				endTime = sc.next();
				endTimeSplit = endTime.split(":");
				h = Integer.parseInt(endTimeSplit[0]);
				mn = Integer.parseInt(endTimeSplit[1]);
				userTimeEnd = compareTime(h,mn)  + (userDaysEnd-nowDays)*1440;
				rentTime = userTimeEnd-userTimeStart;

				countT++;

				while(mn!=0 && mn%10!=0)
				{	
					System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *\n");
				
					System.out.printf("반납 예정 시간을 입력하세요.(예: %s0): ",nowtime10);
					endTime = sc.next();
					endTimeSplit = endTime.split(":");
					h = Integer.parseInt(endTimeSplit[0]);
					mn = Integer.parseInt(endTimeSplit[1]);
					userTimeEnd = compareTime(h,mn)  + (userDaysEnd-nowDays)*1440;
					rentTime = userTimeEnd-userTimeStart;
        		
				}
        		
				if (((userTimeEnd - userTimeStart) < 120) || (userTimeEnd - userTimeStart) < 0)
				{
					System.out.println("\n** 예약 시각 2시간 이후부터 선택 가능합니다. 다시 입력해주세요. **\n");
					countT = 0;
				}
			}
			while ((h<0 || h>23) || (m<0 || m>59) || ((userTimeEnd - userTimeStart) < 120) || ((userTimeEnd - userTimeStart) < 0));
		}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException f)
		{
			System.out.println("\n** 올바른 형식으로 입력하세요.**\n");
			sc = new Scanner(System.in);
			userInputEnd();
		}

		endDateArr = new String[]{Integer.toString(y), Integer.toString(m), Integer.toString(d), Integer.toString(h), Integer.toString(mn)};	
		
		rd.putReservStartData(new Reserv(loginUser.getName(), loginUser.getLicenseNum(), startDateArr[0], startDateArr[1], startDateArr[2], startDateArr[3], startDateArr[4], endDateArr[0], endDateArr[1], endDateArr[2], endDateArr[3], endDateArr[4]));

		reserveInfo[0] = loginUser.getName();
		reserveInfo[1] = loginUser.getLicenseNum();
		reserveInfo[2] = startDateArr[0] + "년 " + startDateArr[1] + "월 " + startDateArr[2] +"일 "+ startDateArr[3] + "시" + startDateArr[4] + "분";
		reserveInfo[3] = endDateArr[0] + "년 " + endDateArr[1] + "월 " + endDateArr[2] +"일 "+ endDateArr[3] + "시" + endDateArr[4] + "분";

		return reserveInfo;
	}
	
	int compareDate(int y, int m, int d)
	{
		int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};

		if (y % 4 == 0 && y % 100 !=0 || y % 400 == 0)
			days[1] = 29;

		int checkDays = (y-1)*365 + (y-1)/4 - (y-1)/100 + (y-1)/400;

		for (int i=0; i<m-1; i++)
			checkDays += days[i];

		checkDays += d;

		return checkDays;
	}

	int compareTime(int h, int mn)
	{
		int checkTime = (h*60) + mn;

		return checkTime;
	}
}

class DateTime
{
	public static void main(String[] args)
	{
		// 테스트
		InputDate id = new InputDate();
		id.userInputStart();
		id.userInputEnd();
	}
}

/*
[실행 결과]

        [2021년 3월]                       [2021년 4월]
 일  월  화  수  목  금  토         일  월  화  수  목  금  토
============================       ============================
       1   2   3   4   5   6                          1   2   3
   7   8   9  10  11  12  13          4   5   6   7   8   9  10
  14  15  16  17  18  19  20         11  12  13  14  15  16  17
  21  22  23  24  25  26  27         18  19  20  21  22  23  24
  28  29  30  31                     25  26  27  28  29  30

============================       ============================

대여 시작 날짜를 입력하세요.(예: 2021-03-17) : 2021-03-17
대여 시작 시간을 입력하세요.(예: 18:30): 18:30

반납 예정 날짜를 입력하세요.(예: 2021-03-17) : 2021-03-17
반납 예정 시간을 입력하세요.(예: 18:30): 20:30
계속하려면 아무 키나 누르십시오 . . .
*/