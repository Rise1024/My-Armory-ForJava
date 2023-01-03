package com.security.interface_verification_design;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.TreeMap;

/**
 * Description: AES+RSA签名，加密 验签，解密
 *
 * @author: zds
 * @date: 2015/8/13 15:12
 */
public class Main {
//    public static final String clientPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKbNojYr8KlqKD/y" +
//            "COd7QXu3e4TsrHd4sz3XgDYWEZZgYqIjVDcpcnlztwomgjMj9xSxdpyCc85GOGa0" +
//            "lva1fNZpG6KXYS1xuFa9G7FRbaACoCL31TRv8t4TNkfQhQ7e2S7ZktqyUePWYLlz" +
//            "u8hx5jXdriErRIx1jWK1q1NeEd3NAgMBAAECgYAws7Ob+4JeBLfRy9pbs/ovpCf1" +
//            "bKEClQRIlyZBJHpoHKZPzt7k6D4bRfT4irvTMLoQmawXEGO9o3UOT8YQLHdRLitW" +
//            "1CYKLy8k8ycyNpB/1L2vP+kHDzmM6Pr0IvkFgnbIFQmXeS5NBV+xOdlAYzuPFkCy" +
//            "fUSOKdmt3F/Pbf9EhQJBANrF5Uaxmk7qGXfRV7tCT+f27eAWtYi2h/gJenLrmtke" +
//            "Hg7SkgDiYHErJDns85va4cnhaAzAI1eSIHVaXh3JGXcCQQDDL9ns78LNDr/QuHN9" +
//            "pmeDdlQfikeDKzW8dMcUIqGVX4WQJMptviZuf3cMvgm9+hDTVLvSePdTlA9YSCF4" +
//            "VNPbAkEAvbe54XlpCKBIX7iiLRkPdGiV1qu614j7FqUZlAkvKrPMeywuQygNXHZ+" +
//            "HuGWTIUfItQfSFdjDrEBBuPMFGZtdwJAV5N3xyyIjfMJM4AfKYhpN333HrOvhHX1" +
//            "xVnsHOew8lGKnvMy9Gx11+xPISN/QYMa24dQQo5OAm0TOXwbsF73MwJAHzqaKZPs" +
//            "EN08JunWDOKs3ZS+92maJIm1YGdYf5ipB8/Bm3wElnJsCiAeRqYKmPpAMlCZ5x+Z" +
//            "AsuC1sjcp2r7xw==";
//
//    public static final String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmzaI2K/Cpaig/8gjne0F7t3uE" +
//            "7Kx3eLM914A2FhGWYGKiI1Q3KXJ5c7cKJoIzI/cUsXacgnPORjhmtJb2tXzWaRui" +
//            "l2EtcbhWvRuxUW2gAqAi99U0b/LeEzZH0IUO3tku2ZLaslHj1mC5c7vIceY13a4h" +
//            "K0SMdY1itatTXhHdzQIDAQAB";


