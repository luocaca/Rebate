package me.luocaca.rebate;


import android.util.Base64;


/**
 *
 */
public class TestGson联通模拟 {


    private static final String TAG = "联通模拟";


    String desKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB";

    public static void main(String... args) throws Exception {


//        String reslu = new String(Base64.decode("4\u000EА\u0012);

//        String reslu = AesUtil.aesDecrypt("ZT8Lv2kcM6k8tfnwy0OXZSOAO8O96HoIt44QTLcctfXtgbUezJzEi2e6Ne6agOs+7FNlEZ3ywejPAjfgO3d6RDRq1GjeU/VVXlg67V52MrCe7JUQXa1RTmWAnu8RmP9jPQ6kD/RsZYrXuFKO5np1V6ybFHn/hXzGvXVOiBW12aY=");

//        syso(reslu);


        syso(new String(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)));


    }


    public static void syso(String string) {

        System.out.println(string);
    }

}
