# Power Grid Minimap / PGMM - Documentation (Merged)

This file consolidates the project's documentation into a single entrypoint.

Sources (original files are kept):
- `README.md`
- `RELEASE_NOTES.md`
- `pgmm_files_dox.md`
- `pgmm_overlays_dox.md`
- `pgmm_settings_dox.md`
- `pgmm_rescue_dox.md`
- `pgmm_api_dox.md`
- `pgmm_build_release_dox.md`
- `OverlayUI使用说明.md`

---

## README.md

# Power Grid Minimap / 电网小地图 (Mindustry Mod)

- [中文](#中文)
- [English](#english)

## 中文

### 简介

电网小地图是一个纯客户端的叠加层模组：它会在 **HUD 小地图** 与 **全屏大地图** 上把每个**独立电网**用不同颜色标出来，并在电网中心显示**电力盈亏**。当电网断开或持续缺电时，会提供告警与定位标记，帮助你更快找到断点与缺电源头。

### 功能一览

- 电网着色叠加：在小地图/全屏大地图为每个电网单独着色，快速看清哪些建筑属于同一电网。
- 电力盈亏标记：在电网（或电网分块）中心显示 `+/-` 数值；支持调节字号、颜色、透明度，以及“随小地图缩放”的程度。
- 稀疏电网分块中心：对激光/远距离连接导致的“很稀”的电网，可显示多个中心标记，避免数字挤在同一个点。
- 断网告警 + 建议连接点：大电网分裂后，若在设定时间窗内出现负电，会弹出提示并在地图上标记建议连接点与连线（阈值/颜色/线宽可调）。
- 缺电救援建议（Beta）：电网持续负电时，给出可执行的救援提示，例如：
  - 圈出建议隔离的“正电岛”（让部分区域先恢复供电）
  - 标记建议禁用的冲击反应堆（当它们是主要耗电来源时）
- 电力表（Power Table）：以列表形式汇总“大电网”的概览数据（当前盈亏、近期最低值等），便于快速定位最糟糕的电网；在安装 MindustryX 时可作为 OverlayUI 窗口显示。
- 启动检查更新：可选在启动时检查新版本并提示。

### 使用方法

- 直接打开小地图或按 `M` 打开全屏大地图即可看到叠加层。
- 数值含义：`> 0` 代表供电富余，`< 0` 代表缺电（数值可能随负载/电池充放电而波动）。
- 设置入口：`设置 → 模组 → 电网小地图 (Power Grid Minimap)`（分类名称会随游戏语言变化）。

### 安装

- 桌面端：下载 Release 中的 `powergrid-minimap.zip`（或 `powergrid-minimap.jar`），放入 Mindustry 的 `mods` 目录并在游戏内启用。
- 安卓端：请使用包含 `classes.dex` 的 `powergrid-minimap-android.jar`。

### 反馈

【BEK辅助mod反馈群】：https://qm.qq.com/q/cZWzPa4cTu

![BEK辅助mod反馈群二维码](docs/bek-feedback-group.png)

### 构建（可选，开发者）

在本仓库根目录执行：

```powershell
./gradlew zipMod
```

输出：`build/libs/`

安卓本地构建（在本仓库根目录）：

```powershell
./gradlew.bat jarAndroid
```

输出：`dist/powergrid-minimap-android.jar`

---

## English

### Overview

Power Grid Minimap is a client-side overlay mod. It colors each **separate power network** on the **HUD minimap** and the **full map**, and shows the **net power balance** at the center of each grid. When a grid splits or stays in deficit, it adds alerts and map markers so you can locate the break/culprit faster.

### Features

- Power-network coloring overlay on the minimap and full map.
- Net balance text markers (`+/-`) with configurable size, color, opacity, and minimap scaling.
- Sparse-grid cluster centers so long-range/laser-linked networks can show multiple centers instead of one cramped label.
- Split alerts + reconnect hints: if a large grid splits and one side becomes negative within a time window, the mod warns you and marks suggested reconnect points/lines (thresholds and style are configurable).
- Power Rescue Advisor (Beta): when a grid stays negative, shows actionable hints such as outlining “positive islands” to isolate and marking Impact Reactors to disable when relevant.
- Power Table: a compact list view for large grids (current balance + recent minimum, etc.). If MindustryX is installed, it can be shown as an OverlayUI window.
- Optional update check on game start.

### Usage

- Just open the minimap or press `M` for the full map.
- Balance meaning: `> 0` surplus, `< 0` deficit (values may fluctuate with load/battery behavior).
- Settings: `Settings → Mods → Power Grid Minimap`.

### Install

- Desktop: download `powergrid-minimap.zip` (or `powergrid-minimap.jar`) from Releases, put it into Mindustry's `mods` folder, then enable it in-game.
- Android: use `powergrid-minimap-android.jar` (must include `classes.dex`).

### Feedback

Discord: https://discord.com/channels/391020510269669376/1467903894716940522

### Build (Optional)

From this repo root:

```powershell
./gradlew zipMod
```

Output: `build/libs/`

Android jar (from this repo root):

```powershell
./gradlew.bat jarAndroid
```

Output: `dist/powergrid-minimap-android.jar`

---

## RELEASE_NOTES.md

## v1.10.0 更新日志（PGMM / Power Grid Minimap）

### 🧩 电网显示过滤（高级）
- 🧹 新增高级设置项「忽略面积小于 X 的电网」（单位：格²）。
  - 电网面积 = 有效发电厂/用电建筑/存电建筑的面积总和。
  - 当面积 < X 时，该电网会在电网表与小/大地图上被忽略显示；设为 0 表示关闭。

### 🔄 自动检测更新（GitHub）
- 🧾 更新弹窗增强：展示 Release 说明/发布时间，并列出可下载文件（assets）。
- 📥 支持“下载并重启”：下载完成后自动安装更新并重启游戏（桌面端）。
- 🌐 支持镜像下载开关（ghfile.geekertao.top），用于在部分网络环境下加速下载。

---

## pgmm_files_dox.md

# PGMM 文件结构说明（Power Grid Minimap）

本文档概览本仓库（`Power-Grid-Minimap-repo-clone`）的主要文件/目录职责，方便二次开发与排查问题。

> 说明：PGMM 是 Mindustry 客户端 Java 模组；本仓库根目录即“模组根目录（仓库根目录）”。

---

## 1) 顶层文件

- `README.md`
  - 面向玩家的说明（安装/功能简介）。
- `OverlayUI使用说明.md`
  - 面向开发者：如何把窗口接入 MindustryX 的 OverlayUI（反射 + 回退方案）。
- `build.gradle`
  - Gradle 构建脚本：`zipMod` / `jarMod` / 拷贝到 `dist/`。
- `settings.gradle`
  - Gradle 工程名称等。
- `LICENSE`
  - 许可证。

---

## 2) 目录说明

- `.github/workflows/release.yml`
  - GitHub Actions：push `v*` tag 时自动发 Release，上传 `dist/powergrid-minimap.zip` 与 `dist/powergrid-minimap.jar`。
- `dist/`
  - 发布产物目录（本地构建后自动更新）：
    - `powergrid-minimap.zip`：传统 Mindustry 模组包（桌面/安卓都可用）。
    - `powergrid-minimap.jar`：安卓更常用的 jar 形式（内容与 zip 等价）。
- `src/main/java/powergridminimap/`
  - 模组源码（核心逻辑/UI/绘制/反射集成）。
- `src/main/resources/`
  - `mod.json`：Mindustry 模组元数据（名称/版本/入口类等）。
  - `bundles/`：多语言文本资源（设置项名称/描述、toast、提示文字等）。

---

## 3) Java 源码文件职责（按重要性）

- `src/main/java/powergridminimap/PowerGridMinimapMod.java`
  - 模组主入口与绝大部分逻辑所在：
    - 缓存/扫描电网
    - 小地图/全屏地图叠加绘制
    - 缺电救援建议（正电岛多边形圈选、冲击反应堆禁用建议、去抖窗口）
    - Power Table（HUD 或 MindustryX OverlayUI）
    - 与 MI2 / MindustryX 的可选集成（反射）
    - 设置页注册（已改为 MindustryX 风格行组件）
- `src/main/java/powergridminimap/PgmmSettingsWidgets.java`
  - PGMM 的“MindustryX 风格”设置项 UI 组件：
    - Header / Check / Slider / Text 行组件（图标、背景、可换行标题、tooltip 描述）。
- `src/main/java/powergridminimap/PgmmTypes.java`
  - 一些数据结构与反射小工具（例如全屏地图对齐用的 `FullMinimapAccess`）。
- `src/main/java/powergridminimap/GithubUpdateCheck.java`
  - 启动后异步检查 GitHub 上 `mod.json` 的版本号，提示可更新（离线/失败静默跳过）。

---

## pgmm_overlays_dox.md

# PGMM 叠加层/绘制系统说明（Minimap / Full Map / World / OverlayUI）

本文档描述 PGMM 的绘制入口、坐标系、以及与其他模组（MI2、MindustryX OverlayUI）的集成方式。

---

## 1) 绘制入口与坐标系

- HUD 小地图叠加（UI 内嵌）
  - 通过 `ensureOverlayAttached()` 把自定义 `MinimapOverlay` 作为 minimap 的 child 挂上去。
- 全屏地图叠加（按 M 打开）
  - 通过 `Trigger.uiDrawEnd`，拿到全屏地图的 pan/zoom/baseSize（`FullMinimapAccess` 反射），构造 transform 再绘制 overlay/marker。
- 主游戏画面世界坐标叠加
  - 通过 `Trigger.draw` 执行 `drawWorldRescueOverlay()`：在世界坐标系画救援多边形与冲击反应堆标记。

---

## 2) Power Table（电力表）显示模式

Power Table 可在两种宿主中显示：

1) Vanilla / 未安装 MindustryX：
   - 挂到 `ui.hudGroup`，并在 `act()` 中锚定到 minimap 附近（左侧）。
2) 安装了 MindustryX：
   - 通过反射调用 `OverlayUI.INSTANCE.registerWindow(...)` 注册为 OverlayUI 窗口。
   - 此时 `PowerTableOverlay` 会进入 `hostedByOverlayUI=true` 模式：不再自己 setPosition，避免与 OverlayUI 拖拽/吸附冲突。

