基于Jenkins，docker实现自动化部署
![img.png](img.png)
1、开发人员在gitLab上打了一个tag
2、gitLab把tag事件推送到Jenkins
3、Jenkins 获取tag源码，编译，打包，构建镜像
4、Jenkins push 镜像到阿里云仓库
5、Jenkins 执行远程脚本
5-1. 远程服务器 pull 指定镜像
5-2. 停止老版本容器，启动新版本容器
通知测试人员部署结果

maven 构建镜像配置
pom.xml 文件添加docker插件
```
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>0.4.11</version>
    <configuration>
        <imageName>${docker.image.prefix}/${project.artifactId}</imageName>
        <imageTags>
            <imageTag>${project.version}</imageTag>
            <imageTag>latest</imageTag>
        </imageTags>
        <dockerDirectory>src/main/docker</dockerDirectory>
        <resources>
            <resource>
                <targetPath>/</targetPath>
                <directory>${project.build.directory}</directory>
                <include>${project.build.finalName}.jar</include>
            </resource>
        </resources>
    </configuration>
</plugin>
```
${docker.image.prefix} 是镜像的前缀
${project.artifactId} 是镜像名字
${project.version} 版本号，此处也用来当做镜像的版本号