package commons;

public class Generators {
    public static String generateInstanceId(){
        StringBuilder res = new StringBuilder(Constants.INSTANCE_ID_LENGTH);
        for(int i = 0 ; i < Constants.INSTANCE_ID_LENGTH ; i++){
            if(Math.random() < 0.7)
                res.append((char)((int)(Math.random() * 26) + 97));
            else
                res.append((char)((int)(Math.random() * 10) + 48));
        }
        return res.toString();
    }
}
