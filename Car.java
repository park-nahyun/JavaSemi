import java.util.*;
import java.text.DecimalFormat;
import java.util.InputMismatchException;


class CarFrame
{
	private String carNum;						//-- 차 번호
	private String carModel;					//-- 차종 모델명
	private String carType;						//-- 차 분류(경차, 소형, 중형, 대형, SUV)					
	private boolean rentalState = true;			//-- 대여상태
	private int pricePerHour;					//-- 시간당 가격 변수
	private int[] insurance;					//-- 기본 보험료(자기부담금 5,30,70)
	private String insuOption;

	CarFrame()
	{	
	}

	CarFrame(String carNum, String carModel, String carType, int pricePerHour, int[] insurance, String insuOption)
	{
		this.insurance = insurance;
		this.carNum = carNum;
		this.carModel = carModel;
		this.carType = carType;
		this.pricePerHour = pricePerHour;
		this.insuOption = insuOption;
	}

	public void setCarNum(String carNum)
	{
		this.carNum = carNum;
	}

	public String getCarNum()
	{
		return carNum;
	}

	public void setCarModel(String carModel)
	{
		this.carModel = carModel;
	}

	public String getCarModel()
	{
		return carModel;
	}

	public void setCarType(String carType)
	{
		this.carType = carType;
	}

	public String getCarType()
	{
		return carType;
	}

	public void setPricePerHour(int pricePerHour)
	{
		this.pricePerHour = pricePerHour;
	}

	public int getPricePerHour()
	{
		return pricePerHour;
	}

	public void setInsurance(int[] insurance) 
	{
		this.insurance = insurance;
	}

	public int[] getInsurance()
	{
		return insurance;
	}

	public void setRentalState(boolean rentalState)
	{
		this.rentalState = rentalState;
	}

	public boolean getRentalState()
	{
		return rentalState;
	}

	public void setInsuOption(String insuOption)
	{
		this.insuOption = insuOption;	
	}

	public String getInsuOption()
	{
		return insuOption;
	}

}


class Cars
{
	CarFrame[] whole = new CarFrame[15];
	CarFrame[] kyung = new CarFrame[3];
	CarFrame[] so = new CarFrame[3];
	CarFrame[] jung = new CarFrame[3];
	CarFrame[] dae = new CarFrame[3];
	CarFrame[] suv = new CarFrame[3];
	static CarFrame selCar;
	
	Vector<String> list;
	Vector<Integer> rent;

	Insurance insu;
	InputDate id = new InputDate();
	int rentPrice = 0;
	int sum =0;
	static int selCarPrice = 0;
	static String selCarNum;

	{
		selCar = new CarFrame();
	}

	Cars()
	{
		list = new Vector<String>();
		inputCars();
	}

	DecimalFormat df = new DecimalFormat("0,000"); // 숫자를 000,000 표현해주는 클래스 생성

	String[] carNums = {"94허2440", "91허7170", "26허9534", "64허3801", "90허3756",
						"45허9522", "06허0751", "44허3063", "71허3339", "48허0764",
						"79허4836", "77허1867", "58허2665", "90허8409", "56허1020"};

	String[] carModels = {"레이  ", "K3    ", "K5 ", "K9 ", "르노삼성XM3", "레이  ", "K3    ", "K5 ", "G80", "르노삼성XM3", 
						  "스파크", "아반떼", "SM6", "K9 ", "레인지로버 "};
	
	String[] carType = {"경차", "소형차", "중형차", "대형차", "suv"};

	int[] pricePerHour = {5580, 7040, 9580, 12130, 15210};

	int[][] insurance = {{2700, 1700, 1420}, {4040, 2670, 2120}, {4430, 2960, 2320}, {5070, 3670, 3070}, {6140, 4740, 4140}};

	
	public void inputCars()
	{		
		for (int i=0, j=0; i<carNums.length; i++, j++)
		{
			if(j==5)
				j=0;
		
			whole[i] = new CarFrame(carNums[i], carModels[i], carType[j], pricePerHour[j], insurance[j], "기본옵션");
		}

		for (int i=0, j=0; i<whole.length; j++)
		{
			kyung[j] = whole[i++];
			so[j] = whole[i++];
			jung[j] = whole[i++];
			dae[j] = whole[i++];
			suv[j] = whole[i++];
		}

	}

	public void updateCar(Cars c)
	{
		for(int i=0; i<c.whole.length; i++)	
			whole[i] = c.whole[i];

		for (int i=0, j=0; i<whole.length; j++)
		{
			kyung[j] = whole[i++];
			so[j] = whole[i++];
			jung[j] = whole[i++];
			dae[j] = whole[i++];
			suv[j] = whole[i++];
		}
	}

	// 사용 가능한 차 출력
	public void printCar()
	{
		int j = 1;

		int time = (InputDate.rentTime)/60 + 1;		// 사용자 대여 시간

		rent = new Vector<Integer>();

		System.out.print("\n\n-----------------------------------------------------------------------\n\n");
		System.out.println("\n*  차량 선택으로 이동합니다.  *");
		System.out.print("\n\n-----------------------------------------------------------------------\n\n");

		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}

		System.out.println("\n【 차종 선택 】\n\n");
		System.out.print("   차종     |     시간당 가격      |      대여금액\n");
		System.out.print("\n=================== [ 경 차 ] ====================\n\n");

