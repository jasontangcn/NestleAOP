一、参考实现
  HuiHoo的JoyAOP
  Spring的SpringAOP
  JBoss的JBossAOP
  
二、其它需要研究的技术
  CGLib
  
(2005/1/6)
目前，NestleAOP基于 Dynamic Proxy技术。
支持4种Advice：
1、BeforeAdvice
2、AfterReturnAdvice
3、AroundAdvice
4、ThrowsAdvice)
并且支持introduction(Mixin)。
   
由于基于Dynamic Proxy技术，所以，NestleAOP只支持在interface中签署的method的拦截。
而一些主流AOP框架支持的特性，譬如：
1、支持class 成员函数（member function）的拦截
2、支持class成员变量(member variable)的拦截等等。
我打算在下一个版本中利用CGLib来支持这些特性。