    public static final String clientPrivateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJusHm9yb5URNo19" +
            "6rPFbwLkcJ+gpQww9wnonCX+GEjnoW751S/xxdv9s3KXcCjMy4NbRDIHw8vSykgV" +
            "pFObtFcxXXYMHP6F7R9z8Ftz9dLNjalu604tIEjgcJqxjE8GOIa4s+ht1UG6u61K" +
            "MU0Axa1knpmSxcqz6LCkGnyWWhi7AgMBAAECgYEAj/As0BV6sNWSoG999S0nwP/i" +
            "Vuad1VDtCt9migaRA9AQx2D/yu39abUCGIGH9mGuB037F1twoeTMnqxMHfaJXuhx" +
            "mG13e4VVD+d20jM/umee/Ifnb4hZcZqa+qZFUpt98E7rO0WNa+o25D8+z/6PhNW9" +
            "+2Re+pxbVW2Xbx7NZ9kCQQDNz2+QMPVrEiAYDsdVtBXm55yLBB7/XhK1uUqulNOP" +
            "WEK0DPuwCGUx9psGrJJm+UUZtcnPGc+Lzkv/+bvcgqb/AkEAwaKczOiYxdHSEqcO" +
            "Ygl+G9ocHtl4I5gia7SQwdpD5x0ePft0Ye/aI/Mlv0ZKxNujxjopF7pMZi0EJp5z" +
            "E4LqRQJAbmvvNXOvGOU+pum29TqLzblSehuZ7fUxIKLreMOMxonaL/QPTaETej0P" +
            "bANHAUyYUHUCc8UrX50tlYcNzf+jTQJAX5cIMzvsF1QikaS4u/sVr2g4E56l6WR0" +
            "MZl9EMXQWaqNQGn/JU4D8VVaYBeyV4ZaTkRt/tGwX4x/jRURmCM7xQJAEv8yh8ur" +
            "h7wRl1CM6R0eMT01XNJhKKi8SqwWvp+0U40ygsSv4IOwfVBxQ0/vk8jR/kP5JDVK" +
            "W0f19UNAbwlAGA==";
    public static final String clientPublicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbrB5vcm+VETaNfeqzxW8C5HCf" +
            "oKUMMPcJ6Jwl/hhI56Fu+dUv8cXb/bNyl3AozMuDW0QyB8PL0spIFaRTm7RXMV12" +
            "DBz+he0fc/Bbc/XSzY2pbutOLSBI4HCasYxPBjiGuLPobdVBurutSjFNAMWtZJ6Z" +
            "ksXKs+iwpBp8lloYuwIDAQAB";

    public static final String serverPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALIZ98KqgLW8IMt4" +
            "G+N+4d3DiOiEa+5s6lCMSGE/NbU9stJEqw0EuCP54MY6JkT0HCYTCrLXqww6rSQy" +
            "WF7BNCVGssk2XDcvSKiCz1ZMgabd6XVK5kvIycySydXQ0Ky6rnfxw8w2mllHABFv" +
            "s1eamaHQozv18n/XGqemjW2BFy/jAgMBAAECgYAxT3FCi3SBXKnzy7hk/z9H6Bhi" +
            "0C8V3z/stzpe+mJDYOa+wtZdD15wT4HFQFpSIwgcHo+Kvp2UEDbZ27qN2Y43AZbF" +
            "9LOalWTRUzYtr8wL8MIbgtew/QQ9YFNWdkTZ6MxCItjD/mSz3Lrkcphvbsx4VoCV" +
            "YIJ04r+Loi0t9g0guQJBANvkpfrq0bLVRYWfaigjkx47mr0trJkB7mjADe69Iqts" +
            "M/2x5dHPpClDK78yzAWxU2BrYzOd31QIOm32iMIvRxUCQQDPWJPMOzcq8Jqs1PAM" +
            "7D0hxnvF3tSJB0CJCQWdGFkJiuIYSbrWnCVF78jJyU2AK1H3RDi9BzGPL2Z3i2Si" +
            "+9kXAkAPnKtAJl3fEY9PDmNuGCCA3AB/f/eqIV345/HVSm5kt1j1oSTNAa4JE/DO" +
            "MWAU42MlDFrNtl69y5vCZOeOyeaFAkBOJieGmWcAozDZJWTYqg2cdk/eU08t2nLj" +
            "c2gPPscIRrVSzC9EhhOyWV8HVv0D6s/471inPlfajNYFBp/Goj+/AkEAiejHX/58" +
            "Vv8+ccW22RMZmyxiHcZpTw9hz7vHUCWv03+fyVGtGMhJ4xuPt8UaZm91yHSPWWar" +
            "M8Xa7errKaXN9A==";
    public static final String serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyGffCqoC1vCDLeBvjfuHdw4jo" +
            "hGvubOpQjEhhPzW1PbLSRKsNBLgj+eDGOiZE9BwmEwqy16sMOq0kMlhewTQlRrLJ" +
            "Nlw3L0iogs9WTIGm3el1SuZLyMnMksnV0NCsuq538cPMNppZRwARb7NXmpmh0KM7" +
            "9fJ/1xqnpo1tgRcv4wIDAQAB";

