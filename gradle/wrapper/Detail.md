# Detail: gradle\wrapper

## 这一层在做什么
这一层是 Gradle Wrapper 的实物层，存放启动器 jar 与目标发行版配置。

## 这一层直接包含什么
### 直接子目录
- 本层没有直接子目录。

### 直接文件
- gradle-wrapper.jar（42.74 KB）
- gradle-wrapper.properties（253 B）

## 这一层如何实现自己的职责
gradle-wrapper.properties 当前指向 gradle-8.14.3-bin.zip，这说明官方推荐基线是 8.14.3。gradle-wrapper.jar 则负责在目标机器上拉起对应版本。

## 这一层与其他层级的关系
它连接根目录启动脚本和远端 Gradle 发行包，与 build.gradle 形成“版本保证 + 逻辑描述”的互补关系。

## 阅读这一层时要注意什么
排查不同机器构建不一致时，这层通常比源码更值得先看。
