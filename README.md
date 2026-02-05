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

在 `Mindustry-master` 根目录执行：

```powershell
./gradlew.bat :powergrid-minimap:zipMod
```

输出：`mods/powergrid-minimap/build/libs/`

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

From the `Mindustry-master` root:

```powershell
./gradlew.bat :powergrid-minimap:zipMod
```

Output: `mods/powergrid-minimap/build/libs/`

Android jar (from this repo root):

```powershell
./gradlew.bat jarAndroid
```

Output: `dist/powergrid-minimap-android.jar`
