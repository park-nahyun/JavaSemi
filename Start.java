import java.util.Scanner;
import java.util.InputMismatchException; 


class Start
{
	// 메소드내에서 return 하면 메소드를 빠져나오기 때문에
	// 메소드를 호출한 곳으로 돌아오는 원리를 적용 
	Login l;
	Join j;
	Cars c;
	Payment pm;
	Insurance insu;

	static InputDate id;
	static CarFrame selCar;
	static Inquiry iq;
	static UserData ud;
	static User loginUser;
	
	static String loginUserId;
	static String[] reserveInfo;

	int amount; // 결제금액

	CheckSelection cs;

	// 로그인 유저의 결제 내용 받아오는 변수들
	private String userCardNum;		// (카드일 때) 사용한 카드 번호
	private String userCardBank;	// (카드일 때) 사용한 카드사
	private int userPaidAmount;		// (현금일 때) 투입한 금액
	private String paymentType;		// 결제방식(현금, 카드)
	private int priceWithCoupon;

	Scanner sc = new Scanner(System.in);

	Start()
	{	
		l = new Login();
		j = new Join();
		c = new Cars();
		ud = new UserData();
		loginUser = new User();
		id = new InputDate();
	}


	void startScreen()
	{
		int userInput = 0;
		
		do
		{
			try
			{
				System.out.println("\n【 시작 화면 】");
				System.out.println();
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");
				System.out.println();
				System.out.print("\n>> 입력: ");
				userInput = sc.nextInt();
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *");
				System.out.println("\n\n-----------------------------------------------------------------------\n");
			}
		}
		while(userInput!=1 && userInput!=2);

		if (userInput == 1)
		{
			loginUserId = l.login();
			if (loginUserId != "")
				afterLoginScreen();
			else
				startScreen();
		}
		else if (userInput == 2)
		{
			ud = j.join();
			loginUser = ud.getLoginUserInfo(loginUserId);
		}
	}

	void afterLoginScreen()
	{
		int userInput;		
		id = new InputDate(loginUser);

		String userName = loginUser.getName();

		System.out.print("\n\n-----------------------------------------------------------------------\n\n");
		System.out.println("\n* "+userName+" 님 환영합니다. *");
		System.out.print("\n\n-----------------------------------------------------------------------\n\n");

		do
		{
			System.out.println("\n【 뚜또 렌터카 】");
			System.out.println();
			System.out.println("1. 신규 예약");
			System.out.println("2. 예약 확인");
			System.out.println("3. 로그아웃");
			System.out.println("4. 종료(관리자만 가능)");
			System.out.println();
			System.out.print("\n>> 입력: ");
			userInput = sc.nextInt();
		}
		while(userInput!=1 && userInput!=2 && userInput!=3 && userInput!=4);

		System.out.println();
		
		try
		{
			if (userInput == 1)
			{
				selectDate();
			}
			else if (userInput == 2)
			{
				iq.clear();
				afterLoginScreen();
			}
			else if (userInput == 3)
			{
				startScreen();
			}
			else if(userInput == 4)
			{
				System.out.println("\n-----------------------------------------------------------------------\n");
				System.out.println("\n관리자 모드 접근 암호를 입력해주세요.");
				System.out.print("\n>> 입력: ");
				int adminPw = sc.nextInt();
				if (adminPw == 160905)
					exitProgram();
				else
				{
					System.out.println("\n\n-----------------------------------------------------------------------\n");
					System.out.println("\n* 관리자만 접근 가능합니다. 메뉴로 돌아갑니다. *");
					afterLoginScreen();
				}
					

			}
		}
		catch(NullPointerException e)
		{
			System.out.print("현재 예약된 내역이 없습니다.\n");
			Scanner sc = new Scanner(System.in);

			int n;

			do
			{
				System.out.println("예약하시겠습니까?");
				System.out.print("1. 예 (예약화면으로 이동)\n");		//→ 대여 시간 및 날짜 선택으로 이동
				System.out.print("2. 아니오 (초기화면으로 이동)\n");	//→ 초기화면으로 이동
				System.out.print(">> 입력 : ");
				n = sc.nextInt();
				System.out.println();
			}
			while (n<1 || n>2);
			
			switch(n)
			{
				case 1: selectDate(); break;
				case 2: startScreen(); break;				
			}
			sc.close();
		}
		return;
	}

	void selectDate()
	{
		boolean successStart;

		successStart = id.userInputStart();

		if (successStart)
		{
			reserveInfo = id.userInputEnd();
			if (reserveInfo != null)
			{
				selectCar();
			}
			else
				return;
		}
		else
			return;
	}

	void selectCar()
	{
		c.updateCar(c);
		c.printCar();
		int userSel = c.selectCar();
		
		switch (userSel)
		{
			case -1: selectDate();	break;	// -1을 리턴했다 → 이전으로 되돌아가기를 선택
			case 1: selCar = c.selCar;		// 1을 리턴했다 → 사용자가 선택한 번호의 차를 받아와서 selCar변수에 담았다.
					selectInsu();	break;
			case 2: selectCar();	break;	// 2를 리턴했다 → 잘못된 값을 입력해서 selectCar()를 재호출 해야한다.
		}

		return;
	}

	void selectInsu()
	{
		insu = new Insurance(selCar);		// 위에서 정보를 채워둔 selCar를 생성자의 매개변수로 넘겨줌 
		
		int userSel2 = insu.selectIns();	// 선택한 차에 대해 보험 옵션을 계산한다. 
		
		if (userSel2 == -1)
			selectCar();
		else
		{
			amount = insu.amount;
			checkSelection();
		}

		return;
	}

	void checkSelection()
	{
		cs = new CheckSelection(reserveInfo, selCar);

		int userSel = cs.checkInfo1();

		switch (userSel)
		{
			case 1: payment();
			case 2: userSel = cs.checkInfo2();
					switch (userSel)
					{
						case 1: selectDate(); break;
						case 2: selectCar(); break;
						case 3: selectInsu(); break;
					}
		}

		return;
	}

	void payment()
	{
		pm = new Payment(c, selCar);
		//System.out.println("payment메소드 내부의 amount : " + amount);
		boolean success = pm.run(amount);
		
		userCardBank = pm.user.getBank();
		userCardNum = pm.user.getNum();
		userPaidAmount = pm.getPaidAmount();
		paymentType = pm.user.getPayment();
		priceWithCoupon = pm.getPriceWithCoupon();

		if(paymentType.equals("현금"))	// 사용자가 현금으로 결제 시 
		{	
			iq = new Inquiry(selCar, loginUserId, ud, reserveInfo, paymentType, userPaidAmount, priceWithCoupon);
			iq.cashReceiptPrint();
		}
		else
		{
			iq = new Inquiry(selCar, loginUserId, ud, reserveInfo, paymentType, userCardNum, userCardBank, priceWithCoupon);
			iq.cardReceiptPrint();	
		}
		
		if(success)
			c = pm.changeRentalState();
		
		try
		{
			Thread.sleep(3000);
			startScreen();
		}
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}
	}


	// 프로그램 완전 종료
	void exitProgram()
	{
		System.out.println("\n\n-----------------------------------------------------------------------\n\n");
		System.out.println("*  프로그램이 종료됩니다.  *");
		System.out.println("* 이용해주셔서 감사합니다. *\n\n");
		try
		{
			Thread.sleep(2000);
			System.exit(0);
		}
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}
	}
}
