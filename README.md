# ll-ssm
Spring+SpringMVC+Mybatis，采用velocity模板引擎

前端：

采用比较主流的bootstrap为样式，同时整合三方的插件：  
表格：bootstrap-table  
下拉框：bootstrap-select  
复选框：bootstrap-checkbox  
菜单树：zTree  
表单校验：bootstrap-validate  
JSON：jquery.serialize-object.js
消息提示：toastr.js

velocity作为模板引擎，页面使用的也是velocity的布局功能

后端：  
采用主流的Spring+SpringMVC+Mybatis，Druid作为数据库连接池，Shiro为权限校验  
采用aop实现系统请求日志记录功能





注意（坑）：由于配置的是Spring和SpringMVC子父类容器关系，所以在开启aop注解配置的时候<aop:aspectj-autoproxy ></aop:aspectj-autoproxy>无效，  
是因为在主容器中并未扫描@Controller，而是值SpringMVC的容器扫描的，导致将开启aop注解的配置是配在了spring的主配置文件中，从而aop注解失效的情况，  
改为在SpringMVC的配置文件中开启即可。