---

## 3) MI2 小地图集成（可选）

若玩家安装 MI2-Utilities：

- 通过遍历 `Vars.mods.list()`，在各自 `mod.loader` 中尝试 `Class.forName("mi2u.ui.MinimapMindow", false, mod.loader)`。
- 成功后反射拿到 MI2 minimap 的 element 与 rect/setRect 接口，把 overlay 作为 minimap 的 sibling 挂在其 parent 下。

失败策略：反射失败/MI2 尚未初始化时静默跳过，后续定时重试。

---

## 4) MindustryX OverlayUI 集成（可选）

文件内实现：`PowerGridMinimapMod.MindustryXOverlayUI`

- 检测：`Vars.mods.locateMod("mindustryx") != null`
- 反射：
  - `Class.forName("mindustryX.features.ui.OverlayUI")`
  - `OverlayUI.INSTANCE` + `registerWindow(String, Table)`
  - 可选调用 `window.setAvailability(Prov<Boolean>)`
- 同步开关：
  - OverlayUI 窗口实际是否显示由 `window.data.enabled` 与 `pinned` 决定。
  - PGMM 把 `pgmm-power-table` 的 boolean 映射到 enabled/pinned，以保持“开关即显示”的玩家体验。

---

## pgmm_settings_dox.md

# PGMM 设置项设计与风格说明（MindustryX 风格）

