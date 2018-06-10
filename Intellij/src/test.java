/**
 * @Author:Jsen
 * @Date:Created in 9:54 AM 03/06/2018
 * @Language: Java
 */
public class test {
    public boolean isPalindrome(int x) {
        if (x<0 || x%10==0) return false;
        int y=0;
        y+=x%10;
        while (x/10!=0)
        {
            x/=10;
            y*=10;
            y+=x%10;

        }
        System.out.println(y);

        if (y!=x)return false;
        return true;
    }
    public static void main(String[] args) {
        test sl=new test();
        System.out.println(sl.isPalindrome(121));
    }
}
