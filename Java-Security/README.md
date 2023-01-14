### java-security（java安全加密合集）

- [JAVA进制转换](/Java-Security/src/main/java/com/security/jinzhi/JinzhiZhuanHuan.java)

#### [JAVA安全实现三种方式]

    1.JDK 
    2.Commons Codec 
    3.Bouncy Castle

- [一、非对称加密算法](/Java-Security/src/main/java/com/security/asymmetric_encryption/RSAConcurrencyTest.java)

    1.DH 2.RSA 3.ElGamal大纲

- [二、Base64](/Java-Security/src/main/java/com/security/base64/Base64Test.java)

    1.JDK实现 2.common codes实现 3.bouncy castle实现

- [三、消息摘要算法](/Java-Security/src/main/java/com/security/message_digest/MD5Test.java)

    1.MD5 2.SHA 3.MAC

- [四、数字签名:JDK实现](/Java-Security/src/main/java/com/security/signature/RSATest.java)

    1.RSA 2.DSA 3.ECDSA

- [五、对称加密算法](/Java-Security/src/main/java/com/security/symmetric_encryption/AESTest.java)

    1.3DES 2.AES 3.PBE
- [六、PBKDF2 密码加密验证](/Java-Security/src/main/java/com/security/password_hash/PasswordHash.java)
    1.PBKDF2&SHA256 2.PBKDF2WithHmacSHA256

- [七、AES+RSA签名，加密 验签，解密（用户客户端服务端验证）](/src/main/java/com/security/interface_verification_design/Main.java)
   1.客户端密钥利用参数内容生成RSA签名、随机数生成AES密钥对参数进行加密、利用服务端公钥对AES密钥进行RSA加密
   2.RSA服务端私钥对AES密钥进行解密、利用AES密钥解开请求参数、服务端公钥RSA对请求参数进行验证签名