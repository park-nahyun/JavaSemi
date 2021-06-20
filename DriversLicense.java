import java.util.Scanner;
import java.util.Arrays;
import java.util.List;


class CheckDriversLicense
{
	String[] license = {"11-17-893804-41", "12-18-890791-42", "19-02-154347-31", "11-10-992930-62",
						"13-06-524250-01", "15-03-481467-50", "11-10-709337-60", "13-09-965490-81",
						"20-10-317315-42", "23-10-502429-31", "16-04-148258-83", "26-11-205454-83",
						"19-13-141395-41", "12-04-529445-20", "15-19-847916-43", "14-17-525269-31",
						"15-06-113999-53", "24-19-586570-63", "17-11-980325-31", "20-15-351569-52",
						"28-78-630204-52", "18-95-203876-31", "12-77-527351-22", "22-88-756643-00",
						"12-95-308149-93", "25-97-746509-03", "13-90-706098-21", "16-97-397435-80",
						"21-80-718364-03", "17-90-847908-22", "11-11-111111-11"};


	// 기존 보유 데이터 내 번호와 사용자 입력 번호의 일치 여부
	boolean checkNum(String userInputDl)
	{
		boolean checkednum;

		if (Arrays.asList(license).contains(userInputDl))
			checkednum = true;
		else
			checkednum = false;

		return checkednum;
	}

	// 회원가입 시 기존 회원 여부 확인
	String checkJoin(String userInputDl)
	{
		String replacedDlNum = userInputDl.replaceAll("-","");
		String[] splitDlNum = replacedDlNum.split("");
		String checkjoin = "";

		for (int i=0; i<splitDlNum.length-1; i++)
			checkjoin += splitDlNum[i];

		return checkjoin;
	}
}


public class DriversLicense
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		CheckDriversLicense cdl = new CheckDriversLicense();

		System.out.print("운전면허 번호 입력: ");
		String userInputDl = sc.next();

		boolean checknum = cdl.checkNum(userInputDl);
		String checkjoin = cdl.checkJoin(userInputDl);

		System.out.println(checknum+" "+checkjoin);
	}
}
