import java.util.Scanner;


class CheckSelection
{
	//선택내용 보여주기
	int userSel;
	String[] reserveInfo;
	CarFrame selCar;

	CheckSelection(String[] reserveInfo, CarFrame selCar)
	{
		this.reserveInfo = reserveInfo;
		this.selCar = selCar;
	}
	

	public int checkInfo1()
	{
		Scanner sc = new Scanner(System.in);

		System.out.print("\n=========================================\n");
		// 대여시작일(시간포함)
		System.out.printf("대여 시작 날짜 : %s\n", reserveInfo[2]);
		// 반납예정일(시간포함)
		System.out.printf("반납 예정 일자 : %s\n", reserveInfo[3]);
		// 선택 차종
		System.out.printf("선택 차종 : %s\n", selCar.getCarModel());
		// 보험 옵션
		System.out.printf("보험 옵션 : %s\n", selCar.getInsuOption());
		System.out.print("=========================================\n");
		
		int n;

		do
		{
			System.out.print("\n입력된 정보가 맞습니까?\n\n");
			System.out.print("1. 예\n");		// 결제 금액으로
			System.out.print("2. 아니오\n");	
			System.out.print("\n\n>> 입력: ");	// 1. 예, 2. 아니오
			n = sc.nextInt();					// y 입력하면 결제 금액으로 		    
		}
		while (n<1 || n>2);

		switch (n)
		{  
			case 1: userSel=1; break;	
			case 2: userSel=2; break;
		}
	
		return userSel;
	}

	public int checkInfo2()
	{
		Scanner sc = new Scanner(System.in);

		int n;

		do
		{
			System.out.print("1. 날짜 및 시간 재입력\n");
			System.out.print("2. 차종 재입력\n");
			System.out.print("3. 보험 옵션 재입력\n");
			System.out.print(">> 입력: ");
			n = sc.nextInt();
			System.out.println();
		}
		while (n<1 || n>4);
		
		switch (n)
		{
			case 1: userSel=1 ; break;	
			case 2: userSel=2 ; break;
			case 3: userSel=3 ; break;	
		}
		
		return userSel;
	}
}


/*
- 선택 내용 확인 후 '입력된 정보가 맞습니까?
======================================
대여 시작 날짜 : 2021.03.12(금) 15:00
반납 예정 일자 : 2021.03.15(월) 15:00
선택 차종 : 올뉴제네시스 G80
보험 옵션 : 자기부담금 30만원
======================================
- 1. 예 → 결제 금액 안내
- 2. 아니오 → 돌아가기 옵션 출력
1. 날짜 및 시간 재입력
2. 차종 재입력
3. 보험 옵션 재입력
- 1번 선택 시, 차종, 보험옵션 입력하는 부분 거쳐서 순차적으로 돌아오도록.
*/