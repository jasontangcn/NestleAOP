一、参考实现<br/>
  HuiHoo的JoyAOP<br/>
  Spring的SpringAOP<br/>
  JBoss的JBossAOP<br/>
  
二、其它需要研究的技术<br/>
  CGLib<br/>
  
(2005/1/6)<br/>
目前，NestleAOP基于 Dynamic Proxy技术。<br/>
支持4种Advice：<br/>
1、BeforeAdvice<br/>
2、AfterReturnAdvice<br/>
3、AroundAdvice<br/>
4、ThrowsAdvice<br/>
并且支持introduction(Mixin)。<br/>
   
基于Dynamic Proxy技术，所以NestleAOP只支持interface的method的拦截。<br/>
而一些主流AOP框架支持的特性，譬如：<br/>
1、支持class 成员函数（member function）的拦截<br/>
2、支持class成员变量(member variable)的拦截等等。<br/>
我打算在下一个版本中利用CGLib来支持这些特性。<br/>
