import java.util.Map;
import java.util.HashMap;
import java.util.Vector;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;


class Login extends User
{
	UserData userdata;
	InputDate id = new InputDate();
	
	Login()
	{
	}
	
	Login(UserData readData)
	{
		this.userdata = readData;
	}
	

	String userInputId = "";         // 사용자 입력 아이디
	String userInputPw = "";         // 사용자 입력 비밀번호

	int loginCount = getLoginCount();
	String loginTime = getLoginTime();
	boolean userStatus = getUserStatus();

	Scanner sc = new Scanner(System.in);
	SimpleDateFormat splitFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");   // 나중에 "-"로  split해서 쓸 것
	Date time = new Date();

	Map<String, String> loginMap = new HashMap<String, String>();   // 아이디, 비밀번호 저장용 map
	Map<String, String> lockMap = new HashMap<String, String>();    // 아이디, 비밀번호 잠금용 map
	Map<String, String> collectMap = new HashMap<String, String>();

	
	String login()
	{
		String result = "";
		boolean success = false;
		
		System.out.print("\n\n-----------------------------------------------------------------------\n\n");
		System.out.print("\n【 로그인 】\n\n");

		do
		{
			try
			{
				System.out.print("휴대폰 번호: ");
				userInputId = sc.next();

				if (userInputId.length() != 11)
					System.out.println("다시 입력해주십시오.");
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *");
				System.out.println("\n\n-----------------------------------------------------------------------\n");
			}
		}
		while (userInputId.length() != 11);

		// 입력한 아이디가 readData의 key값에 존재하는지 확인
		if (tryLogin(userInputId) != null)
		{
			try
			{
				System.out.print("비밀번호: ");
				userInputPw = sc.next();	
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\n* 잘못 입력하셨습니다. 다시 입력해주세요. *");
				System.out.println("\n\n-----------------------------------------------------------------------\n");
			}
			result = userInputId;

			// userInputId가 readData에 저장된 key값일 경우
			// 이에 해당하는 value값이 userInputPw와 일치한다면
			if (tryLogin(userInputId).equals(userInputPw))
			{
				userStatus = true;
				setUserStatus(userStatus);
				setLoginTime(splitFormat.format(time));
				userCoupon();
				success = true;
			}
			else	// id는 존재하는데, 비밀번호가 틀린경우
			{
				userStatus = false;
				setUserStatus(userStatus);
				System.out.println("\n* 잘못된 비밀번호 입니다. *");
				login();
			}
		}
		else if (tryLogin(userInputId) == null)	// 존재하지 않는 id일 경우
		{
			System.out.println("\n* 등록되지 않은 아이디입니다. 초기 화면으로 돌아갑니다. *");
			System.out.print("\n\n-----------------------------------------------------------------------\n\n");
			result = "";
		}
		else	// 잘못 입력했을 경우
		{
			System.out.println("\n* 잘못된 아이디입니다. *");
			login();
		}

		return result;
	}

	String tryLogin(String userInputId)
	{
		for (int i=0; i<userdata.getUser().size(); i++)
		{
			collectMap.put(userdata.getUser().get(i).getHp(), userdata.getUser().get(i).getPw());
		}

		return collectMap.get(userInputId);
	}

	// 로그인 시도 횟수 세기
	void countLogin()
	{
		setLoginCount(loginCount++);

		// 로그인 횟수 5회 이하는 로그인 재시도, 초과시 계정 잠금
		if (loginCount < 5)
			return;
		else if (loginCount >= 5)
		{
			lockMap.put(userInputId,userInputPw);
			loginMap.put(userInputId,null);
			lockedAccount();
		}
	}

	// 정지된 계정 안내
	void lockedAccount()
	{
		System.out.print("\n\n-----------------------------------------------------------------------\n\n");
		System.out.println("\n* 로그인 입력 횟수 초과로 계정이 정지된 상태입니다. *");
		System.out.println("* 오프라인 창구를 방문해주세요. *");

		loginCount = 0;
		setLoginCount(loginCount);

		System.out.print("\n\n-----------------------------------------------------------------------\n\n");
		System.out.print("\n* 초기 화면으로 돌아갑니다. *\n");
		System.out.print("\n\n--------------------------------------------------------------------\n\n");

		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			System.out.println(e.toString());
		}

		return;
	}
	
	// 쿠폰 추가
	void userCoupon()
	{
		String loginTime = getLoginTime();
		String joinTime = getJoinTime();

		String[] loginTimeSplit = loginTime.split("-");
		String[] joinTimeSplit = joinTime.split("-");
		String[] birthSplit = getBirth().split("");

		String birthMonth = birthSplit[2]+birthSplit[3];

		int checkJoinTime = id.compareDate(Integer.parseInt(joinTimeSplit[0]),Integer.parseInt(joinTimeSplit[1]),Integer.parseInt(joinTimeSplit[2]));
		int checkNowTime = id.compareDate(Integer.parseInt(loginTimeSplit[0]),Integer.parseInt(loginTimeSplit[1]),Integer.parseInt(loginTimeSplit[2]));

		if (loginTimeSplit[1].equals(birthMonth))
			setCoupon("1::: 생일 축하 쿠폰 (10% 할인)","10");

		if (loginTimeSplit[0].equals("2021"))
			setCoupon("2::: 개업 7주년 기념 쿠폰 (7% 할인)","7");

		if ((checkNowTime-checkJoinTime)>0 || ((checkNowTime-checkJoinTime)<7))
			setCoupon("3::: 신규 가입 쿠폰 (5% 할인)","5");
	}

	void testPrint() throws Exception
	{
		userdata.printUser();
	}
}