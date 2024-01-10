### java-security（java安全加密合集）

- [JAVA进制转换](/Java-Security/src/main/java/com/security/jinzhi/JinzhiZhuanHuan.java)

### [JAVA安全实现三种方式]

    1.JDK 

```java
package java.security;

public abstract class MessageDigest extends MessageDigestSpi {
}

```

    2.Commons Codec

```xml

<dependency>
    <groupId>commons-codec</groupId>
    <artifactId>commons-codec</artifactId>
    <version>${commons-codec.version}</version>
</dependency>
```

    3.Bouncy Castle

```xml

<dependency>
    <groupId>org.bouncycastle</groupId>
    <artifactId>bcpkix-jdk15on</artifactId>
    <version>${bcpkix-jdk15on.version}</version>
</dependency>
```

- [一、Base64](/Java-Security/src/main/java/com/security/base64/Base64Test.java)

  1.JDK实现 2.common codes实现 3.bouncy castle实现


- [二、消息摘要算法](/Java-Security/src/main/java/com/security/message_digest/MD5Test.java)

    - 1.MD5
      (MD5
      曾经是一种广泛使用的哈希算法，但现在已被认为是不安全的，因为它存在碰撞攻击，攻击者可以找到两个不同的输入产生相同的MD5摘要。)
    - 2.SHA
      (SHA-1\SHA-224\SHA-256) 推荐使用SHA256(SHA-1
      也因为存在碰撞攻击而被认为不再安全。在2017年，Google等研究机构成功演示了对SHA-1的碰撞攻击，进一步证实了其不安全性。)
    - 3.MAC
      (HMACMD5\HMACSHA1\HMACSHA256) 推荐使用HMACSHA256

- [三、对称加密算法](/Java-Security/src/main/java/com/security/symmetric_encryption/AESTest.java)
    - 1.DES<br>
      DES使用56位的密钥和64位的明文块进行加密。DES算法的分组大小是64位，因此，如果需要加密的明文长度不足64位，需要进行填充；如果明文长度超过64位，则需要使用分组模式进行分组加密。
      虽然DES算法的分组大小是64位，但是由于DES算法的密钥长度只有56位，因此DES算法存在着弱点，容易受到暴力破解和差分攻击等攻击手段的威胁。因此，在实际应用中，DES算法已经不再被广泛使用，而被更加安全的算法所取代，如AES算法等。
    - 2.3DES <br>
      3DES（Triple Data Encryption Standard）是一种对称加密算法，基于DES（Data Encryption
      Standard）算法的加强版本。它使用了对称密钥加密的方式，意味着加密和解密使用相同的密钥。
      当使用3DES进行加密时，数据首先经过密钥扩展生成三个密钥（K1、K2和K3）。明文数据通过“加密-解密-加密”（EDE）过程，分别用K1、K2和K3加密。解密时按相反顺序用K3、K2和K1解密，最终恢复原始明文。这个算法比标准DES更安全，因为它重复应用DES算法三次，提高了安全性
    - 3.AES <br>
    - 4.PBE

- [四、非对称加密算法](/Java-Security/src/main/java/com/security/asymmetric_encryption/RSAConcurrencyTest.java)

  - 1.DH 
  - 2.RSA 
  - 3.ElGamal大纲

- [五、数字签名:JDK实现](/Java-Security/src/main/java/com/security/signature/RSATest.java)

  - 1.RSA 
  - 2.DSA 
  - 3.ECDSA

- [六、PBKDF2 密码加密验证](/Java-Security/src/main/java/com/security/password_hash/PasswordHash.java)
  - 1.PBKDF2&SHA256 
  - 2.PBKDF2WithHmacSHA256

- [七、AES+RSA签名，加密 验签，解密（用户客户端服务端验证）](/src/main/java/com/security/interface_verification_design/Main.java)
  - 1.客户端密钥利用参数内容生成RSA签名、随机数生成AES密钥对参数进行加密、利用服务端公钥对AES密钥进行RSA加密
  - 2.RSA服务端私钥对AES密钥进行解密、利用AES密钥解开请求参数、服务端公钥RSA对请求参数进行验证签名