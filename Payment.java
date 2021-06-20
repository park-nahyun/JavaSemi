import java.util.Scanner;
import java.util.HashMap;
import java.util.Vector;
import java.util.Arrays;
import java.util.ArrayList;
import java.text.DecimalFormat;


class Payment
{
	private int[] cash = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
	private int price;
	private int sel;				// 사용자 선택값 저장 변수
	private int change;				// 거스름돈 저장
	private int paidAmount;			// 사용자가 낸 현금 합계
	private String paymentType; 	// 사용자가 선택한 지불방식(현금, 카드)
	private int priceWithCoupon;
	Scanner sc;
	
	DecimalFormat df = new DecimalFormat("0,000"); // 숫자를 000,000 표현해주는 클래스 생성

	HashMap<String, String> couponMap = new HashMap<String, String>();
	
	Cars c;
	CarFrame selCar;
	UserCard user;
	User u;
	CreditCard credit;
	CheckCard check;
	boolean flag = false;
	boolean flag2 = false;

	ArrayList<String[]> couponKey = new ArrayList<String[]>();

	Payment()
	{
		user = new UserCard();
		credit = new CreditCard();
		check = new CheckCard();
		u = new User();
	}

	Payment(Cars c, CarFrame selCar)
	{
		user = new UserCard();
		credit = new CreditCard();
		check = new CheckCard();
		u = new User();
		this.c = c;
		this.selCar = selCar;	
	}

	public void setPaidAmount(int paidAmount)
	{
		this.paidAmount = paidAmount;
	}
	
	public int getPaidAmount()
	{
		return paidAmount;
	}

	public void setPriceWithCoupon(int priceWithCoupon)
	{
		this.priceWithCoupon = priceWithCoupon;
	}

	public int getPriceWithCoupon()
	{
		return priceWithCoupon;
	}
	

	// 외부에서 결제를 위해 호출하는 메소드
	public boolean run(int price)
	{
		boolean success = false;
		this.price = price;

		couponAdd();
		select();

		switch (sel)
		{
			case 1: System.out.println("\n\n* 최종 결제 예정 금액 : " + df.format(priceWithCoupon) + " *"); inputCash(); break;
			case 2: System.out.println("\n\n* 최종 결제 예정 금액 : " + df.format(priceWithCoupon) + " *"); inputCardNum(); break;
		}
	
		System.out.print("\n\n--------------------------------------------------------------------\n\n");

		success = true;

		return success; 
	}
	
	// 쿠폰 조회 및 적용
	void couponAdd()
	{
		couponMap = u.getCoupon();

		Object[] mapkey = couponMap.keySet().toArray();
		Arrays.sort(mapkey);

		price *= (100-(Double.parseDouble(String.valueOf(couponMap.get(mapkey[0])))))/100;
		priceWithCoupon = Math.round(price/10)*10;

		System.out.print("\n\n--------------------------------------------------------------------\n\n");
		System.out.println("\n* 보유 쿠폰 목록 *\n");
		
		for (Object obj : mapkey)
			couponKey.add(String.valueOf(obj).split(":::"));
		
		int i = 1; 

		for (String[] str : couponKey)
		{
			System.out.println(i+". "+str[1]);
			i++;
		}
			
		System.out.print("\n\n* 이중 가장 할인율이 높은 쿠폰이 자동 적용됩니다. *\n");
	}

	// 현금, 카드 선택
	private void select()
	{
		sc = new Scanner(System.in);
 
		System.out.print("\n\n--------------------------------------------------------------------\n\n");

		do
		{
			System.out.println("\n【 결제 방식 선택 】\n");
			System.out.println("1. 현금 결제 ");
			System.out.println("2. 카드 결제 ");
			System.out.print("\n>> 입력: ");
			sel = sc.nextInt();
		}
		while (sel!=1 && sel!=2);
	}

