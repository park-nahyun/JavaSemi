import java.util.Hashtable;
import java.util.Vector;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.io.Serializable;
import java.util.ArrayList;


class UserData implements Serializable
{
	private boolean exist;					// UserData에 특정 유저의 존재 유무

	private String appDir;
	private File userList;
	private Hashtable ht;
	private ArrayList al;

	static private Hashtable<String, String> hUser;
	static private Vector<User> vUser;
	static private ArrayList<String> aCoupon;

	private User loginUser;

	{
		hUser = new Hashtable<String, String>();
		vUser = new Vector<User>();
		aCoupon = new ArrayList<String>();
		exist = false;

		loginUser = new User();
	}


	// 새 유저 생성하는 곳
	public void putUserData(User user)
	{
		hUser.put(user.getHp(), user.getLicenseNum());
		vUser.add(user);
	}

	public static Vector<User> getUser()
	{
		return vUser; 		
	}

	public static ArrayList<String> getCoupon()
	{
		return aCoupon;
	}

	public static void printUser()
	{
		Iterator it = vUser.iterator();
		User u1;
		while(it.hasNext())
		{
			u1 = (User)it.next();
			System.out.println(u1);
		}
	}

	// 유저 검색
	public boolean isMember(String id, String licenseNum)
	{
		// 매개변수로 받은 id(휴대폰 번호)와 면허번호가 hashtable 내에 존재하면 이미 존재하는 회원
		if(hUser.containsKey(id) && hUser.get(id) == licenseNum)
			exist = true;
		else
			exist = false;

		return exist;
	}

	public User getLoginUserInfo(String loginUserId)
	{
		for (User u : vUser)
		{
			if (u.getHp().equals(loginUserId))
			{
				loginUser = u;
			}
		}

		return loginUser;
	}
}