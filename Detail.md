# Detail: 仓库根目录

## 这一层在做什么
仓库根目录是整个 Power Grid Minimap 模组的总控层。这里同时放着玩家可读文档、开发者专题文档、Gradle 构建脚本、模组元数据，以及所有源码层、构建层、缓存层和发布层的入口目录。真正的业务源头主要在 src，真正决定打包策略的是 build.gradle，而真正决定模组身份的是根目录 mod.json。

## 这一层直接包含什么
### 直接子目录
- .github
- .gradle
- .vscode
- bin
- build
- dist
- docs
- gradle
- src

### 直接文件
- .gitignore（73 B）
- AGENTS.md（1.11 KB）
- build.gradle（10.68 KB）
- DOC.md（25.28 KB）
- gradlew（8.53 KB）
- gradlew.bat（2.87 KB）
- LICENSE（34.98 KB）
- mod.json（579 B）
- OverlayUI使用说明.md（9.68 KB）
- pgmm_api_dox.md（2.19 KB）
- pgmm_build_release_dox.md（1.56 KB）
- pgmm_files_dox.md（2.42 KB）
- pgmm_overlays_dox.md（2.19 KB）
- pgmm_rescue_dox.md（2.65 KB）
- pgmm_settings_dox.md（2.10 KB）
- README.md（4.67 KB）
- RELEASE_NOTES.md（2.00 KB）
- settings.gradle（40 B）

## 这一层如何实现自己的职责
这一层通过 settings.gradle 定义工程名，通过 build.gradle 固定 Java 8 兼容、Mindustry 依赖、桌面包、Android 包、合并包和 deploy 工作流。README、DOC、RELEASE_NOTES 与多份 pgmm_* 文档则构成面向不同读者的解释层：README 对玩家，专题文档对维护者，DOC 负责总入口。也就是说，根目录不是简单文件堆，而是把实现、发布和说明统一编排起来的控制台。

## 这一层与其他层级的关系
向下看，src 是源层，gradle 是构建引导层，.github 是远端发布自动化层，.gradle、build、bin 是本地产生的缓存与中间结果层，dist 是最终分发层，docs 是文档素材层。向上看，Mindustry 最终只关心归档里的 class、bundle 和 mod.json，但这些东西都在根目录这一层被组织出来。为避免污染 Git 元数据，本次补充刻意没有向 .git 内写入文档。

## 阅读这一层时要注意什么
当前还能看到一些状态信息：dist 内已有历史产物，工作区还有两个未跟踪的 Power-Grid-Minimap-.jar/.zip；.gitignore 明确把 .gradle、build、bin、.vscode 与外部构建目录视为派生物。这说明仓库采用“源码与文档入库，缓存与产物本地生成”的典型工作流。
