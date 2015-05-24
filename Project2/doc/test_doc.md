# Project2测试文档

##content
* TeamQueryImplTest.java
* MainTest.java

## 代码功能
* TeamQueryImplTest.java:

 用Junit测试project2的server是否正常运行。 
 
* MainTest.java:

 用Junit测试main函数是否正常。

## 具体实现
+ TeamQueryImplTest.java:

 public void testGetTeamMember()类，用于测试getTeamMember()类是否能够正常运行。

 输入查询的team号之后，获取相应组中成员的list，并用assert判定结果是否正确。

+ MainTest.java:

 public void testMain() 函数是负责测试main()函数的。

 注入team number = 2， 用ArrayList接收结果。

 用Junit的assertTrue（）验证结果是否正确。