    public static void main(String[] args) throws Exception {
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        params.put("userid", "152255855");
        params.put("phone", "18965621420");

//        String url="/api/platform/tenants/1003/accessToken";
//NpHmut7HIc6B4p06Yt9ApB5Fa/YNbgWrKRY2DiPp02mM75jk04zQWr+9bITwSRKMp3snTxgaLEnQxPrt+/qXGX8/kVY9jUkyC7B8kQA+Xsz7aCuGR1SbHysvrzHtlnZjr3llk6ptpQ49ZLqguOttp2GT+xlBa352P5tgvnL6FxQ=
       client(params);

        server();
    }

//    public static String client1(String content) throws Exception {
//        // 生成RSA签名
//        String sign = handleRSA1(content, clientPrivateKey);
//
//        System.out.println("生成的签名："+sign);
//        System.out.println("生成的签名："+Base64.encode(sign).length());
//        return sign;
//    }
//
//    public static String handleRSA1(String url,
//                                   String privateKey) {
//
//        String sign = "";
//        if (StringUtils.isNotEmpty(privateKey)) {
//            sign = RSA.sign(url, privateKey);
//        }
//        return sign;
//    }
//
//    public static void server1(String content,String sign) throws Exception {
//        String decode = Base64.decode(sign);
//        // 验签
//        boolean passSign = checkDecryptAndSign1(content, sign, clientPublicKey);
//
//        if (passSign) {
//            System.out.println("验证签名:" +passSign);
//
//        } else {
//            System.out.println("验签失败");
//        }
//    }
//    public static boolean checkDecryptAndSign1(String signData, String sign,
//                                              String clientPublicKey) {
//
//        /** 5. result为true时表明验签通过 */
//        boolean result = RSA.checkSign(signData.toString(), sign,
//                clientPublicKey);
//
//        return result;
//    }

    /**
     * RSA 密钥签名、公钥验签|公钥加密、私钥解密
     * @param params
     * @throws Exception
     */
//客户端密钥利用参数内容生成RSA签名、随机数生成AES密钥对参数进行加密、利用服务端公钥对AES密钥进行RSA加密
    public static void client(TreeMap<String, Object> params) throws Exception {
        // 生成RSA签名
        String sign = EncryUtil.handleRSA(params, clientPrivateKey);
        params.put("sign", sign);

        String info = JSON.toJSONString(params);
        //随机生成AES密钥
        String aesKey = SecureRandomUtil.getRandom(16);
        //AES加密数据
        String data = AES.encryptToBase64(ConvertUtils.stringToHexString(info), aesKey);

        // 使用RSA算法将商户自己随机生成的AESkey加密
        String encryptkey = RSA.encrypt(aesKey, serverPublicKey);

        Req.data = data;
        Req.encryptkey = encryptkey;

        System.out.println("加密后的请求数据:\n" + new Req().toString());
    }
//RSA服务端私钥对AES密钥进行解密、利用AES密钥解开请求参数、服务端公钥RSA对请求参数进行验证签名
    public static void server() throws Exception {

        // 验签
        boolean passSign = EncryUtil.checkDecryptAndSign(Req.data,
                Req.encryptkey, clientPublicKey, serverPrivateKey);

        if (passSign) {
            // 验签通过
            String aeskey = RSA.decrypt(Req.encryptkey,
                    serverPrivateKey);
            String data = ConvertUtils.hexStringToString(AES.decryptFromBase64(Req.data,
                    aeskey));

            JSONObject jsonObj = JSONObject.parseObject(data);
            String userid = jsonObj.getString("userid");
            String phone = jsonObj.getString("phone");

            System.out.println("解密后的明文:userid:" + userid + " phone:" + phone);

        } else {
            System.out.println("验签失败");
        }
    }

    static class Req {
        public static String data;
        public static String encryptkey;

        @Override
        public String toString() {
            return "data:" + data + "\nencryptkey:" + encryptkey;
        }
    }
}