本文档说明 PGMM 的设置项组织方式、命名规则、以及与 MindustryX 设置风格同步的实现手法。

---

## 1) 设置存储

PGMM 使用 Mindustry 原生 `Core.settings` 存取配置：

- `Core.settings.defaults(key, def)`：注册默认值
- `Core.settings.getBool/getInt/getString(...)`：读取
- `Core.settings.put(key, value)`：写入

这些 key 以 `pgmm-` 开头（例如 `pgmm-enabled`）。

---

## 2) 设置 UI：MindustryX 风格行组件

实现文件：`src/main/java/powergridminimap/PgmmSettingsWidgets.java`

提供 4 类行组件（与 MindustryX/StealthPath 设置页类似）：

- `HeaderSetting`
  - 分组标题：`Styles.black3` 背景 + `Pal.accent` 标题色。
- `IconCheckSetting`
  - 开关：`Tex.button` 容器 + 左侧图标 + 可换行标题，`addDesc(...)` 提示描述。
- `IconSliderSetting`
  - 滑条：`Stack(slider, content)` 叠加，在滑条上方显示标题与当前值。
- `IconTextSetting`
  - 文本：左侧标题 + 右侧输入框（常用于 Hex 颜色）。

宽度策略：`prefWidth()` 根据屏幕宽度取一个上限，避免桌面过宽、移动端过窄。

