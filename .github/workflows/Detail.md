# Detail: .github\workflows

## 这一层在做什么
.github/workflows 是 GitHub Actions 工作流集合层，用来定义触发条件、执行环境与发布产物。

## 这一层直接包含什么
### 直接子目录
- 本层没有直接子目录。

### 直接文件
- release.yml（1.17 KB）

## 这一层如何实现自己的职责
当前唯一的 release.yml 支持手动触发和 v* tag 触发，运行在 ubuntu-latest，安装 Java 17、Gradle、Android SDK 与 build-tools，再执行 clean deploy。随后它把 dist 目录里的 jar 和 zip 上传到 GitHub Release。这和本地 build.gradle 中的 deploy 任务完全联动。

## 这一层与其他层级的关系
它一端连接 GitHub 事件，一端连接 gradlew、build.gradle 和 dist。它本身不理解 PGMM 业务逻辑，只负责把构建结果送进发布渠道。

## 阅读这一层时要注意什么
CI 环境连 Android build-tools 也准备好了，说明这个项目的发布定义不是只做桌面包，而是把跨平台交付当成正式要求。
