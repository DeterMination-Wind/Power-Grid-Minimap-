# Detail: gradle

## 这一层在做什么
gradle 目录是 Gradle Wrapper 配套文件层，用来保证不同机器能拉起一致的构建环境。

## 这一层直接包含什么
### 直接子目录
- wrapper

### 直接文件
- 本层没有直接文件，职责主要体现在子目录与其生成关系上。

## 这一层如何实现自己的职责
当前只有 wrapper 子目录，说明仓库采用标准 wrapper 布局，没有额外自定义引导脚本。

## 这一层与其他层级的关系
它向上被 gradlew 和 gradlew.bat 引用，向下由 wrapper jar 和属性文件构成。

## 阅读这一层时要注意什么
这里定义“该用什么 Gradle”，而 .gradle 则记录“这个 Gradle 在本机干过什么”。
