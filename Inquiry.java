import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Calendar;


class Inquiry
{	
	User loginUser;
	InputDate id;
	Payment pm;
	static Reserv rs;
	static CarFrame selCar;
	static UserData ud;

	String[] reserveInfo;
	String loginUserId;

	String paymentType;
	String userCardNum;
	String userCardBank;
	int paidAmount;
	int amount;
	int priceWithCoupon;

	String reserveNum = "";

	DecimalFormat df;

	{
		loginUser = new User();
		rs = new Reserv();
		selCar = new CarFrame();
		ud = new UserData();
		pm = new Payment();
		df = new DecimalFormat("0,000");
	}

	Inquiry()
	{		
	}

	// 사용자가 현금 결제시 사용하는 생성자
	Inquiry(CarFrame selCar, String loginUserId, UserData ud, String[] reserveInfo, String paymentType, int paidAmount, int priceWithCoupon)
	{
		this.selCar = selCar;
		this.loginUserId = loginUserId;
		this.ud = ud;
		this.reserveInfo = reserveInfo;
		this.paymentType = paymentType;
		this.paidAmount = paidAmount;
		this.priceWithCoupon = priceWithCoupon;
	}

	// 사용자가 카드 결제시 사용하는 생성자
	Inquiry(CarFrame selCar, String loginUserId, UserData ud, String[] reserveInfo, String paymentType, String userCardNum, String userCardBank, int priceWithCoupon)
	{
		this.selCar = selCar;
		this.loginUserId = loginUserId;
		this.ud = ud;
		this.reserveInfo = reserveInfo;
		this.paymentType = paymentType;
		this.userCardNum = userCardNum;
		this.userCardBank = userCardBank;
		this.priceWithCoupon = priceWithCoupon;
	}


	// 대여자 이름 가져오기 → loginUserId

	// 선택한 차종 가져오기 
	String selCarType = selCar.getCarType();
	

	public void clear()
	{	
		if (loginUser.getName()==reserveInfo[0] && loginUser.getLicenseNum()==reserveInfo[1])	//유저 정보와 예약 완료 정보가 같으면
		{
			//사용자 정보와 예약 완료 정보와 같으면
			System.out.print("현재 예약중인 렌터카\n");
			System.out.print("        대여시작일         |         반납예정일        |     차종    \n");
			System.out.print("==================================================================\n");
			
			// 대여 시작일, 반납 예정일, inputdate
			// 차종 TestCar에서 가져오기
			System.out.printf(" %20s | %20s | %s \n", reserveInfo[2], reserveInfo[3], selCar.getCarModel());

			System.out.print("==================================================================\n\n");
			}
		
		// 선택
		Scanner sc = new Scanner(System.in);

		int n;

		do
		{
			System.out.print("1. 예약 확인서 재출력\n");
			System.out.print("2. 이전으로 되돌아가기\n");
			System.out.printf(">> 입력 : ");
			n = sc.nextInt();
			System.out.println();
		}
		while (n<1 || n>3);
		
		// 1번일 때 예약 확인서 재출력 ↓ 2번일 때 이전으로 되돌아가기 ↓ login();
		switch (n)
		{
			case 1: if(paymentType.equals("현금"))
					cashReceiptPrint(); 
				else
					cardReceiptPrint();
				break;
			case 2: return;	// Start에서 clear()를 호출한 부분으로 되돌아감
		}
	}
	
	public void reserveNumber()
  	{
      	Calendar cal = Calendar.getInstance();
      	String mm = "M";

      	int y, m, d;
      	int rand = (int)(Math.random()*1000);

      	y=cal.get(Calendar.YEAR);
      	m=cal.get(Calendar.MONTH)+1;
      	d=cal.get(Calendar.DATE);

		if(m==10 || m==11 | m==12)
			reserveNum = mm + y + m + d + rand ;
      	else
      		reserveNum = mm + y + "0" + m + d + rand ;
  	}

	public void cashReceiptPrint()	
	{
		if (reserveNum=="")
			reserveNumber();

		System.out.println("\n===================================");
		System.out.println("            [예약 확인서]          ");
		System.out.println("===================================");
		System.out.printf("[예약번호]%24s\n", reserveNum);
		System.out.printf("[대 여 자]%20s\n",loginUser.getName());
		System.out.printf("[대여시작]%20s\n", reserveInfo[2]);
		System.out.printf("[반납예정]%20s\n", reserveInfo[3]);
		System.out.printf("[차    종]%20s\n", selCar.getCarModel());
		System.out.printf("[차량번호]%24s\n", selCar.getCarNum());
		System.out.printf("[보험옵션]%18s\n", selCar.getInsuOption());
		System.out.println("===================================");
		System.out.printf("[결제방식]%23s\n", paymentType);	
		System.out.printf("[투입금액]%24s\n", df.format(paidAmount));
		System.out.printf("[결제금액]%24s\n", df.format(priceWithCoupon));
		System.out.printf("[거스름돈]%24s\n", df.format(paidAmount-priceWithCoupon));
		System.out.println("===================================");

		System.out.println("\n* 초기 화면으로 돌아갑니다. *");
		System.out.println("* 이용해주셔서 감사합니다. *");

		try
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}

		System.out.print("\n\n--------------------------------------------------------------------\n\n");
	}

	public void cardReceiptPrint()	
	{
		if (reserveNum=="")
			reserveNumber();

		System.out.println("\n===================================");
		System.out.println("            [예약 확인서]          ");
		System.out.println("===================================");
		System.out.printf("[예약번호]%24s\n", reserveNum);	
		System.out.printf("[대 여 자]%20s\n",loginUser.getName());
		System.out.printf("[대여시작]%20s\n", reserveInfo[2]);
		System.out.printf("[반납예정]%20s\n", reserveInfo[3]);
		System.out.printf("[차    종]%24s\n", selCar.getCarModel());
		System.out.printf("[차량번호]%24s\n", selCar.getCarNum());
		System.out.printf("[보험옵션]%18s\n", selCar.getInsuOption());
		System.out.println("===================================");
		System.out.printf("[결제방식]%24s\n", paymentType);
		System.out.printf("[결제금액]%24s\n", df.format(priceWithCoupon));	
		System.out.println("===================================");

		System.out.println("\n* 시작 화면으로 돌아갑니다. *");
		System.out.println("* 이용해주셔서 감사합니다. *");

		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}

		System.out.print("\n\n--------------------------------------------------------------------\n\n");
	}
}