	// 현금 결제 메소드
	private void inputCash()
	{
		sc = new Scanner(System.in);

		int inputCash;

		user.setPayment("현금");
		
		do
		{
			System.out.print("\n\n--------------------------------------------------------------------\n\n\n");
			System.out.println("현금을 투입하세요.");
			System.out.print("\n>> ");
			inputCash = sc.nextInt();
		}
		while (!isCash(inputCash));
		
		paidAmount += inputCash; // 사용자가 투입한 금액의 합계를 저장

		if (inputCash > price)
		{
			change = inputCash-price;
			price = 0;
			System.out.println("\n\n남은 금액 : " + price);
			System.out.println("거스름돈  : " + df.format(change));
		}
		else if (inputCash == price)
		{
			price = 0;
			System.out.println("\n\n남은 금액 : " + price);
			System.out.println("거스름돈  : " + df.format(change));
		}
		else if (inputCash < price)
		{
			price -= inputCash;
			System.out.println("\n\n남은 금액 : " + df.format(price));
			inputCash();
		}
		if(flag==false)
		{
			printChange();
			printPaymentResult();
			flag = true;
		}
	}

	private boolean isCash(int inputCash)
	{
		for (int i : cash)
		{
			if(inputCash == i)
				return true;
		}
		return false;	
	}

	public void printChange()
	{
		int cnt;
		String s = "";
		flag = true;

		System.out.print("\n\n--------------------------------------------------------------------\n\n\n");
		printSleep("거스름돈 반환 중");
		System.out.print("\n--------------------------------------------------------------------\n\n\n");

		for (int i=0; i<cash.length; i++)
		{
			if (change!=0)
			{
				cnt = change/cash[i];
				change %= cash[i];
				
				if (cnt>0)
				{
					if (i==1)
					{
						System.out.printf("┏━━━━━━━━┓\n");
						System.out.printf("┃  10000 ┃  Ⅹ %d\n", cnt);
						System.out.printf("┗━━━━━━━━┛\n");
					}
					if (i == 2)
					{
						System.out.printf("┏━━━━━━━┓\n");
						System.out.printf("┃  5000 ┃ Ⅹ %d\n", cnt);
						System.out.printf("┗━━━━━━━┛\n");
					}
					if (i == 3)
					{
						System.out.printf("┏━━━━━━━┓\n");
						System.out.printf("┃  1000 ┃ Ⅹ %d\n", cnt);
						System.out.printf("┗━━━━━━━┛\n");
					}
					if (i== 4)
					{
						System.out.printf(" ⑤ⓞⓞ Ⅹ %d", cnt);
					}
					if (i == 5)
					{
						System.out.printf(" ①ⓞⓞ Ⅹ %d", cnt);
					}
					if (i == 6)
					{
						System.out.printf(" ⑤ⓞ Ⅹ %d", cnt);
					}
					if (i == 7)
					{
						System.out.printf(" ①ⓞ Ⅹ %d", cnt);
					}
				}
			}
		}
		System.out.printf("\n%s\n", s);
		System.out.print("\n--------------------------------------------------------------------\n\n");
	}


	// 사용자 카드 입력 받는 메소드 (카드리더기 역할)
	private void inputCardNum()
	{
		sc = new Scanner(System.in);

		int n;	// 사용자가 선택한 카드사

		System.out.print("\n\n--------------------------------------------------------------------\n\n");
		
		do
		{
			System.out.println("\n카드사를 선택해주세요.\n");

			for (int i=0; i<user.BANK_LIST.length; i++)
			{
				System.out.printf("%d. %s\n", (i+1), user.BANK_LIST[i]);
			}
			System.out.print("\n\n>> 입력: ");

			n = sc.nextInt();
		}
		while (n<1 || n>7);
		
		switch (n)
		{
			case 1: user.setBank("국민"); break;
			case 2: user.setBank("농협"); break;
			case 3: user.setBank("롯데"); break;
			case 4: user.setBank("삼성"); break;
			case 5: user.setBank("신한"); break;
			case 6: user.setBank("우리"); break;
			case 7: user.setBank("하나"); break;
		}
		
		System.out.print("\n\n--------------------------------------------------------------------\n\n");
		System.out.print("\n카드번호 16자리를 입력해주세요(하이픈 포함) : ");
		user.setNum(sc.next());

		cardCompare();
		seperate();
		printPaymentResult();
	}

