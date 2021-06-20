import java.util.Vector;
import java.util.HashMap;


class User
{
	private static String name;          // 이름
	private static String hp;            // 휴대폰 번호
	private static String pw;            // 비밀번호
	private static String licenseNum;    // 운전면허 번호
	private static String birth;         // 생년월일
  
	private static String loginTime;     // 로그인 시각
	private static String joinTime;      // 회원가입 시각

	private static int loginCount;       // 로그인 시도 횟수
	private static boolean userStatus;   // 로그인 여부

	private static String couponName;	 // 쿠폰 이름
	private static String couponPer;	 // 쿠폰 할인율
	private static String couponLimit;	 // 쿠폰 기한

	private static HashMap<String, String> couponMap = new HashMap<String, String>();

	User()
	{
	}

	User(String name, String hp, String pw, String licenseNum, String birth)
	{
		this.name = name;
		this.hp = hp;
		this.pw = pw;
		this.licenseNum = licenseNum;
		this.birth = birth;
	}
	
	User(String name, String hp, String pw, String licenseNum, String birth, String loginTime, String joinTime, int loginCount, boolean userStatus)
	{
		this.name = name;
		this.hp = hp;
		this.pw = pw;
		this.licenseNum = licenseNum;
		this.birth = birth;
		this.loginTime = loginTime;
		this.joinTime = joinTime;
		this.loginCount = loginCount;
		this.userStatus = userStatus; 
	}
  
	// getter 생성
	public String getName()
	{
		return name;
	}	

	public String getHp()
	{
		return hp;
	}

	public String getPw()
	{
		return pw;
	}

	public String getLicenseNum()
	{
		return licenseNum;
	}

	public String getBirth()
	{
		return birth;
	}

	public int getLoginCount()
	{
		return loginCount;
	}

	public boolean getUserStatus()
	{
		return userStatus;
	}

	public String getLoginTime()
	{
		return loginTime;
	}

	public String getJoinTime()
	{
		return joinTime;
	}

	public HashMap<String, String> getCoupon()
	{
		return couponMap;
	}

	// setter 생성
	public void setName(String name)
	{
		this.name = name;
	}

	public void setHp(String hp)
	{
		this.hp = hp;
	}

	public void setPW(String pw)
	{
		this.pw = pw;
	}

	public void setLicenseNum(String licenseNum)
	{
		this.licenseNum = licenseNum;
	}

	public void setBirth(String birth)
	{
		this.birth = birth;
	}

	public void setLoginCount(int loginCount)
	{
		this.loginCount = loginCount;
	}

	public void setUserStatus(boolean userStatus)
	{
		this.userStatus = userStatus;
	}

	public void setLoginTime(String loginTime)
	{
		this.loginTime = loginTime;
	}

	public void setJoinTime(String joinTime)
	{
		this.joinTime = joinTime;
	}

	public void setCoupon(String couponName, String couponPer)
	{
		couponMap.put(couponName, couponPer);
	}
	
	@Override
	public String toString()
	{
		return String.format("Name : %s, Phone : %s, LicenseNum : %s", name, hp, licenseNum);
	}
}