		for (int i=0;i<kyung.length;i++)
		{	
			if (kyung[i].getRentalState())
			{
				for (int k=1; k<=time ; k++)
				{
					if (k==1)
						rentPrice += k*kyung[i].getPricePerHour();	
					else if (1<k && k<12)
						rentPrice += 0.15*k*kyung[i].getPricePerHour();		
					else if (12<=k && k<168)
						rentPrice += 0.004*k*kyung[i].getPricePerHour();
					else 
						rentPrice += 0.001*k*kyung[i].getPricePerHour();
				}
				System.out.print(j+". "+ kyung[i].getCarModel() +"            "+ df.format(kyung[i].getPricePerHour())+ " 원");
				System.out.printf("%18s 원%n", df.format(rentPrice)); 
				list.add(kyung[i].getCarNum());
				j++;
				rent.add(rentPrice);
				rentPrice=0;
			}
		}
		
		System.out.print("\n\n=================== [ 소형차 ] ===================\n\n");
		for (int i=0;i<so.length;i++)
		{	
			if (so[i].getRentalState())
			{
				for (int k=1; k<=time ; k++)
				{
					if (k==1)
						rentPrice += k*so[i].getPricePerHour();	
					else if (1<k && k<12)
						rentPrice += 0.15*k*so[i].getPricePerHour();
					else if (12<=k && k<168)
						rentPrice += 0.0025*k*so[i].getPricePerHour();
					else 
						rentPrice += 0.001*k*so[i].getPricePerHour();
				}

				System.out.print(j+". "+ so[i].getCarModel() + "            " + df.format(so[i].getPricePerHour()) + " 원");
				System.out.printf("%18s 원%n", df.format(rentPrice)); 
				list.add(so[i].getCarNum());
				j++;
				rent.add(rentPrice);
				rentPrice=0;
			}
		}

		System.out.print("\n\n=================== [ 중형차 ] ===================\n\n");
		for (int i=0;i<jung.length;i++)
		{	
			if (jung[i].getRentalState())
			{
				for (int k=1; k<=time ; k++)
				{
					if (k==1)
						rentPrice += k*jung[i].getPricePerHour();	
					else if (1<k && k<12)
						rentPrice += 0.15*k*jung[i].getPricePerHour();
					else if (12<=k && k<168)
						rentPrice += 0.003*k*jung[i].getPricePerHour();
					else 
						rentPrice += 0.001*k*jung[i].getPricePerHour();
				}

				System.out.print(j+". "+ jung[i].getCarModel() + "               " + df.format(jung[i].getPricePerHour()) + " 원");
				System.out.printf("%18s 원%n", df.format(rentPrice)); 
				list.add(jung[i].getCarNum());
				j++;
				rent.add(rentPrice);
				rentPrice=0;
			}
		}
		System.out.print("\n\n=================== [ 대형차 ] ===================\n\n");
		for (int i=0;i<dae.length;i++)
		{	
			if (dae[i].getRentalState())
			{
				for (int k=1; k<=time ; k++)
				{
					if (k==1)
						rentPrice += k*dae[i].getPricePerHour();	
					else if (1<k && k<12)
						rentPrice += 0.15*k*dae[i].getPricePerHour();		
					else if (12<=k && k<168)
						rentPrice += 0.003*k*dae[i].getPricePerHour();
					else 
						rentPrice += 0.001*k*dae[i].getPricePerHour();
				}
				System.out.print(j+". "+ dae[i].getCarModel() + "             " + df.format(dae[i].getPricePerHour()) + " 원");
				System.out.printf("%18s 원%n", df.format(rentPrice)); 
				list.add(dae[i].getCarNum());
				j++;
				rent.add(rentPrice);
				rentPrice=0;
			}
		}
		System.out.print("\n\n=================== [ SUV ] =====================\n\n");
		for (int i=0;i<suv.length;i++)
		{	
			if (suv[i].getRentalState())
			{
				for (int k=1; k<=time ; k++)
				{
					if (k==1)
						rentPrice += k*suv[i].getPricePerHour();	
					else if (1<k && k<12)
						rentPrice += 0.15*k*suv[i].getPricePerHour();		
					else if (12<=k && k<168)
						rentPrice += 0.003*k*suv[i].getPricePerHour();
					else 
						rentPrice += 0.0007*k*suv[i].getPricePerHour();
				}
				System.out.print(j+". "+ suv[i].getCarModel() + "     " + df.format(suv[i].getPricePerHour()) + " 원");
				System.out.printf("%18s 원%n", df.format(rentPrice)); 
				list.add(suv[i].getCarNum());
				j++;
				rent.add(rentPrice);
				rentPrice=0;
			}
		}
	}

	// 사용자가 차 선택
	public int selectCar()
	{
		Scanner sc = new Scanner(System.in); 

		int n;  // 사용자가 선택한 번호

		System.out.println();
		System.out.println("\n* 차종을 선택해주세요. *\n* 이전 단계로 돌아가기: -1 *\n\n");
		System.out.print(">> 입력: ");

		try
		{
			n = sc.nextInt();

			if (n==-1)
			{
				try
				{
					Thread.sleep(3000);
				}
				catch (InterruptedException e)
				{
					System.out.println(e.toString());
				}

				return -1;
			}
			else if (n>0 && n<=list.size())
			{
				for (int i=0;i<whole.length;i++)
				{
					if(whole[i].getCarNum().equals(list.get(n-1)))
					{
						selCar = whole[i];
						selCarPrice = rent.get(n-1);
						selCarNum = selCar.getCarNum();
					}
				}

				return 1;
			}
			else
			{
				System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *\n");
				return 2;
			}
			
		}
		catch (InputMismatchException e)
		{
			sc = new Scanner(System.in);
			System.out.println("\n* 숫자를 입력하세요 *\n");
			selectCar();
			return 0;
		}

	}		
}