---

## 3) 设置页入口与分组

设置页注册位置：`PowerGridMinimapMod.registerSettings()`

分组标题文案来自 bundles：

- `pgmm.section.basic`（基础）
- `pgmm.section.integration`（集成）
- `pgmm.section.alerts`（告警与标记）
- `pgmm.section.advanced`（高级）
- `setting.pgmm.rescue.section`（缺电救援）
- `setting.pgmm.powertable.section`（电力表）
- `pgmm.section.performance`（性能）

---

## 4) 文本资源（bundles）

文件：`src/main/resources/bundles/bundle*.properties`

主要类别：

- `setting.<key>.name/description`：原生设置项名称/描述
- `pgmm.*`：toast/提示文字/分组标题等
- `settingV2.overlayUI.pgmm-power-table.name`
  - MindustryX OverlayUI 读取的窗口标题（OverlayUI 侧用 `settingV2.overlayUI.<windowName>.name`）。

建议：新增设置项时，至少补 `bundle.properties`（英文）与 `bundle_zh_CN.properties`（中文）。

---

## pgmm_rescue_dox.md

# PGMM 缺电救援建议（Rescue Advisor）说明

本文档说明“缺电救援建议”模块的目标、判断逻辑与输出形式，便于二次优化（例如：更精准的正电岛边界、更多类型的救援动作等）。

---

## 1) 功能目标

当电网持续为负电时，提供“恢复正电”的建议，并在：

- 小地图 / 全屏地图：标出建议
- 主游戏画面：直接绘制建议（无鼠标交互）

当前实现支持两类建议：

1) **正电岛隔离建议**：断开某条电力链接后，某一侧成为可自洽的正电区域，使用多边形圈出该区域。
2) **冲击反应堆禁用建议**：当禁用若干个“净耗电”的冲击反应堆即可让电网回正电时，优先给出该方案并标记建议禁用的反应堆。

---

## 2) 去抖/清除策略（避免反复提示）

设置项：`pgmm-rescue-clearwindow`（k 秒）

- 为每个 `PowerGraph` 维护一个滑动窗口（ring buffer），记录最近 k 秒内的 `powerBalance(/s)` 采样。
- 计算窗口最小值 `minBalance`：
  - 若 `minBalance > 0`：说明该窗口内始终为正电，可自动清除救援提示。

目的：解决“电力一会负一会正导致提示反复出现”的噪音问题。

---

## 3) 正电岛计算与多边形圈选

核心步骤：

1) 枚举候选“可断开的电力链接”（目前主要针对 PowerNode 激光链接）。
2) 对每条候选边，分别在“假设断开”情况下做 BFS 计算两侧 component 的：
   - 产电（produced）
   - 需电（needed）
   - 储能（stored）
3) 若某一侧的 `produced - needed` 为正（且满足一些过滤条件），作为正电岛候选。
4) 为了在世界/地图上圈出区域：
   - 收集 BFS 访问到的所有 tile 的四个角点
   - 计算这些角点的凸包（convex hull）
   - 以凸包多边形作为“正电岛轮廓”（速度快，但可能包含空洞/凹陷区域）

---

## 4) 冲击反应堆禁用建议

- 扫描电网内所有 `Blocks.impactReactor`
- 计算每个反应堆的每秒净贡献：
  - `net = produced - needed`
  - 只对 `net < 0`（净耗电）的反应堆给出禁用建议（避免误伤净发电的反应堆）