	private void cardCompare()
	{
		// 체크카드 번호의 key로 존재하고, value와 입력한 카드사가 같으면 
		if (check.containsNum(user.getNum()) && (check.getBank(user.getNum()) == user.getBank()))
		{
			user.setValid(true);		// 유효하고
			user.setType(false);		// 체크카드
		}
		else if (credit.containsNum(user.getNum()) && (credit.getBank(user.getNum()) == user.getBank()))
		{
			user.setValid(true);		// 유효하고
			user.setType(true);			// 신용카드
		}
		else
		{
			user.setValid(false);
			System.out.println("\n* 유효하지 않은 카드번호 입니다. *\n");
			inputCardNum();		// 이전단계로 돌아가는 것 구현 필요
		}
	}

	private void seperate()	// 일시불, 할부(개월) 
	{
		sc = new Scanner(System.in);
		int n=0;
		int halbu=0;

		//일시불 할부 선택
		if(user.getValid()==true && price>=50000)
		{
			System.out.print("\n\n--------------------------------------------------------------------\n\n");

			do
			{
				System.out.println("\n원하시는 결제 방식을 선택해주세요.\n");
				System.out.println("1. 일시불");
				System.out.println("2. 할부");
				System.out.print("\n>> ");
				n = sc.nextInt();
			}
			while (n!=1 && n!=2);

			if (n==2 && user.getType()==false)
			{	
				System.out.println("\n* 체크카드는 일시불 결제만 가능합니다. *\n");
				seperate(); 
			}
			else if(n==2 && user.getType()==true)
			{
				do
				{
					System.out.print("\n할부 개월 수를 입력하세요(2~12) : ");
					System.out.print("\n>> 입력: ");
					halbu = sc.nextInt();
				}
				while (halbu<2 || halbu>12);
				user.setPayment("할부(" + halbu + "개월)");
			}
		}
		
		System.out.println();
		if (flag2==false)
		{
			System.out.print("\n--------------------------------------------------------------------\n\n\n");
			printSleep("결제 진행 중");
			System.out.print("\n--------------------------------------------------------------------\n\n");
			flag2=true;
		}
		
		System.out.println();
	}
	
	private void printPaymentResult()
	{
		System.out.println("\n* "+user.getPayment() + " 결제 완료 *");
	}

	public String getUserValue()
	{
		return user.getBank() + " " + user.getNum();
	}
	
	public Cars changeRentalState()
	{
		for(int i=0; i<c.whole.length; i++)
		{
			if(c.whole[i].getCarNum().equals(selCar.getCarNum()))
			{
				c.whole[i].setRentalState(false);
			}
		}

		return c;
	}

	public void printSleep(String str)
	{
		try
		{
			Thread.sleep(500);
		}	
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}	

		System.out.print("  ·");

		try
		{
			Thread.sleep(500);
		}	
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}		
		
		System.out.print("·");

		try
		{
			Thread.sleep(500);
		}			
		catch (InterruptedException e)
		{				
			System.out.println(e.toString());
		}		

		System.out.print("· ");		
		
		try
		{
			Thread.sleep(500);
		}			
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}	
		
		System.out.print(str);		

		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}		

		System.out.print(" ·");	
		
		try
		{
			Thread.sleep(500);
		}		
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}

		System.out.print("·");	
		
		try
		{
			Thread.sleep(500);
		}		
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}

		System.out.print("·");		
		
		try
		{
			Thread.sleep(500);
		}	
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}	
		
		System.out.println("\n");
	}
}