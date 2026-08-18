# Power Grid Minimap / 电网小地图

## 中文

> 让大型基地的电力问题一眼可见。

Power Grid Minimap 是一个 Mindustry 客户端信息增强模组。它把原本分散在电网连接、地图和建筑状态里的信息整理到小地图与全图中，帮助你快速看懂每个电网的范围、盈亏和异常位置。

当基地变大、远距离供电变多或电网意外断开时，你可以更快找到问题所在，而不必逐个检查建筑和连接线。模组只增强本地显示，不要求服务器安装，适合单人和多人游戏。

### 安装

从 Release 下载 powergrid-minimap.zip 或 powergrid-minimap.jar，放入 Mindustry 的 mods 目录并在游戏内启用。

### 使用

启用后打开小地图或全图即可查看电网信息。详细显示方式和阈值可在 设置 → 模组 → Power Grid Minimap 中调整。MindustryX 或 MI2-Utilities 可提供额外的悬浮窗口整合，但不是核心功能的必要条件。

### 构建（开发者）

~~~powershell
.\gradlew.bat zipMod
.\gradlew.bat jarAndroid
~~~

桌面构建位于 build/libs/，Android 构建位于 dist/。

## English

> See the power situation of a large base at a glance.

Power Grid Minimap is a Mindustry client-side information mod. It brings power-network boundaries, live balance, and outage hints into the minimap and full map, so you can understand a large base without checking every building and connection by hand.

It is most useful when a base grows complex, long-range power links are involved, or a grid suddenly splits. The mod only improves local presentation, requires no server installation, and works in both singleplayer and multiplayer.

### Install

Download powergrid-minimap.zip or powergrid-minimap.jar from Releases, put it in Mindustry's mods directory, and enable it in-game.

### Usage

Open the minimap or full map after enabling the mod. Display styles and thresholds are available under Settings → Mods → Power Grid Minimap. MindustryX or MI2-Utilities can provide additional overlay integration, but is not required for the core experience.

### Build

~~~powershell
.\gradlew.bat zipMod
.\gradlew.bat jarAndroid
~~~

Desktop artifacts are written to build/libs/ and Android artifacts to dist/.
