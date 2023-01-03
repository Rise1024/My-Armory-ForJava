package com.security.password_hash;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zds
 * @Description 随机密码生成器自定义字符
 * @createTime 2022/2/23 15:14
 */
public class RandomPassword {

    public static String getRandomPassword(int passwordLength){
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        List<CharacterRule> ruleList = new ArrayList<>();
        ruleList.add(new CharacterRule(EnglishCharacterData.Alphabetical));
        ruleList.add(new CharacterRule(EnglishCharacterData.Digit));
        CharacterRule specialCharacterRule = new CharacterRule(new CharacterData() {
            @Override
            public String getErrorCode() {
                return "SAMPLE_ERROR_CODE";
            }

            @Override
            public String getCharacters() {
                return "`~!@#$%^&*()-_=+\\|[{}]:;'\",<.>/? ";
            }
        });
        ruleList.add(specialCharacterRule);
        return passwordGenerator.generatePassword(passwordLength,ruleList);
    }
}