- 按“禁用带来的改善值”排序，取最小数量的前缀使改善总和覆盖 deficit
- 若无法完全覆盖 deficit，则不输出该方案（避免给出无效建议）

---

## 5) 输出与绘制

- Rescue 提示会写入 `RescueAlert`，携带：
  - 多边形 hull + bbox + centroid（文本标签位置）
  - ImpactDisableHint 列表（位置 + rank）
- 绘制入口见 `pgmm_overlays_dox.md`：
  - 世界叠加：多边形线框 + `#rank + net/s` 文本
  - Impact：方框标记 + `!rank`

---

## pgmm_api_dox.md

# PGMM 主要接口说明（对外可用/可复用）

本文档列出 PGMM 中“相对稳定、对外可调用/可复用”的函数接口与入口点。PGMM 绝大多数逻辑是内部类/私有方法；因此这里以“可调用入口 + 行为契约”为主，而不是逐个 private 方法罗列。

---

## 1) 模组入口类

### `powergridminimap.PowerGridMinimapMod`

- 入口：由 `src/main/resources/mod.json` 的 `main` 指向。
- 生命周期：
  - `ClientLoadEvent`：注册 settings / 初始化颜色 / 尝试集成 MI2 / 安装控制台 API / 触发更新检查等。
  - `WorldLoadEvent`：清理缓存，延迟挂载 overlay/power table。
  - `Trigger.update`：周期性更新扫描与建议（split watcher / rescue advisor 等）。
  - `Trigger.draw`：在主游戏画面绘制救援叠加层（世界坐标系）。
  - `Trigger.uiDrawEnd`：在全屏地图上绘制 overlay 与 marker。

---

## 2) 控制台 API（F8 Console）

### `PowerGridMinimapMod.PgmmConsoleApi`

以 `pgmm` 对象挂到脚本作用域（安装成功会日志提示）。

- `String help()`
  - 返回可用命令列表（字符串）。
- `String restart()`
  - 重启 PGMM：清理缓存、重新挂载 overlay 等。
- `String rescan()`
  - 立即触发一次电网扫描/overlay 重建（绕过延迟）。
- `String mi2Refresh()`
  - 强制重新检测并重新挂载 MI2 overlay（前提：装了 MI2 且开关开启）。

注意：这些方法是给玩家/调试用的“软接口”，不是多人同步/服务器逻辑。

---

## 3) 客户端命令（聊天命令）

在 `registerClientCommands(...)` 注册（只影响客户端）：

- `/pgmm-restart`
  - 同 `restart()`：重启 PGMM。
- `/pgmm-rescan`
  - 同 `rescan()`：立即扫描重建。
- `/pgmm-mi2 [on/off/refresh]`
  - 开关或刷新 MI2 overlay。

---

## 4) 构建接口（Gradle Tasks）

在 `build.gradle` 中提供：

- `zipMod`
  - 生成 `build/libs/powergrid-minimap.zip`，并复制到 `dist/powergrid-minimap.zip`。
- `jarMod`
  - 生成 `build/libs/powergrid-minimap.jar`，并复制到 `dist/powergrid-minimap.jar`。

两者内容等价，都是 Mindustry 可加载的“类 + 资源”归档；安卓端通常更习惯使用 `.jar`。

---

## pgmm_build_release_dox.md

# PGMM 构建 / 打包 / Release 工作流说明

本文档说明如何在本仓库内构建 PGMM，以及 GitHub Release 的自动化流程（tag 触发上传产物）。

---

## 1) 本地构建（Windows / PowerShell）

在仓库根目录执行：

```powershell
.\gradlew.bat zipMod
.\gradlew.bat jarMod
```

输出：

- `dist/powergrid-minimap.zip`
- `dist/powergrid-minimap.jar`

两者内容等价（都包含编译后的 class 与 `src/main/resources` 资源）。Mindustry 安卓端通常更偏好 `.jar`，但 `.zip` 也能用。

---

## 2) 版本号维护

版本需要保持一致：

- `build.gradle`：`version = "x.y.z"`
- `src/main/resources/mod.json`：`"version": "x.y.z"`

建议：每次发布前先修改版本号，再本地构建生成 `dist/` 产物。

