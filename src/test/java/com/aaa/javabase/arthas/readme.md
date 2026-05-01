## 【步骤1：编译 RetransformAgent】

cd F:\WorkSpace\MyGithub\java-base\src\test\java
& "C:\Program Files\Java\jdk1.8.0_251\bin\javac.exe" -encoding UTF-8 F:\WorkSpace\MyGithub\java-base\src\test\java\com\aaa\javabase\arthas\RetransformAgent.java

## 【步骤2：打包 agent.jar】

cd F:\WorkSpace\MyGithub\java-base\src\test\java
jar cvfm F:\WorkSpace\MyGithub\java-base\src\test\java\com\aaa\javabase\arthas\agent.jar com/aaa/javabase/arthas/MANIFEST.MF com/aaa/javabase/arthas/RetransformAgent.class 'com/aaa/javabase/arthas/RetransformAgent$MyTransformer.class'

## 【步骤3：编译 AttachMain】

& "C:\Program Files\Java\jdk1.8.0_251\bin\javac.exe" -encoding UTF-8 -cp "C:\Program Files\Java\jdk1.8.0_251\lib\tools.jar" F:\WorkSpace\MyGithub\java-base\src\test\java\com\aaa\javabase\arthas\AttachMain.java

## 【步骤4：确保目标类已加载】

先访问一次目标方法，让类加载到JVM：
http://localhost:8080/httpController/arthasTestServiceSayHello

## 【步骤5：更新待修改的字节码】
& "C:\Program Files\Java\jdk1.8.0_251\bin\javac.exe" -encoding UTF-8 -d "F:\DevlopSoftware\arthas\tempData" "F:\WorkSpace\MyGithub\java-base\src\main\java\com\aaa\javabase\service\ArthasTestService.java"

## 【步骤5：Attach 到目标进程】
注意：类名需要使用完整路径 com.aaa.javabase.arthas.AttachMain

cd F:\WorkSpace\MyGithub\java-base\src\test\java; & "C:\Program Files\Java\jdk1.8.0_251\bin\java.exe" -cp ".;C:\Program Files\Java\jdk1.8.0_251\lib\tools.jar" com.aaa.javabase.arthas.AttachMain 19984 "F:\WorkSpace\MyGithub\java-base\src\test\java\com\aaa\javabase\arthas\agent.jar" "F:\DevlopSoftware\arthas\tempData\com\aaa\javabase\service\ArthasTestService.class"




