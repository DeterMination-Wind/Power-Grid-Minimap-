# Detail: src\main\java\powergridminimap

## 这一层在做什么
这一层是 PGMM 最核心的 Java 包层，真正决定模组如何扫描电网、绘制叠加、显示电力表、给出救援建议、检查更新和对接外部 UI 的业务实现基本都在这里。

## 这一层直接包含什么
### 直接子目录
- 本层没有直接子目录。

### 直接文件
- GithubUpdateCheck.java（25.10 KB）
- PgmmSettingsWidgets.java（8.63 KB）
- PgmmTypes.java（2.70 KB）
- PowerGridMinimapMod.java（167.20 KB）

## 这一层如何实现自己的职责
当前共有四个源码文件。PowerGridMinimapMod.java 约三千四百多行，是总控文件，负责事件注册、Core.settings 默认值、设置页、缓存更新、小地图和全屏地图绘制、世界坐标救援叠加、Power Table、MI2 集成、MindustryX OverlayUI 反射适配、控制台 API、SplitWatcher 与 RescueAdvisor。PgmmSettingsWidgets.java 把设置项封装成 MindustryX 风格的行组件，统一了 Tex.button 背景、左侧图标、可换行标题和叠加式滑条值显示。PgmmTypes.java 放置 GridInfo、MarkerInfo、MarkerRectInfo 和 FullMinimapAccess 等轻量类型与反射工具。GithubUpdateCheck.java 则负责通过 GitHub Releases API 检查新版本、展示对话框、下载并安装更新，必要时触发应用重启。

## 这一层与其他层级的关系
它向上依赖 src/main/resources/bundles 提供所有可见文本，向下被编译成 build/classes/.../powergridminimap 与 bin/main/powergridminimap。根目录那些专题文档基本都是围绕这一层的具体机制展开说明。

## 阅读这一层时要注意什么
如果未来要继续提高清晰度，最值得做的不是再加目录，而是把 PowerGridMinimapMod.java 内已经形成子系统的内部类继续外提成独立文件。
