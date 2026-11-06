# 架构总览

改 `src/` 下的任何代码前先读本文。PGMM 是纯客户端信息增强模组：扫描电力图（PowerGraph），把电网分色、盈亏、断网告警与救援提示画到 HUD 小地图、全屏大地图和主游戏画面上。玩家视角说明见 [DOC.md](../DOC.md)。

## 入口与生命周期

Mindustry 按 `mod.json` 加载入口；本模组声明了双入口：

| 入口 | 类 | 环境 | 差异 |
| --- | --- | --- | --- |
| `main` | `powergridminimap.PowerGridMinimapMod` | 原版客户端 | 桥能力为 UNSUPPORTED / Noop |
| `mainX` | `powergridminimap.PowerGridMinimapModX` | MindustryX 类加载器存在时 | 注入 `MindustryXMarkerBridge` + `MindustryXOverlayUiBridge` |

`PowerGridMinimapModX` 只有一个构造函数：向基类 protected 构造传入桥实现，业务逻辑全部留在基类。

基类构造函数挂接的事件流：

1. `ClientLoadEvent`：写入全部设置默认值（`pgmm-*` key）、`registerSettings()` 注册设置页、刷新三个标记颜色、`mi2.tryInit()`、安装 F8 控制台 API、`GithubUpdateCheck.checkOnce()`。
2. `WorldLoadEvent`：清空缓存与告警状态，延迟重挂 overlay / power table。
3. 建筑与配置事件（BuildEnd / Destroy / Rotate / TeamChange / Config）：`cache.invalidateAll()` 触发带去抖的重建。
4. `Trigger.update`：周期重试挂载（HUD 可能被重建），推进 `SplitWatcher` 与 `RescueAdvisor`。
5. `Trigger.draw` / `Trigger.uiDrawEnd`：分别在主画面绘制救援标记、在全屏大地图绘制电网叠加。

## 核心类与数据流

主体在 `powergridminimap/PowerGridMinimapMod.java`（约 4200 行，功能以内部类组织）：

```text
PowerGraph（游戏本体）
   |  扫描 / 缓存
   v
PowerGridCache ──► GridInfo / MarkerInfo / MarkerRectInfo（见 PgmmTypes.java）
   |
   |-- MinimapOverlay                   HUD 小地图：电网分色 + 盈亏数字
   |-- Mi2MinimapIntegration/Mi2Overlay MI2U 小地图（反射接入，缺失时静默降级）
   |-- Trigger.uiDrawEnd                全屏大地图叠加（分色纹理 + 标记）
   |-- SplitWatcher ──► SplitAlert      电网分裂检测 → 告警 + 重连建议连线
   |-- RescueAdvisor ──► RescueAlert    持续负电 → 救援建议（正电岛隔离 / 冲击反应堆禁用）
   |-- PowerTableOverlay                电力表（默认关）；MDtX 下注册为 OverlayUI 窗口
   `-- NativeMarkers                    联机兼容：聊天发送 "<PGMM><标签>(x,y)" 标记
```

外部能力隔离：

- `mdtxcompat` 包提供 `OverlayUiBridge` / `MarkerBridge` / `SchematicShareBridge` 接口及 MindustryX 实现、Noop 实现；`LegacyMindustryXGuard` 拒绝过旧的 MDtX 环境。
- MI2-Utilities-Java 通过反射访问（`mi2u.ui.MinimapMindow`，逐个 mod 类加载器查找），未安装时不崩溃。

## 设置系统

- 独立运行：`registerSettings()` 调 `ui.settings.addCategory(category, Icon.powerSmall, this::bekBuildSettings)`。
- 全部设置项集中在 `bekBuildSettings(SettingsMenuDialog.SettingsTable)`，控件由 `PgmmSettingsWidgets` 提供图标行样式；分组：Basic / Integration / Alerts & Markers / Advanced / Rescue / Power Table / Performance / Update。
- 设置 key 统一 `pgmm-` 前缀，文案 key 统一 `pgmm.*` / `setting.pgmm.*`，源文件在 `src/main/resources/bundles/bundle*.properties`（约 30 种语言）。

## Neon 聚合形态

PGMM 同时作为 Neon 聚合模组的子模组存在，双形态靠主类上的 `public static boolean bekBundled` 区分：

- `bekBundled == false`（独立安装）：按上文注册独立设置分类。
- `bekBundled == true`（被 Neon 打包）：`registerSettings()` 直接早退，由 Neon 总设置页调用 `bekBuildSettings(...)` 接管分组；其余逻辑不变。
- 聚合侧只依赖这两个契约成员，本仓库不引入任何 `bektools` 符号，保持可独立构建。

## 客户端指令与控制台

- `/pgmm-restart` 清缓存并重挂 overlay；`/pgmm-rescan` 立即重扫；`/pgmm-mi2 [on/off/refresh]` 控制 MI2U 叠加。
- F8 控制台 API（`PgmmConsoleApi`）：`pgmm.help() / restart() / rescan() / mi2Refresh() / mi2On() / mi2Off()`。

## 目录速查

```text
src/main/java/powergridminimap/
|-- PowerGridMinimapMod.java    主类：生命周期 + 全部功能内部类
|-- PowerGridMinimapModX.java   MDtX 入口（仅注入桥）
|-- PgmmTypes.java              GridInfo / MarkerInfo / MarkerRectInfo 等共享类型
|-- PgmmSettingsWidgets.java    设置页图标控件
`-- GithubUpdateCheck.java      启动更新检查
src/main/java/mdtxcompat/       MDtX 桥接口、Noop 实现、LegacyMindustryXGuard
src/main/resources/bundles/     bundle*.properties 多语言文案
```

## 设计约束（务必保持）

- 纯客户端：不要求服务器安装；联机告警只走聊天消息路径。
- 对 MDtX / MI2U 一律经桥接口或反射访问，缺失时静默降级，不允许 import 后硬依赖。
- 缺电救援建议、电力表、MI2U 叠加、联机兼容标记默认关闭。
- 场景控件（`Table` / `Element` 等）只能在渲染线程创建：模组构造函数由 `Vars.loadAsync` 在资源加载线程执行，所以 `PowerTableOverlay` 在 `ensurePowerTableAttached()` 中惰性创建；不要在字段初始化或构造函数里 `new` 任何 UI，否则 Arc 会记录 `RuntimeException: UI should be created in main Thread`。
- 保持 Java 8 字节码兼容（`options.release.set(8)`）。
- 原生标记的 minimap 可见性由 `showOnMinimap()` 按字段类型写入：v160.1 起该字段是 `@IndexBool int`（`MapMarkers.mapMarkers` 的索引），必须在 `Call.createMarker` 注册前写成非 -1，注册时会被 `MapMarkers.add()` 改写成真实索引；旧版仍是 boolean。
