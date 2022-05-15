1、将项目目录变为git可管理目录
    执行git init
2、将文件添加到暂存区
    执行 git add xxx/
3、将文件添加到本地仓库
    执行 git commit -m '描述内容'
4、添加远程主机(remote adj. (计算机) 远程的；origin n.起源)
    执行 git remote add origin https://github.com/smallFeathers/springcloud-config-server.git
5、把本机的master分支推送到origin主机
    执行 git push -u origin master

操作做完可以启动配置中心通过/{application}/{profile}/{label}来访问我们的配置文件
其中：
{applicaiton} 表示配置文件的名字 对应的配置文件即 application
{profile}   表示环境，有dev、test、online及默认
{labe}  表示分支，默认我们放在master上
通过浏览器访问https://localhost:3721/application/dev/master

当访问成功后配置中心会通过git clone命令将远程配置文件在本地保存一份。确保git远程仓库故障后，我们应用程序继续访问