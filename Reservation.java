class Reserv
{
	private static String name;			// 유저 예약 정보 확인용
	private static String licenseNum;	// 유저 예약 정보 확인용
	private static String reservNum;
	private static String startYear;
	private static String startMonth;
	private static String startDate;
	private static String startHour;
	private static String startMinute;
	private static String endYear;
	private static String endMonth;
	private static String endDate;
	private static String endHour;
	private static String endMinute;
	private static String cardName;		// 카드사
	private static String cardNum;		// 카드 번호
	private static String paySum;		// 결제 금액
	private static String endCheck;


	Reserv()
	{
	}

	Reserv(String startyear, String startMonth, String startDate, String startHour, String startMinute)
	{
		this.startYear = startYear;
		this.startMonth = startMonth;
		this.startDate = startDate;
		this.startHour = startHour;
		this.startMinute = startMinute;
	}

	Reserv(String endYear, String endMonth, String endDate, String endHour, String endMinute, String endCheck)
	{
		this.endYear = endYear;
		this.endMonth = endMonth;
		this.endDate = endDate;
		this.endHour = endHour;
		this.endMinute = endMinute;
		this.endCheck = endCheck;
	}
	
	Reserv(String name, String licenseNum, String startyear, String startMonth, String startDate, String startHour, String startMinute, String endYear, String endMonth, String endDate, String endHour, String endMinute)
	{
		this.name = name;
		this.licenseNum = licenseNum;
		this.startYear = startYear;
		this.startMonth = startMonth;
		this.startDate = startDate;
		this.startHour = startHour;
		this.startMinute = startMinute;
		this.endYear = endYear;
		this.endMonth = endMonth;
		this.endDate = endDate;
		this.endHour = endHour;
		this.endMinute = endMinute;
		this.cardName = cardName;
		this.cardNum = cardNum;
		this.paySum = paySum;
		this.endCheck = endCheck;
	}

	Reserv(String name, String licenseNum, String reservNum, String startyear, String startMonth, String startDate, String startHour, String startMinute, String startTime, String endYear, String endMonth, String endDate, String endHour, String endMinute, String cardName, String cardNum, String paySum, String endCheck)
	{
		this.name = name;
		this.licenseNum = licenseNum;
		this.reservNum = reservNum;
		this.startYear = startYear;
		this.startMonth = startMonth;
		this.startDate = startDate;
		this.startHour = startHour;
		this.startMinute = startMinute;
		this.endYear = endYear;
		this.endMonth = endMonth;
		this.endDate = endDate;
		this.endHour = endHour;
		this.endMinute = endMinute;
		this.cardName = cardName;
		this.cardNum = cardNum;
		this.paySum = paySum;
		this.endCheck = endCheck;
	}
  
	{
		this.name = "";
		this.licenseNum = "";
		this.reservNum = "";
		this.startYear = "";
		this.startMonth = "";
		this.startDate = "";
		this.startHour = "";
		this.startMinute = "";
		this.endYear = "";
		this.endMonth = "";
		this.endDate = "";
		this.endHour = "";
		this.endMinute = "";
		this.cardName = "";
		this.cardNum = "";
		this.paySum = "";
		this.endCheck = "";
	}
  
	// getter 생성
	public String getName()
	{
		return name;
	}
	
	public String getLicenseNum()
	{
		return licenseNum;
	}	

	public String getReservNum()
	{
		return reservNum;
	}	

	public String getStartYear()
	{
		return startYear;
	}

	public String getStartMonth()
	{
		return startMonth;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public String getStartHour()
	{
		return startHour;
	}

	public String getStartMinute()
	{
		return startMinute;
	}

	public String getEndYear()
	{
		return endYear;
	}

	public String getEndMonth()
	{
		return endMonth;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public String getEndHour()
	{
		return endHour;
	}

	public String getEndMinute()
	{
		return endMinute;
	}

	public String getCardName()
	{
		return cardName;
	}

	public String getPaySum()
	{
		return paySum;
	}

	public String getEndCheck()
	{
		return endCheck;
	}


	// setter 생성
	public void setName(String name)
	{
		this.name = name;
	}

	public void setLicenseNum(String licenseNum)
	{
		this.licenseNum = licenseNum;
	}

	public void setReservNum(String reservNum)
	{
		this.reservNum = reservNum;
	}

	public void setStartYear(String startYear)
	{
		this.startYear = startYear;
	}

	public void setStartMonth(String startMonth)
	{
		this.startMonth = startMonth;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public void setStartHour(String startHour)
	{
		this.startHour = startHour;
	}

	public void setStartMinute(String startMinute)
	{
		this.startMinute = startMinute;
	}

	public void setEndYear(String endYear)
	{
		this.endYear = endYear;
	}

	public void setEndMonth(String endMonth)
	{
		this.endMonth = endMonth;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public void setEndHour(String endHour)
	{
		this.endHour = endHour;
	}

	public void setEndMinute(String endMinute)
	{
		this.endMinute = endMinute;
	}

	public void setCardName(String cardName)
	{
		this.cardName = cardName;
	}

	public void setPaySum(String paySum)
	{
		this.paySum = paySum;
	}

	public void setEndCheck(String endCheck)
	{
		this.endCheck = endCheck;
	}
}