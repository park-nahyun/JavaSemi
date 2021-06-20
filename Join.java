import java.util.Scanner;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;


class Join
{
	CheckDriversLicense cdl = new CheckDriversLicense();

	UserData ud;
	User u;

	Scanner sc = new Scanner(System.in);
	SimpleDateFormat splitFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");   // 나중에 "-"로  split해서 쓸 것
	Date time = new Date();

	// 자리수 맞춰 입력할 때까지 입력 횟수 확인용
	int count;
	
	{
		ud = new UserData();
		u = new User();
	}
	

	// 입력받고 저장
	String[] joinDB(String userTel, String userPw, String userName, String userBirth, String userLicenseNum)
	{
		String[] joinData = {userTel,userPw,userName,userBirth,userLicenseNum};

		return joinData;
	}

	// 아이디 입력받기
	String inputUserTel()
	{	
		count = 0;
		String userTel = "";
		
		System.out.print("\n\n-----------------------------------------------------------------------\n\n");

		do
		{
			try
			{
				if (count == 0)
					System.out.print("\n사용하실 아이디(휴대폰번호 11자리)를 입력하세요. : ");
				else
					System.out.print("\n잘못 입력하셨습니다. 다시 입력해주세요. : ");
				userTel = sc.next();
				count++;
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *");
				System.out.println("\n\n-----------------------------------------------------------------------\n");
			}
		}
		while (userTel.length() != 11 || userTel.charAt(0)!='0' || userTel.charAt(1)!='1');
	
		return userTel;
	}

	// 비밀번호 입력받기
	String inputUserPw()
	{
		count = 0;
		String userPw = "";

		do
		{
			try
			{
				if (count == 0)
				{
					System.out.print("\n사용하실 비밀번호(6자리)를 입력하세요. : ");
					userPw = sc.next();
				}
				else
				{
					System.out.print("\n잘못 입력하셨습니다. 다시 입력해주세요. : ");
					userPw = sc.next();
				}
				count++;	
				}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *");
				System.out.println("\n\n-----------------------------------------------------------------------\n");
			}
		}
		while (userPw.length() != 6);

		return userPw;
	}

	// 비밀번호 확인
	void checkUserPw(String userPw)
	{
		count = 0;
		String checkPw = "";

		do
		{
			try
			{
				if (count == 0)
				{
					System.out.print("\n비밀번호 확인을 위해 다시 입력해주세요. : ");
					checkPw = sc.next();
				}
				else if (count < 3)
				{
					System.out.print("\n잘못 입력하셨습니다. 다시 입력해주세요. : ");
					checkPw = sc.next();
				}
				else
				{
					System.out.println("\n잘못 입력하셨습니다. 다시 입력해주세요.");
					System.out.print("비밀번호 입력으로 돌아가시려면 1을 입력하세요. : ");
					checkPw = sc.next();

					if (checkPw.equals("1"))
					{
						inputUserPw();
						count = -1;
					}
				}
				count++;	
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *");
				System.out.println("\n\n-----------------------------------------------------------------------\n");
			}

		}
		while (!checkPw.equals(userPw));
	}

	// 이름 입력받기
	String inputUserName()
	{
		System.out.print("\n사용하실 이름을 입력하세요. : ");
		String userName= sc.next();	

		return userName;
	}

	// 생년월일 6자리 입력받기
	String inputUserBirth()
	{
		count = 0;
		String userBirth = "";

		do
		{
			try
			{
				if (count == 0)
					System.out.print("\n생년월일(숫자 6자리)을 입력하세요. : ");
				else
					System.out.print("\n잘못 입력하셨습니다. 다시 입력해주세요. : ");
				userBirth = sc.next();
				count++;
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *");
				System.out.println("\n\n-----------------------------------------------------------------------\n");
			}
		}
		while (userBirth.length()>6 || userBirth.length()<6);

		return userBirth;
	}

	// 면허번호 입력받기
	String inputUserLicenseNum()
	{
		count = 0;
		String userLicenseNum = "";

		do
		{
			try
			{
				if (count == 0)
					System.out.print("\n면허번호(숫자 12자리, 하이픈 포함) 입력하세요. : ");
				else
					System.out.print("\n잘못 입력하셨습니다. 다시 입력해주세요. : ");
				userLicenseNum = sc.next();
				count++;
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *");
				System.out.println("\n\n-----------------------------------------------------------------------\n");
			}

		}
		while (userLicenseNum.length()>15 || userLicenseNum.length()<15);

		do
		{
			try
			{
				if (!cdl.checkNum(userLicenseNum))
				{
					System.out.print("\n유효하지 않은 번호입니다. 다시 입력해주세요. : ");
					userLicenseNum = sc.next();
				}
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *");
				System.out.println("\n\n-----------------------------------------------------------------------\n");
			}
		}
		while (!cdl.checkNum(userLicenseNum));
		
		return userLicenseNum;
	}

	// 기존 보유 데이터 내 번호와 사용자 입력 번호의 일치 여부
	String checkLicenseNum(String userLicenseNum)
	{
		try
		{
			if (!cdl.checkNum(userLicenseNum))
			{
				System.out.print("\n유효하지 않은 번호입니다. 다시 입력해주세요. : ");
				inputUserLicenseNum();
			}
		}
		catch (InputMismatchException e)
		{
			sc = new Scanner(System.in);
			System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *");
			System.out.println("\n\n-----------------------------------------------------------------------\n");
		}		
		return userLicenseNum;
	}

	// 입력 메소드들을 호출하고, 입력값들을 UserData에 저장하여 회원가입 전체 프로세스를 진행하는 메소드
	UserData join() 
	{
		// 유저에게 회원가입을 위한 입력값들을 받아오는 메소드 호출
		String userTel = inputUserTel();
		String userPw = inputUserPw();
		checkUserPw(userPw);
		String userName = inputUserName();
		String userBirth = inputUserBirth();
		String userLicenseNum = inputUserLicenseNum();
		
		// 입력받은 값을 바탕으로 User를 생성하고 이를 UserData에 put해줌 
		ud.putUserData(new User(userName, userTel, userPw, userLicenseNum, userBirth));
		u.setJoinTime(splitFormat.format(time));

		// UserData에 저장을 마치면 회원가입이 완료됨 
		System.out.print("\n\n-----------------------------------------------------------------------\n\n");
		System.out.println("\n* 회원가입이 완료되었습니다. *");
		System.out.println("* 시작 화면으로 이동합니다. *");

		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}
		
		System.out.print("\n\n-----------------------------------------------------------------------\n\n");

		// 메소드에서 return을 한다는 것은 메소드를 종료한다는 것.
		// 즉, 이 메소드를 호출했던 지점으로 돌아가게 된다. → startScreen() 
		return ud;
	}
	

	// 입력받은 값이 저장되어 있는 UserData를 반환해주는 메소드
	UserData getUserData()
	{
		return ud;
	}
}

/*
[실행 결과]
사용하실 아이디(휴대폰번호 11자리)를 입력하세요. : 12345678910

사용하실 비밀번호(숫자 6자리)를 입력하세요. : 121212

비밀번호 확인을 위해 다시 입력해주세요. : 121212

사용하실 이름을 입력하세요. : ddd

생년월일(숫자 6자리)을 입력하세요. : 121212

면허번호(숫자 12자리, 하이픈 포함) 입력하세요. : 12-12-121212-11

유효하지 않은 번호입니다. 다시 입력해주세요. : 11-17-893804-41

회원가입이 완료되었습니다.


시작 화면

[ 1. 로그인   ]
[ 2. 회원가입 ]

>> 입력:
*/