---

## 3) GitHub Actions 自动 Release

工作流文件：

- `.github/workflows/release.yml`

触发条件：

- push tag：`v*`（例如 `v1.8.0`）

行为：

1) checkout 代码
2) 创建 GitHub Release
3) 上传：
   - `dist/powergrid-minimap.zip`
   - `dist/powergrid-minimap.jar`

注意：

- 该工作流不会帮你本地生成 `dist/`，因此发布前应确保仓库里提交了最新的 `dist/*`（或者你修改工作流让 CI 自己构建再上传）。

---

## 4) 推荐发布步骤（当前仓库习惯）

1) 更新版本号（`build.gradle` + `mod.json`）
2) 本地构建（`zipMod` + `jarMod`）确保 `dist/` 更新
3) commit + push `main`
4) 打 tag 并 push：

```powershell
git tag -a vX.Y.Z -m "vX.Y.Z"
git push origin vX.Y.Z
```

随后 Actions 会自动生成 Release 并附带产物。

---

## OverlayUI使用说明.md

# OverlayUI 使用说明（MindustryX）

本文档说明：在 Mindustry 模组中，如何把自己的 UI 窗口接入 MindustryX 的 `OverlayUI`（可拖拽/可固定/可缩放/可在设置里管理），并在未安装 MindustryX 时提供回退显示方案。

> 适用对象：Mindustry 模组作者（Java/Kotlin 都可）。  \
> 术语：本文把你要显示的那块 UI（`Table` / `Element`）称为“窗口内容”；把 MindustryX 管理的壳（Window）称为“OverlayUI 窗口”。

---

## 1. 前置条件

1) 玩家安装了 MindustryX（模组 ID 通常为 `mindustryx`）。  \
2) 你的模组运行在客户端（`ClientLoadEvent` 之后才能安全访问 UI）。  \
3) 你的 UI 代码基于 Arc Scene2D（`arc.scene.*` / `arc.scene.ui.layout.Table`）。

---

## 2. OverlayUI 能提供什么

- 窗口管理：统一在 OverlayUI 面板里显示/隐藏、锁定（Pinned）、设置缩放等。
- 拖动/吸附/缩放：OverlayUI 自带窗口拖拽与约束吸附系统（玩家体验更一致）。
- 可配置持久化：OverlayUI 会把每个窗口的数据写到 `overlayUI.<name>` 的配置里（坐标、大小、锁定、缩放等）。

你只需要提供一个 `Table`（窗口内容），再调用 `registerWindow(name, table)` 注册即可。

---

## 3. 最小接入示例（Kotlin，写在 MindustryX 环境里）

如果你的项目本身依赖了 MindustryX（能直接 import 到 `mindustryX.features.ui.OverlayUI`），最简单的写法：

```kotlin
import mindustry.Vars
import mindustryX.features.ui.OverlayUI
import arc.scene.ui.layout.Table

val content = Table().apply {
    name = "my-window-content"
    // ... build UI
}

// 仅示例：你也可以放到 ClientLoadEvent 里
val window = OverlayUI.registerWindow("my-window", content).apply {
    // 仅在游戏内可用（避免菜单界面乱入）
    availability = { Vars.state.isGame }
    resizable = true
    autoHeight = true
}
```

注意：OverlayUI 会把 `content` 包装进自己的 Window 外壳里，窗口标题、设置按钮、关闭按钮等都由 OverlayUI 管理。

---

## 4. Java 模组的推荐写法（不强依赖 MindustryX：反射 + 回退）

很多模组不想把 MindustryX 作为编译期依赖（避免玩家没装 MindustryX 就崩溃）。这时建议：

1) **运行时检测**：是否安装了 MindustryX。  \
2) **反射注册**：若存在则 `OverlayUI.INSTANCE.registerWindow(name, table)`。  \
3) **HUD 回退**：不存在则把 `table` 加到 `ui.hudGroup` 或 `Core.scene`。

### 4.1 检测 MindustryX 是否存在

```java
boolean hasMindustryX = Vars.mods != null && Vars.mods.locateMod("mindustryx") != null;
```

### 4.2 反射调用 OverlayUI.registerWindow

