# src/main/java/powergridminimap 详细说明

目录路径：`src/main/java/powergridminimap`

## 本层定位
- 这一层承载 Power Grid Minimap 的主实现，是仓库里最重的功能包之一：电网扫描、分裂检测、重连建议、救援建议、HUD、全图叠加和电力表窗口都集中在这里。

## 当前内容
- 直接子目录数：0。
- 直接文件数：4。
- 递归下级目录数：0。
- 递归文件数：4。
- 本层没有直接子目录。
- 直接文件：`GithubUpdateCheck.java`、`PgmmSettingsWidgets.java`、`PgmmTypes.java`、`PowerGridMinimapMod.java`。

## 实现方式
- `PowerGridMinimapMod` 通过客户端加载、世界事件和 `Trigger.update/draw/uiDrawEnd` 驱动缓存失效与重算；核心数据来自 PowerGraph 扫描，而不是自建模拟。
- `PgmmSettingsWidgets` 提供定制设置行，`PgmmTypes` 提供数据结构，`GithubUpdateCheck` 处理子模组级更新提示。
- 模块支持 MindustryX OverlayUI、MI2 小地图和 Rhino 控制台 API，因此除了主逻辑外还承担多种外部集成桥接。

## 与其他层级的关系
- 向上被 Neon 聚合为一个设置分组和客户端命令源；向下依赖 bundles 文案、UI 层和世界状态层。

## 本层文件解读
- `GithubUpdateCheck.java`: Java source，Mod implementation source code.，大小 25.1 KB。 声明类型：GithubUpdateCheck, AssetInfo, ReleaseInfo；公开/受保护方法约 0 个
- `PgmmSettingsWidgets.java`: Java source，Mod implementation source code.，大小 8.6 KB。 声明类型：PgmmSettingsWidgets, HeaderSetting, IconCheckSetting, IconSliderSetting, IconTextSetting；公开/受保护方法约 1 个
- `PgmmTypes.java`: Java source，Mod implementation source code.，大小 2.7 KB。 声明类型：GridInfo, MarkerInfo, MarkerRectInfo, FullMinimapAccess；公开/受保护方法约 0 个
- `PowerGridMinimapMod.java`: Java source，Mod implementation source code.，大小 164.2 KB。 声明类型：PowerGridMinimapMod, PgmmConsoleApi, MinimapOverlay, Mi2MinimapIntegration, Mi2Overlay；公开/受保护方法约 25 个，含聚合设置入口; 含打包标记; 含 OverlayUI 集成; 含客户端命令注册

## 维护关注点
- 这是性能和复杂度双高的模块，目录级说明必须强调它是事件驱动加缓存驱动，而不是每帧全量扫描。
- 这一层更接近事实来源，做结构或行为调整应优先改这里，再通过构建链刷新下游。

## 层级关系速记
- 上层目录：`src/main/java`。
- 下层入口：无。
- 当前文档由 `tools/generate_detail.py` 依据工作区实时结构与人工摘要生成。
