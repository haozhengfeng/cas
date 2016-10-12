# cas
天远-单点登录demo

cas：单点登录服务器端
cas-client：cas2协议demo客户端
cas-client4:saml协议demo客户端

服务端创建证书:
keytool -genkey -alias tianyuan  -keyalg RSA -keystore D:\
j2ee\apache-tomcat-7.0.55\key\.keystore

在服务端tomcat服务器上应用证书
<!-- 务必注意大小写 -->
<connector port="8443" protocol="HTTP/1.1" sslenabled="true"
  maxthreads="150" scheme="https" secure="true" 
  clientauth="false" sslprotocol="TLS" 
  keystoreFile="/key/.keystore" 
  keystorePass="123456">
</connector>

服务端导出证书
C:\Users\haozhengfeng>keytool -export -file D:\j2ee\apache-tomcat-7.0.55\key\tia
nyuan.crt -alias tianyuan -keystore D:\j2ee\apache-tomcat-7.0.55\key\.keystore

生成的tianyuan.crt就可以分发给客户端的jdk使用了.

客户端导入证书
keytool -import -keystore E:/Java/jdk1.7.0_21/jre/lib/security/cacerts -file D:\j2ee\apache-tomcat-7.0.55\key\tianyuan.crt  -alias tianyuan1