MindustryX 的 `OverlayUI` 在 Kotlin 中是 `object OverlayUI`，Java 侧会暴露为：
- `OverlayUI.INSTANCE` 单例字段
- `OverlayUI.INSTANCE.registerWindow(String, Table)` 方法

示例（省略异常处理）：

```java
Class<?> overlayUiClass = Class.forName("mindustryX.features.ui.OverlayUI");
Object overlayUi = overlayUiClass.getField("INSTANCE").get(null);
Method registerWindow = overlayUiClass.getMethod("registerWindow", String.class, Table.class);

Object window = registerWindow.invoke(overlayUi, "my-window", contentTable);
```

### 4.3 只在游戏内可用（availability）

OverlayUI.Window 有一个 `availability`（Kotlin 属性，Java 会表现为 setter 方法）。  \
在不引入 MindustryX 的情况下，最稳妥做法是 **可选地**反射调用：

```java
// window.getClass().getMethod("setAvailability", Prov.class).invoke(window, (Prov<Boolean>)() -> Vars.state.isGame());
```

注意：availability 的类型是 `arc.func.Prov<Boolean>`（不是 `Boolp`）。

### 4.4 同步你自己的开关到 OverlayUI 的 enabled/pinned

OverlayUI 的“窗口启用状态”由 `window.data.enabled` 控制（同时 pinned 影响是否常驻显示）。  \
如果你希望你的模组设置（例如 `my-window-enabled`）控制 OverlayUI 面板显示，可以在更新循环里同步：

```java
// 伪代码：当你的设置从 false->true 时，设置 data.enabled=true 且 data.pinned=true
```

这样玩家不必先打开 OverlayUI 再点 “+” 添加窗口，体验更像“开关即显示”。

---

## 5. 窗口内容 Table 的设计建议

1) **不要自己管理位置/尺寸**（OverlayUI 模式下）
   - 位置、大小交给 OverlayUI；你的 `Table` 只负责内容布局与数据刷新。
2) **可见性与刷新逻辑**
   - 你的 `act()` 里可以根据 `Vars.state.isGame()` 判断是否更新内容；但不要强制 `setPosition(...)`。
3) **背景**
   - OverlayUI.Window 自己会绘制窗口壳（标题栏等），你的内容背景可选。
   - 如果你仍想要内容背景，注意不要与窗口壳冲突（例如黑底透明即可）。
4) **触摸/鼠标**
   - 如果你只是展示信息，`touchable = Touchable.disabled` 能减少误触。
   - 如果你有按钮/滚动等交互，使用 `Touchable.childrenOnly` 并保证 UI 元素可点击。

---

## 6. 与 WayzerMapBrowser 的一致做法（参考）

WayzerMapBrowser 的逻辑是：

- 如果检测到 MindustryX：`OverlayUI.INSTANCE.registerWindow("wayzer-maps", table)`  \
- 否则：`Core.scene.add(table)` 并自己做拖拽/定位

PGMM 也采用了相同思路：优先 OverlayUI，缺失时 HUD 回退。

---

## 7. PGMM 当前实现位置（可直接抄结构）

PGMM 的 OverlayUI 对接代码在：
- `src/main/java/powergridminimap/PowerGridMinimapMod.java`：
  - `ensurePowerTableAttached()`：优先注册到 OverlayUI，否则回退 HUD
  - `MindustryXOverlayUI`：反射包装（检测、registerWindow、enabled/pinned 同步）
  - `PowerTableOverlay.setHostedByOverlayUI(true/false)`：切换“是否由 OverlayUI 托管”，避免自己强行锚定坐标

---

## 8. 常见坑

1) **用太早注册**
   - UI 还没初始化时注册会失败；建议放到 `ClientLoadEvent` 后，并用 `Time.runTask(...)` 延迟一次重试。
2) **Table 自己强行 setPosition**
   - 在 OverlayUI 模式下会和 Window 的拖拽/吸附冲突；务必关掉你自己的定位逻辑。
3) **把 MindustryX 当 compile 依赖**
   - 玩家没装 MindustryX 会 ClassNotFound 崩；除非你明确要求依赖，否则用反射更稳。
