import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.DecimalFormat;


class Insurance
{
	static CarFrame selCar;
	Payment pm = new Payment();
	Cars cr = new Cars();

	private int sel;
	int selCarPrice;
	static double sum;
	static int amount;

	DecimalFormat df = new DecimalFormat("0,000"); // 숫자를 000,000 표현해주는 클래스 생성

	int userSel;
	
	{
		selCar = new CarFrame();
	}	

	Insurance(CarFrame selCar)
	{
		Insurance.selCar = selCar;
	}

	Insurance()
	{
	}


	public int selectIns()
	{
		selCarPrice = cr.selCarPrice;

		Scanner sc = new Scanner(System.in);

		System.out.print("\n\n--------------------------------------------------------------------\n\n");
		System.out.println("\n*  보험 선택으로 이동합니다.  *");
		System.out.print("\n\n--------------------------------------------------------------------\n\n");
		
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}

		do
		{
			try
			{
				System.out.println("\n【 보험 옵션 선택(사고 시 운전자 부담 금액) 】\n");
				System.out.println("1. 자기 부담금 5만원");
				System.out.println("2. 자기 부담금 30만원");
				System.out.println("3. 자기 부담금 70만원");
				
				System.out.println("\n* 이전 단계로 돌아가기: -1 *");
				System.out.print("\n\n>> 선택할 옵션 번호 입력(1~3): ");
				sel = sc.nextInt();
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *");
			}
		}
		while(sel!=-1 && sel!=1 && sel!=2 && sel!=3);
		
		if(sel == -1)
			userSel = -1;

		if(sel == 1)
		{
			selCar.setInsuOption("자기 부담금 5만원");
			opCal_s();
		}
		else if(sel == 2) 
		{
			selCar.setInsuOption("자기 부담금 30만원");
			opCal_m();
		}
		else if(sel == 3)
		{
			selCar.setInsuOption("자기 부담금 70만원");
			opCal_l();	
		}

		sc.close();
		return userSel;
	}
	
	// 보험 옵션 5만원
	public void opCal_s()
	{
		double[] insRate = new double[24];
		insRate[0] = 1;
		double price=0;

		selCarPrice = cr.selCarPrice;

		for (int i=0;i<17 ;i++)
		{
				if (i<24)
					price += 0.0175*i*selCar.getInsurance()[0];
			
				else if (i>=24 && i<168)
				{
					price += 0.0125*i*selCar.getInsurance()[0];
				}
				else
					price += 0.005*i*selCar.getInsurance()[0];
					
		}

		sum = price + selCarPrice;
		
		System.out.print("\n\n-----------------------------------------------------------------------\n\n");
		System.out.println("\n* 최종 결제 예정 금액: " + df.format((int)(Math.round(sum/10)*10)) + " 원 *\n");
		amount = ((int)(Math.round(sum/10)*10));
	}
		
	// 보험 옵션 30만원
	public void opCal_m()
	{
		double[] insRate = new double[24];
		insRate[0] = 1;
		double price=0;

		selCarPrice = cr.selCarPrice;
		
		for (int i=0;i<17 ;i++)
		{
				if (i<24)
					price += 0.015*i*selCar.getInsurance()[0];
			
				else if (i>=24 && i<168)
				{
					price += 0.0075*i*selCar.getInsurance()[0];
				}
				else
					price += 0.001*i*selCar.getInsurance()[0];
		}

		sum = price + selCarPrice;
		
		System.out.print("\n\n-----------------------------------------------------------------------\n\n");
		System.out.println("\n* 최종 결제 예정 금액: " + df.format((int)(Math.round(sum/10)*10)) + " 원 *\n");

		amount = ((int)(Math.round(sum/10)*10));
	}
	
	// 보험 옵션 70만원
	public void opCal_l()
	{
		double[] insRate = new double[24];
		insRate[0] = 1;
		double price=0;

		selCarPrice = cr.selCarPrice;

		for (int i=0;i<17 ;i++)
		{
				if (i<24)
					price += 0.005*i*selCar.getInsurance()[0];
			
				else if (i>=24 && i<168)
				{
					price += 0.00075*i*selCar.getInsurance()[0];
				}
				else
					price += 0.0004*i*selCar.getInsurance()[0];
					
		}

		sum = price + selCarPrice;

		System.out.print("\n\n-----------------------------------------------------------------------\n\n");
		System.out.println("\n* 최종 결제 예정 금액: " + df.format((int)(Math.round(sum/10)*10))+" 원 *\n");

		amount = ((int)(Math.round(sum/10)*10));
	}
}