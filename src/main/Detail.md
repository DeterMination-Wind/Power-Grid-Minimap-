# Detail: src\main

## 这一层在做什么
src/main 是主源码集层，定义会进入最终模组包的代码与资源。

## 这一层直接包含什么
### 直接子目录
- java
- resources

### 直接文件
- 本层没有直接文件，职责主要体现在子目录与其生成关系上。

## 这一层如何实现自己的职责
它拆成 java 和 resources 两条标准 JVM 路线，说明项目完全沿用 Gradle Java 插件的约定布局。

## 这一层与其他层级的关系
向上由 src 承接，向下分别投喂 compileJava 与 processResources。

## 阅读这一层时要注意什么
对 Mindustry Java 模组来说，标准布局能显著降低构建与 IDE 摩擦。
