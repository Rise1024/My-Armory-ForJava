package com.base.extendstest.functiona;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface Functional {
  String goodbye(String arg);
}

interface FunctionalNoAnn {
  String goodbye(String arg);
}

/*
@FunctionalInterface
interface NotFunctional {
  String goodbye(String arg);
  String hello(String arg);
}
产生错误信息:
NotFunctional is not a functional interface
multiple non-overriding abstract methods
found in interface NotFunctional
*/

public class FunctionalAnnotation {
  public String goodbye(String arg) {
    return "Goodbye, " + arg;
  }
  public static void main(String[] args) {
    FunctionalAnnotation fa =
      new FunctionalAnnotation();
    Functional f = fa::goodbye;
    FunctionalNoAnn fna = fa::goodbye;
    // Functional fac = fa; // Incompatible
    Functional fl = a -> "Goodbye, " + a;
    FunctionalNoAnn fnal = a -> "Goodbye, " + a;
    fl.goodbye("test");
    fnal.goodbye("test no");
    List<String> strList = Arrays.asList("YangHang", "AnXiaoHei", "LiuPengFei");

    strList.forEach(x -> {
      System.out.println(x);
    });
    strList.forEach(System.out::println);
  }
}